package com.example.kakashi.inventoryapplication.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import com.example.kakashi.inventoryapplication.data.InventoryContract.InventoryEntries;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.widget.CursorAdapter;

public class InventoryProvider extends ContentProvider {

    private static final String LOG = InventoryProvider.class.getSimpleName();
    private static final int INVENTORY = 100;
    private static final int INVENTORY_ID = 101;

    private static final UriMatcher sUriMatcher= new UriMatcher(UriMatcher.NO_MATCH);


    static{
        sUriMatcher.addURI(InventoryContract.CONTENT_AUTHORITY,InventoryContract.PATH_INVENTORY,INVENTORY);
        sUriMatcher.addURI(InventoryContract.CONTENT_AUTHORITY,InventoryContract.PATH_INVENTORY+"/#",INVENTORY_ID);
    }

    private InventoryDbHelper mDbHelper;
    @Override
    public boolean onCreate() {
         mDbHelper= new InventoryDbHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projections, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        SQLiteDatabase sqLiteDatabase= mDbHelper.getReadableDatabase();
        int match= sUriMatcher.match(uri);
        Cursor cursor;
        switch (match){
            case INVENTORY:
            cursor=sqLiteDatabase.query(InventoryContract.InventoryEntries.TABLE_NAME,projections,selection,selectionArgs,null,null,sortOrder);
                break;
            case INVENTORY_ID:
                selection= InventoryContract.InventoryEntries._ID+"=?";
                selectionArgs= new String[] {String.valueOf(ContentUris.parseId(uri))};
             cursor=   sqLiteDatabase.query(InventoryContract.InventoryEntries.TABLE_NAME,projections,selection,selectionArgs,null,null,sortOrder);
            break;
            default:
                throw new IllegalArgumentException("Cannot query unknown URI " + uri);
        }
        cursor.setNotificationUri(getContext().getContentResolver(),uri);
        return cursor;
    }



    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {


        int match=sUriMatcher.match(uri);
        switch (match) {
            case INVENTORY:
                return insertInventory(uri, contentValues);
            default:
                throw new IllegalArgumentException("Insertion is not supported for " + uri);
        }


    }


   Uri insertInventory(Uri uri, ContentValues contentValues){
        String productName= contentValues.getAsString(InventoryEntries.COLUMN_PRODUCT_NAME);
        Integer quantity= contentValues.getAsInteger(InventoryEntries.COLUMN_QUANTITY);
        Integer price= contentValues.getAsInteger(InventoryEntries.COLUMN_PRICE);
        String supplier= contentValues.getAsString(InventoryEntries.COLUMN_SUPPLIER);
        Integer phone= contentValues.getAsInteger(InventoryEntries.COLUMN_SUPPLIER_PHONE_NUMBER);

       if(productName==null)
           throw new IllegalArgumentException("empty name ");
       if(supplier==null)
           throw new IllegalArgumentException("empty supplier ");
       if(phone==null)
           throw new IllegalArgumentException("empty phone number");
       if(price==null)
           throw new IllegalArgumentException("enter price");
       SQLiteDatabase sqLiteDatabase= mDbHelper.getWritableDatabase();
       long id  = sqLiteDatabase.insert(InventoryEntries.TABLE_NAME,null,contentValues);
       if(id==-1){
           Log.e(LOG, "Failed to insert row for " + uri);
           return null;
       }
        getContext().getContentResolver().notifyChange(uri,null);
       return ContentUris.withAppendedId(uri,id);
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        // Get writable database
        SQLiteDatabase database = mDbHelper.getWritableDatabase();

        // Track the number of rows that were deleted
        int rowsDeleted;

        final int match = sUriMatcher.match(uri);
        switch (match) {
            case INVENTORY:
                // Delete all rows that match the selection and selection args
                rowsDeleted = database.delete(InventoryEntries.TABLE_NAME, selection, selectionArgs);
                break;
            case INVENTORY_ID:
                // Delete a single row given by the ID in the URI
                selection = InventoryEntries._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                rowsDeleted = database.delete(InventoryEntries.TABLE_NAME, selection, selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Deletion is not supported for " + uri);
        }

        // If 1 or more rows were deleted, then notify all listeners that the data at the
        // given URI has changed
        if (rowsDeleted != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        // Return the number of rows deleted
        return rowsDeleted;

    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String selection, @Nullable String[] selectionArgs) {
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case INVENTORY:
                return updatePet(uri, contentValues, selection, selectionArgs);
            case INVENTORY_ID:
                // For the PET_ID code, extract out the ID from the URI,
                // so we know which row to update. Selection will be "_id=?" and selection
                // arguments will be a String array containing the actual ID.
                selection = InventoryEntries._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                return updatePet(uri, contentValues, selection, selectionArgs);
            default:
                throw new IllegalArgumentException("Update is not supported for " + uri);
        }
    }
    int updatePet(Uri uri, ContentValues values, String selection, String[] selectionArgs){
        if (values.containsKey(InventoryEntries.COLUMN_PRODUCT_NAME)) {
            String name = values.getAsString(InventoryEntries.COLUMN_PRODUCT_NAME);
            if (name == null) {
                throw new IllegalArgumentException("Inventory requires a name");
            }
        }

        if (values.containsKey(InventoryEntries.COLUMN_PRICE)) {
            Integer price = values.getAsInteger(InventoryEntries.COLUMN_PRICE);
            if (price != null && price < 0) {
                throw new IllegalArgumentException("Inventory requires valid price");
            }
        }
        if (values.containsKey(InventoryEntries.COLUMN_SUPPLIER_PHONE_NUMBER)) {
            Integer phone = values.getAsInteger(InventoryEntries.COLUMN_SUPPLIER_PHONE_NUMBER);
            if (phone != null && phone < 0) {
                throw new IllegalArgumentException("Inventory requires valid phone");
            }
        }

        if (values.containsKey(InventoryEntries.COLUMN_SUPPLIER)) {
            String supplier = values.getAsString(InventoryEntries.COLUMN_SUPPLIER);
            if (supplier != null ) {
                throw new IllegalArgumentException("Inventory requires valid supplier");
            }
        }

        if (values.size() == 0) {
            return 0;
        }

        // Otherwise, get writeable database to update the data
        SQLiteDatabase database = mDbHelper.getWritableDatabase();

        // Perform the update on the database and get the number of rows affected
        int rowsUpdated = database.update(InventoryEntries.TABLE_NAME, values, selection, selectionArgs);

        // If 1 or more rows were updated, then notify all listeners that the data at the
        // given URI has changed
        if (rowsUpdated != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        // Return the number of rows updated
        return rowsUpdated;
    }
}
