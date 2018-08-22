package com.example.kakashi.inventoryapplication.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.kakashi.inventoryapplication.data.InventoryContract.InventoryEntries;

import android.os.Build;
import android.util.Log;


public class InventoryDbHelper extends SQLiteOpenHelper {
    private static final String LOG_TAG = InventoryDbHelper.class.getSimpleName();

    private static final String DATABASE_NAME = "inventory.db";

    private static final int DB_VERSION = 1;

    public InventoryDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_TABLE = "CREATE TABLE " + InventoryEntries.TABLE_NAME
                + "("
                + InventoryEntries._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + InventoryEntries.COLUMN_PRODUCT_NAME + " TEXT NOT NULL, "
                + InventoryEntries.COLUMN_QUANTITY + " INTEGER NOT NULL, "
                + InventoryEntries.COLUMN_PRICE + " INTEGER NOT NULL, "
                + InventoryEntries.COLUMN_SUPPLIER + " TEXT NOT NULL, "
                + InventoryEntries.COLUMN_SUPPLIER_PHONE_NUMBER + " INTEGER NOT NULL "
                + ");";
        Log.d(LOG_TAG, "onCreate: SQL CREATE TABLE  ");
        sqLiteDatabase.execSQL(CREATE_TABLE);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
