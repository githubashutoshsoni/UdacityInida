package com.example.kakashi.inventoryapplication.data;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

public final class InventoryContract {


    public static final String CONTENT_AUTHORITY = "com.example.kakashi.inventoryapplication";

    public static final Uri BASE_CONTENT_URI= Uri.parse("content://"+CONTENT_AUTHORITY);

    public static final String PATH_INVENTORY="inventory";



    public static final class InventoryEntries implements BaseColumns {

        private InventoryEntries(){

        }
        //todo add conetent uri's here.
        public static final Uri CONTENT_URI=Uri.withAppendedPath(BASE_CONTENT_URI,PATH_INVENTORY);

        public static final String CONTENT_LIST_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_INVENTORY;

        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_INVENTORY;


        public final static String TABLE_NAME = "inventory";



        public final static String COLUMN_PRODUCT_NAME = "name";
        public final static String COLUMN_PRICE = "price";
        public final static String COLUMN_QUANTITY = "quantity";
        public final static String COLUMN_SUPPLIER = "supplier";
        public final static String COLUMN_SUPPLIER_PHONE_NUMBER = "phone";

        public static final int SUPPLIER_UNKNOWN = 0;
        public static final int SUPPLIER_LOCAL = 1;
        public static final int SUPPLIER_INTERNATIONAL = 2;
    }
}
