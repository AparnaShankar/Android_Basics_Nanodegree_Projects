package appy.aparna.example.com.inventory.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import appy.aparna.example.com.inventory.data.InventoryContract.ProductEntry;

/**
 * Created by Administrator on 6/4/2017.
 */

public class InventoryDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "inventory.db";

    private static final int DATABASE_VERSION = 1;

    public InventoryDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String SQL_CREATE_PRODUCT_TABLE = "CREATE TABLE " + ProductEntry.TABLE_NAME + "("
                + ProductEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + ProductEntry.COLUMN_PRO_NAME + " TEXT NOT NULL, "
                + ProductEntry.COLUMN_PRO_TYPE + " TEXT NOT NULL, "
                + ProductEntry.COLUMN_PRO_SUPPLIER + " TEXT NOT NULL, "
                + ProductEntry.COLUMN_PRO_PRICE + " INTEGER NOT NULL, "
                + ProductEntry.COLUMN_PRO_QUANTITY + " INTEGER NOT NULL DEFAULT 0, "
                + ProductEntry.COLUMN_PRO_IMAGE + " TEXT NOT NULL)";

        db.execSQL(SQL_CREATE_PRODUCT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
