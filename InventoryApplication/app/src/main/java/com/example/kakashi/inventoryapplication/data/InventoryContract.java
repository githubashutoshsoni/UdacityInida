package com.example.kakashi.inventoryapplication.data;

import android.provider.BaseColumns;

public class InventoryContract {

    public class InventoryEntries implements BaseColumns {
        public final static String TABLE_NAME = "inventory";

        public final static String _ID = BaseColumns._ID;
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
