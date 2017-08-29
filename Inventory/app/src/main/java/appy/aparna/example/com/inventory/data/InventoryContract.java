package appy.aparna.example.com.inventory.data;

/**
 * Created by Administrator on 6/4/2017.
 */


import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;


public final class InventoryContract {
    /**
     * The "Content authority" is a name for the entire content provider, similar to the
     * relationship between a domain name and its website.  A convenient string to use for the
     * content authority is the package name for the app, which is guaranteed to be unique on the
     * device.
     */
    public static final String CONTENT_AUTHORITY = "appy.aparna.example.com.inventory";

    /**
     * Use CONTENT_AUTHORITY to create the base of all URI's which apps will use to contact
     * the content provider.
     */
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    /**
     * Possible path (appended to base content URI for possible URI's)
     */
    public static final String PATH_PRODUCT = "product";

    private InventoryContract() {
    }

    public static final class ProductEntry implements BaseColumns {
        /**
         * The MIME type of the {@link #CONTENT_URI} for a list of products.
         */
        public static final String CONTENT_LIST_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_PRODUCT;

        /**
         * The MIME type of the {@link #CONTENT_URI} for a single product.
         */
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_PRODUCT;

        /**
         * The content URI to access the product data in the provider
         */
        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_PRODUCT);
        public static final String TABLE_NAME = "product";

        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_PRO_NAME = "name";
        public static final String COLUMN_PRO_TYPE = "type";
        public static final String COLUMN_PRO_SUPPLIER = "supplier";
        public static final String COLUMN_PRO_PRICE = "price";
        public static final String COLUMN_PRO_QUANTITY = "quantity";
        public static final String COLUMN_PRO_IMAGE = "image";


    }
}
