package com.example.kakashi.inventoryapplication;

import android.app.LoaderManager;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.kakashi.inventoryapplication.data.InventoryContract.InventoryEntries;
import com.example.kakashi.inventoryapplication.data.InventoryDbHelper;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {
    private InventoryDbHelper mDbHelper;
    TextView productName;
    TextView quantityTextView;
    TextView supplierTextView;
    TextView phoneTextView;
    ItemCursorAdapter mAdapter;
    private static final int INVENTORY_LOADER = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        productName = findViewById(R.id.name_edit);
        quantityTextView= findViewById(R.id.quantity);
        supplierTextView= findViewById(R.id.supplier_name_edit);
        phoneTextView= findViewById(R.id.phone_no_edit);

        ListView listView= findViewById(R.id.list);


        mAdapter= new ItemCursorAdapter(this,null);
        listView.setAdapter(mAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long id) {
                Intent intent= new Intent(MainActivity.this, ProductDetail.class);
                Uri currentUri= ContentUris.withAppendedId(InventoryEntries.CONTENT_URI,id);
                intent.setData(currentUri);
                startActivity(intent);
            }
        });
        getLoaderManager().initLoader(INVENTORY_LOADER,null,this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    private void saveData() {
        ContentValues values = new ContentValues();
        values.put(InventoryEntries.COLUMN_PRODUCT_NAME, "PEN");
        values.put(InventoryEntries.COLUMN_SUPPLIER, "ASIA");
        values.put(InventoryEntries.COLUMN_QUANTITY, 12);
        values.put(InventoryEntries.COLUMN_SUPPLIER_PHONE_NUMBER, 714);
        values.put(InventoryEntries.COLUMN_PRICE,200);

        Uri newUri = getContentResolver().insert(InventoryEntries.CONTENT_URI, values);


    }

    void deleteAllPets(){
        int rowsDeleted = getContentResolver().delete(InventoryEntries.CONTENT_URI, null, null);
        Log.v("MainActivity", rowsDeleted + " rows deleted from Inventory database");

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.dummy:
                saveData();
                return true;

            case R.id.delete_edit:
                deleteAllPets();
                return true;
            case R.id.insert:
                Intent intent= new Intent(MainActivity.this, ProductDetail.class);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public Loader<Cursor> onCreateLoader(int i,  Bundle bundle) {
        String[] projection = {
                InventoryEntries._ID,
                InventoryEntries.COLUMN_PRICE,
                InventoryEntries.COLUMN_QUANTITY,
                InventoryEntries.COLUMN_PRODUCT_NAME };

        // This loader will execute the ContentProvider's query method on a background thread
        return new CursorLoader(this,   // Parent activity context
                InventoryEntries.CONTENT_URI,   // Provider content URI to query
                projection,             // Columns to include in the resulting Cursor
                null,                   // No selection clause
                null,                   // No selection arguments
                null);
    }

    @Override
    public void onLoadFinished(android.content.Loader<Cursor> loader, Cursor cursor) {
        if(cursor.getCount()<1 || cursor==null){
            Log.e("SOMETAGHERE", "onLoadFinished:less ");

        }
        cursor.moveToFirst();
        if(cursor.getCount()==0 || cursor.getCount()>0){
            Log.e("SOMETAGHERE", "onLoadFinished: Items inserted ");

            mAdapter.swapCursor(cursor);

        }


    }

    @Override
    public void onLoaderReset(android.content.Loader<Cursor> loader) {
        mAdapter.swapCursor(null);

    }

}
