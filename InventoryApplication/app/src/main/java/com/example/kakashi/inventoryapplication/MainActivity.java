package com.example.kakashi.inventoryapplication;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kakashi.inventoryapplication.data.InventoryContract.InventoryEntries;
import com.example.kakashi.inventoryapplication.data.InventoryDbHelper;

public class MainActivity extends AppCompatActivity {
    private InventoryDbHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    private void insertData() {
        // Insert into database.
        mDbHelper = new InventoryDbHelper(this);

        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(InventoryEntries.COLUMN_PRODUCT_NAME, "Shampoo");
        values.put(InventoryEntries.COLUMN_PRICE, 300);
        values.put(InventoryEntries.COLUMN_QUANTITY, 5);
        values.put(InventoryEntries.COLUMN_SUPPLIER, InventoryEntries.SUPPLIER_LOCAL);
        values.put(InventoryEntries.COLUMN_SUPPLIER_PHONE_NUMBER, 9900);

        long error = db.insert(InventoryEntries.TABLE_NAME, null, values);
        if (error == -1)
            Toast.makeText(MainActivity.this, "error while inserting into database", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();

    }

    private void queryData() {
        /**
         * Query the database.
         * Always close the cursor when you're done reading from it.
         * This releases all its resources and makes it invalid.
         */
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        String[] projection = {
                InventoryEntries._ID,
                InventoryEntries.COLUMN_PRODUCT_NAME,
                InventoryEntries.COLUMN_SUPPLIER,
                InventoryEntries.COLUMN_SUPPLIER_PHONE_NUMBER,
                InventoryEntries.COLUMN_PRICE,
                InventoryEntries.COLUMN_QUANTITY
        };
        Cursor cursor = db.query(InventoryEntries.TABLE_NAME, projection, null, null, null, null, null);
        TextView mainActivityTextView = (TextView) findViewById(R.id.main_activity);
        try {
            mainActivityTextView.setText("Checking if this works");
            mainActivityTextView.append(InventoryEntries._ID + " " +
                    InventoryEntries.COLUMN_QUANTITY + " " +
                    InventoryEntries.COLUMN_PRICE + " " + InventoryEntries.COLUMN_SUPPLIER + " "
                    + InventoryEntries.COLUMN_SUPPLIER_PHONE_NUMBER + " " + InventoryEntries.COLUMN_PRODUCT_NAME
                    + "\n"
            );
            int idColumnIndex = cursor.getColumnIndex(InventoryEntries._ID);
            int productName = cursor.getColumnIndex(InventoryEntries.COLUMN_PRODUCT_NAME);
            int supplier = cursor.getColumnIndex(InventoryEntries.COLUMN_SUPPLIER);

            while (cursor.moveToNext()) {
                int currentId = cursor.getInt(idColumnIndex);
                String currentProduct = cursor.getString(productName);
                mainActivityTextView.append(currentId + currentProduct);
            }
        } finally {

            cursor.close();
        }


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.dummy:
                insertData();
                queryData();
                break;

        }
        return super.onOptionsItemSelected(item);
    }
}
