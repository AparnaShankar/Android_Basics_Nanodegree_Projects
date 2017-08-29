package appy.aparna.example.com.inventory;

/**
 * Created by Administrator on 6/4/2017.
 */

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import appy.aparna.example.com.inventory.data.InventoryContract.ProductEntry;

/**
 * {@link InventoryCursorAdapter} is an adapter for a list or grid view
 * that uses a {@link Cursor} of product data as its data source. This adapter knows
 * how to create list items for each row of product data in the {@link Cursor}.
 */
public class InventoryCursorAdapter extends CursorAdapter {
    /**
     * Constructs a new {@link InventoryCursorAdapter}.
     *
     * @param context The context
     * @param c       The cursor from which to get the data.
     */
    public InventoryCursorAdapter(Context context, Cursor c) {
        super(context, c, 0 /* flags */);
    }

    /**
     * Makes a new blank list item view. No data is set (or bound) to the views yet.
     *
     * @param context app context
     * @param cursor  The cursor from which to get the data. The cursor is already
     *                moved to the correct position.
     * @param parent  The parent to which the new view is attached to
     * @return the newly created list item view.
     */
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {

        return LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
    }

    /**
     * This method binds the product data (in the current row pointed to by cursor) to the given
     * list item layout.
     *
     * @param view    Existing view, returned earlier by newView() method
     * @param context app context
     * @param cursor  The cursor from which to get the data. The cursor is already moved to the
     *                correct row.
     */
    @Override
    public void bindView(View view, final Context context, Cursor cursor) {

        // Find individual views that we want to modify in the list item layout
        TextView nameTextView = (TextView) view.findViewById(R.id.name);
        TextView priceTextView = (TextView) view.findViewById(R.id.price);
        final TextView quantTextView = (TextView) view.findViewById(R.id.quantity);

        // Find the columns of product attributes that we're interested in
        int nameColumnIndex = cursor.getColumnIndex(ProductEntry.COLUMN_PRO_NAME);
        int priceColumnIndex = cursor.getColumnIndex(ProductEntry.COLUMN_PRO_PRICE);
        int quantityColumnIndex = cursor.getColumnIndex(ProductEntry.COLUMN_PRO_QUANTITY);

        // Read the product attributes from the Cursor for the current product
        String proName = cursor.getString(nameColumnIndex);
        String proPrice = String.valueOf(cursor.getInt(priceColumnIndex));
        final int proQuantity = cursor.getInt(quantityColumnIndex);

        // Update the TextViews with the attributes for the current product
        nameTextView.setText(proName);
        priceTextView.setText("Rs " + proPrice);
        quantTextView.setText("" + proQuantity);

        //get row id to be updated
        final int rowId = cursor.getInt(cursor.getColumnIndex(ProductEntry._ID));

        //Button
        Button sell = (Button) view.findViewById(R.id.sell);

        sell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int qty = proQuantity;

                if (qty > 0) {
                    qty--;

                    ContentValues values = new ContentValues();
                    Uri uri = ContentUris.withAppendedId(ProductEntry.CONTENT_URI, rowId);
                    values.put(ProductEntry.COLUMN_PRO_QUANTITY, qty);

                    int rowsAffected = context.getContentResolver().update(uri, values, null, null);

                    // Show a toast message depending on whether or not the update was successful.
                    if (rowsAffected == 0) {
                        // If no rows were affected, then there was an error with the update.
                        Toast.makeText(context, R.string.update_failed, Toast.LENGTH_SHORT).show();
                    } else {
                        // Otherwise, the update was successful and we can display a toast.
                        Toast.makeText(context, R.string.update_successful, Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(context, R.string.no_items, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
