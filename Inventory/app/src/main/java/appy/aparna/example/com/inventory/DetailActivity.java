package appy.aparna.example.com.inventory;

import android.app.AlertDialog;
import android.app.LoaderManager;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import appy.aparna.example.com.inventory.data.InventoryContract.ProductEntry;

import static appy.aparna.example.com.inventory.data.InventoryProvider.LOG_TAG;

public class DetailActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    /**
     * Identifier for the product data loader
     */
    private static final int EXISTING_PRODUCT_LOADER = 0;

    /**
     * Content URI for the existing PRODUCT
     */
    private Uri mCurrentPRODUCTUri;

    private TextView nameTextView;
    private TextView typeTextView;
    private TextView supplyTextView;
    private TextView priceTextView;
    private TextView quantityTextView;
    private ImageView mImage;
    private int qty = 0;

    /**
     * Boolean flag that keeps track of whether the PRODUCT has been edited (true) or not (false)
     */
    private boolean mProductHasChanged = false;

    /**
     * OnTouchListener that listens for any user touches on a View, implying that they are modifying
     * the view, and we change the mProductHasChanged boolean to true.
     */
    private View.OnTouchListener mTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            mProductHasChanged = true;
            return false;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // Examine the intent that was used to launch this activity,
        Intent intent = getIntent();
        mCurrentPRODUCTUri = intent.getData();

        // Initialize a loader to read the PRODUCT data from the database
        // and display the current values in the editor
        getLoaderManager().initLoader(EXISTING_PRODUCT_LOADER, null, this);


        // Find all relevant views that we will need to read user input from
        nameTextView = (TextView) findViewById(R.id.Name);
        typeTextView = (TextView) findViewById(R.id.Type);
        supplyTextView = (TextView) findViewById(R.id.Supplier);
        priceTextView = (TextView) findViewById(R.id.Price);
        quantityTextView = (TextView) findViewById(R.id.Quantity);
        mImage = (ImageView) findViewById(R.id.image_detail);
        Button delete = (Button) findViewById(R.id.delete);
        Button order = (Button) findViewById(R.id.order);
        Button inc = (Button) findViewById(R.id.button_increment);
        Button dec = (Button) findViewById(R.id.button_decrement);

        // Setup OnTouchListeners on all the input fields, so we can determine if the user
        // has touched or modified them. This will let us know if there are unsaved changes
        // or not, if the user tries to leave the editor without saving.
        inc.setOnTouchListener(mTouchListener);
        dec.setOnTouchListener(mTouchListener);

        dec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (qty > 0) {
                    qty--;
                }
                quantityTextView.setText(String.valueOf(qty));
            }
        });
        inc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                qty++;
                quantityTextView.setText(String.valueOf(qty));
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDeleteConfirmationDialog();
            }
        });

        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:")); // only email apps should handle this
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });
    }

    /**
     * Update product quantity
     */
    private void updateProduct() {

        ContentValues values = new ContentValues();
        values.put(ProductEntry.COLUMN_PRO_QUANTITY, qty);
        //update the PRODUCT with content URI: mCurrentPRODUCTUri
        // and pass in the new ContentValues. Pass in null for the selection and selection args
        // because mCurrentPRODUCTUri will already identify the correct row in the database that
        // we want to modify.
        int rowsAffected = getContentResolver().update(mCurrentPRODUCTUri, values, null, null);

        // Show a toast message depending on whether or not the update was successful.
        if (rowsAffected == 0) {
            // If no rows were affected, then there was an error with the update.
            Toast.makeText(this, getString(R.string.update_failed),
                    Toast.LENGTH_SHORT).show();
        } else {
            // Otherwise, the update was successful and we can display a toast.
            Toast.makeText(this, getString(R.string.update_successful),
                    Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_detail.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "Save" menu option
            case R.id.save:
                // Save PRODUCT to database
                updateProduct();
                // Exit activity
                finish();
                return true;
            // Respond to a click on the "Up" arrow button in the app bar
            case android.R.id.home:
                // If the PRODUCT hasn't changed, continue with navigating up to parent activity
                if (!mProductHasChanged) {
                    NavUtils.navigateUpFromSameTask(DetailActivity.this);
                    return true;
                }
                // Otherwise if there are unsaved changes, setup a dialog to warn the user.
                // Create a click listener to handle the user confirming that
                // changes should be discarded.
                DialogInterface.OnClickListener discardButtonClickListener =
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                // User clicked "Discard" button, navigate to parent activity.
                                NavUtils.navigateUpFromSameTask(DetailActivity.this);
                            }
                        };

                // Show a dialog that notifies the user they have unsaved changes
                showUnsavedChangesDialog(discardButtonClickListener);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * This method is called when the back button is pressed.
     */
    @Override
    public void onBackPressed() {
        // If the PRODUCT hasn't changed, continue with handling back button press
        if (!mProductHasChanged) {
            super.onBackPressed();
            return;
        }

        // Otherwise if there are unsaved changes, setup a dialog to warn the user.
        // Create a click listener to handle the user confirming that changes should be discarded.
        DialogInterface.OnClickListener discardButtonClickListener =
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // User clicked "Discard" button, close the current activity.
                        finish();
                    }
                };

        // Show dialog that there are unsaved changes
        showUnsavedChangesDialog(discardButtonClickListener);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        // Since the editor shows all PRODUCT attributes, define a projection that contains
        // all columns from the PRODUCT table
        String[] projection = {
                ProductEntry._ID,
                ProductEntry.COLUMN_PRO_NAME,
                ProductEntry.COLUMN_PRO_TYPE,
                ProductEntry.COLUMN_PRO_SUPPLIER,
                ProductEntry.COLUMN_PRO_PRICE,
                ProductEntry.COLUMN_PRO_QUANTITY,
                ProductEntry.COLUMN_PRO_IMAGE};

        // This loader will execute the ContentProvider's query method on a background thread
        return new CursorLoader(this,   // Parent activity context
                mCurrentPRODUCTUri,         // Query the content URI for the current PRODUCT
                projection,             // Columns to include in the resulting Cursor
                null,                   // No selection clause
                null,                   // No selection arguments
                null);                  // Default sort order
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        // Bail early if the cursor is null or there is less than 1 row in the cursor
        if (cursor == null || cursor.getCount() < 1) {
            return;
        }

        // Proceed with moving to the first row of the cursor and reading data from it
        // (This should be the only row in the cursor)
        if (cursor.moveToFirst()) {

            // Find the columns of PRODUCT attributes that we're interested in
            int nameColumnIndex = cursor.getColumnIndex(ProductEntry.COLUMN_PRO_NAME);
            int typeColumnIndex = cursor.getColumnIndex(ProductEntry.COLUMN_PRO_TYPE);
            int supplyColumnIndex = cursor.getColumnIndex(ProductEntry.COLUMN_PRO_SUPPLIER);
            int priceColumnIndex = cursor.getColumnIndex(ProductEntry.COLUMN_PRO_PRICE);
            int quantityColumnIndex = cursor.getColumnIndex(ProductEntry.COLUMN_PRO_QUANTITY);
            int imageColumnIndex = cursor.getColumnIndex(ProductEntry.COLUMN_PRO_IMAGE);

            // Extract out the value from the Cursor for the given column index
            String name = cursor.getString(nameColumnIndex);
            String type = cursor.getString(typeColumnIndex);
            String supply = cursor.getString(supplyColumnIndex);
            int price = cursor.getInt(priceColumnIndex);
            int quantity = cursor.getInt(quantityColumnIndex);
            String simage = cursor.getString(imageColumnIndex);
            Uri mUri = Uri.parse(simage);
            qty = quantity;

            // Update the views on the screen with the values from the database

            mImage.setImageBitmap(getBitmapFromUri(mUri));
            nameTextView.setText(name);
            typeTextView.setText(type);
            supplyTextView.setText(supply);
            priceTextView.setText("Rs " + Integer.toString(price));
            quantityTextView.setText(Integer.toString(quantity));

        }
    }

    public Bitmap getBitmapFromUri(Uri uri) {

        if (uri == null || uri.toString().isEmpty())
            return null;

        // Get the dimensions of the View
        int targetW = mImage.getWidth();
        int targetH = mImage.getHeight();

        InputStream input = null;
        try {
            input = this.getContentResolver().openInputStream(uri);

            // Get the dimensions of the bitmap
            BitmapFactory.Options bmOptions = new BitmapFactory.Options();
            bmOptions.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(input, null, bmOptions);
            input.close();

            int photoW = bmOptions.outWidth;
            int photoH = bmOptions.outHeight;

            // Determine how much to scale down the image
            int scaleFactor = Math.min(photoW / targetW, photoH / targetH);

            // Decode the image file into a Bitmap sized to fill the View
            bmOptions.inJustDecodeBounds = false;
            bmOptions.inSampleSize = scaleFactor;
            bmOptions.inPurgeable = true;

            input = this.getContentResolver().openInputStream(uri);
            Bitmap bitmap = BitmapFactory.decodeStream(input, null, bmOptions);
            input.close();
            return bitmap;

        } catch (FileNotFoundException fne) {
            Log.e(LOG_TAG, "Failed to load image.", fne);
            return null;
        } catch (Exception e) {
            Log.e(LOG_TAG, "Failed to load image.", e);
            return null;
        } finally {
            try {
                input.close();
            } catch (IOException ioe) {

            }
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        // If the loader is invalidated, clear out all the data from the input fields.
        nameTextView.setText("");
        typeTextView.setText("");
        supplyTextView.setText("");
        priceTextView.setText("");
        quantityTextView.setText("");
    }

    /**
     * Show a dialog that warns the user there are unsaved changes that will be lost
     * if they continue leaving the editor.
     *
     * @param discardButtonClickListener is the click listener for what to do when
     *                                   the user confirms they want to discard their changes
     */
    private void showUnsavedChangesDialog(
            DialogInterface.OnClickListener discardButtonClickListener) {
        // Create an AlertDialog.Builder and set the message, and click listeners
        // for the postive and negative buttons on the dialog.
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.unsaved_changes_dialog_msg);
        builder.setPositiveButton(R.string.discard, discardButtonClickListener);
        builder.setNegativeButton(R.string.keep_editing, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked the "Keep editing" button, so dismiss the dialog
                // and continue editing the PRODUCT.
                if (dialog != null) {
                    dialog.dismiss();
                }
            }
        });

        // Create and show the AlertDialog
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    /**
     * Prompt the user to confirm that they want to delete this PRODUCT.
     */
    private void showDeleteConfirmationDialog() {
        // Create an AlertDialog.Builder and set the message, and click listeners
        // for the postive and negative buttons on the dialog.
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.delete_pro_msg);
        builder.setPositiveButton(R.string.delete, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked the "Delete" button, so delete the PRODUCT.
                deletePRODUCT();
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked the "Cancel" button, so dismiss the dialog
                // and continue editing the PRODUCT.
                if (dialog != null) {
                    dialog.dismiss();
                }
            }
        });

        // Create and show the AlertDialog
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    /**
     * Perform the deletion of the PRODUCT in the database.
     */
    private void deletePRODUCT() {
        // Only perform the delete if this is an existing PRODUCT.
        if (mCurrentPRODUCTUri != null) {
            // Call the ContentResolver to delete the PRODUCT at the given content URI.
            // Pass in null for the selection and selection args because the mCurrentPRODUCTUri
            // content URI already identifies the PRODUCT that we want.
            int rowsDeleted = getContentResolver().delete(mCurrentPRODUCTUri, null, null);

            // Show a toast message depending on whether or not the delete was successful.
            if (rowsDeleted == 0) {
                // If no rows were deleted, then there was an error with the delete.
                Toast.makeText(this, getString(R.string.delete_failed),
                        Toast.LENGTH_SHORT).show();
            } else {
                // Otherwise, the delete was successful and we can display a toast.
                Toast.makeText(this, getString(R.string.delete_successful),
                        Toast.LENGTH_SHORT).show();
            }
        }

        // Close the activity
        finish();
    }

}
