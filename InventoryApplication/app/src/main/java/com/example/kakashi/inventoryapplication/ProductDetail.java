package com.example.kakashi.inventoryapplication;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Build;
import android.support.v4.content.CursorLoader;
import android.content.DialogInterface;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.Loader;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telecom.Call;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kakashi.inventoryapplication.data.InventoryContract;

public class ProductDetail extends AppCompatActivity implements android.support.v4.app.LoaderManager.LoaderCallbacks<Cursor> {

    TextView productName;
    TextView quantityTextView;
    TextView supplierTextView;
    TextView phoneTextView;
    TextView priceTextView;
    private static final int EXISTING_INVENTORY_LOADER = 0;
    Uri mCurrentInventoryUri;

    AlertDialog.Builder builder;


    Button plusButton;
    Button minusButton;
    Button deleteButton;
    Button callButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        priceTextView= findViewById(R.id.price_edit);
        productName= findViewById(R.id.name_edit);
        quantityTextView= findViewById(R.id.QuantityEdit);
        supplierTextView= findViewById(R.id.supplier_name_edit);
        phoneTextView= findViewById(R.id.phone_no_edit);

        plusButton= findViewById(R.id.plus_button);
        minusButton= findViewById(R.id.minus_button);
        deleteButton= findViewById(R.id.delete_edit);
        callButton= findViewById(R.id.call_edit);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(this);
        }

        mCurrentInventoryUri= getIntent().getData();
        if(mCurrentInventoryUri==null)
        {
            setTitle("add an inventory");
        }
        else{
            setTitle("Edit an Inventory");
            getSupportLoaderManager().initLoader(EXISTING_INVENTORY_LOADER, null, this);
        }
        final ContentValues cv= new ContentValues();
        plusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String quantityString= quantityTextView.getText().toString();
                int quantityInt= Integer.parseInt(quantityString);
                quantityTextView.setText(String.valueOf(quantityInt+1));
                cv.put(InventoryContract.InventoryEntries.COLUMN_QUANTITY,quantityInt+1);
                getContentResolver().update(mCurrentInventoryUri,cv,null,null);
            }
        });

        minusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                String quantityString= quantityTextView.getText().toString();

                int quantityInt= Integer.parseInt(quantityString);
                if(quantityInt<0){
                    Toast.makeText(ProductDetail.this, "no negative quants", Toast.LENGTH_SHORT).show();
                return;
                }
                else{
                    quantityTextView.setText(String.valueOf(quantityInt-1));
                    cv.put(InventoryContract.InventoryEntries.COLUMN_QUANTITY,quantityInt-1);
                    getContentResolver().update(mCurrentInventoryUri,cv,null,null);
                }

            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                builder.setTitle("Delete entry")
                        .setMessage("Are you sure you want to delete this entry?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // continue with delete
                                getContentResolver().delete(mCurrentInventoryUri,null,null);
                                finish();
                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // do nothing
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();

            }
        });

        callButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent callIntent= new Intent(Intent.ACTION_DIAL);
                String phoneNumber= phoneTextView.getText().toString();
                if(TextUtils.isEmpty(phoneNumber))
                {
                    Toast.makeText(ProductDetail.this,"error",Toast.LENGTH_SHORT).show();
                }
                else {
                    callIntent.setData(Uri.parse("tel:" + phoneNumber));
                    if (callIntent.resolveActivity(getPackageManager()) != null) {
                        startActivity(callIntent);
                    }
                }
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_editor.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.edit, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        // If this is a new pet, hide the "Delete" menu item.
        if (mCurrentInventoryUri == null) {
            MenuItem menuItem = menu.findItem(R.id.action_delete);
            menuItem.setVisible(false);
        }
        return true;
    }

    private void saveInventory() {
        // Read from input fields
        // Use trim to eliminate leading or trailing white space
        String nameString = productName.getText().toString().trim();
        String priceString = priceTextView.getText().toString().trim();
        String supplierString = supplierTextView.getText().toString().trim();
        String quantityString= quantityTextView.getText().toString().trim();
        String phoneString= phoneTextView.getText().toString().trim();

        // Check if this is supposed to be a new pet
        // and check if all the fields in the editor are blank
        if (mCurrentInventoryUri == null &&
                TextUtils.isEmpty(nameString) || TextUtils.isEmpty(quantityString) ||
                TextUtils.isEmpty(supplierString)|| TextUtils.isEmpty(quantityString) ||
                TextUtils.isEmpty(phoneString))
                {
                    Toast.makeText(ProductDetail.this,"somethingiswrong add correct values bro",Toast.LENGTH_SHORT).show();
            // Since no fields were modified, we can return early without creating a new pet.
            // No need to create ContentValues and no need to do any ContentProvider operations.
            return;
        }
        int quantity=Integer.parseInt(quantityString);
        int phone= Integer.parseInt(phoneString);

         ContentValues values = new ContentValues();
        values.put(InventoryContract.InventoryEntries.COLUMN_PRODUCT_NAME, nameString);
        values.put(InventoryContract.InventoryEntries.COLUMN_PRICE, priceString);
        values.put(InventoryContract.InventoryEntries.COLUMN_QUANTITY,quantity );
        values.put(InventoryContract.InventoryEntries.COLUMN_SUPPLIER,supplierString);
        values.put(InventoryContract.InventoryEntries.COLUMN_SUPPLIER_PHONE_NUMBER,phone);
        // Determine if this is a new or existing pet by checking if mCurrentPetUri is null or not
        if (mCurrentInventoryUri == null) {
            // This is a NEW pet, so insert a new pet into the provider,
            // returning the content URI for the new pet.
            Uri newUri = getContentResolver().insert(InventoryContract.InventoryEntries.CONTENT_URI, values);

            // Show a toast message depending on whether or not the insertion was successful.
            if (newUri == null) {
                // If the new content URI is null, then there was an error with insertion.
                Toast.makeText(this, "insertion failder",
                        Toast.LENGTH_SHORT).show();
            } else {
                // Otherwise, the insertion was successful and we can display a toast.
                Toast.makeText(this, "insertion success",
                        Toast.LENGTH_SHORT).show();
            }
        } else {
            // Otherwise this is an EXISTING pet, so update the pet with content URI: mCurrentPetUri
            // and pass in the new ContentValues. Pass in null for the selection and selection args
            // because mCurrentPetUri will already identify the correct row in the database that
            // we want to modify.
            int rowsAffected = getContentResolver().update(mCurrentInventoryUri, values, null, null);

            // Show a toast message depending on whether or not the update was successful.
            if (rowsAffected == 0) {
                // If no rows were affected, then there was an error with the update.
                Toast.makeText(this, "failder update",
                        Toast.LENGTH_SHORT).show();
            } else {
                // Otherwise, the update was successful and we can display a toast.
                Toast.makeText(this,"success update",
                        Toast.LENGTH_SHORT).show();
            }
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "Save" menu option
            case R.id.action_save:
                // Save pet to database
                saveInventory();
                // Exit activity
                finish();
                return true;
            // Respond to a click on the "Delete" menu option
            case R.id.action_delete:

                finish();
                // Pop up confirmation dialog for deletion
                   return true;
            // Respond to a click on the "Up" arrow button in the app bar
            case android.R.id.home:
                // If the pet hasn't changed, continue with navigating up to parent activity
                // which is the {@link CatalogActivity}.


                // Otherwise if there are unsaved changes, setup a dialog to warn the user.
                // Create a click listener to handle the user confirming that
                // changes should be discarded.
                DialogInterface.OnClickListener discardButtonClickListener =
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                // User clicked "Discard" button, navigate to parent activity.
                      //          NavUtils.navigateUpFromSameTask(EditorActivity.this);
                            }
                        };

                // Show a dialog that notifies the user they have unsaved changes
          //      showUnsavedChangesDialog(discardButtonClickListener);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public Loader<Cursor> onCreateLoader(int i, @Nullable Bundle bundle) {

        String[] projection = {
                InventoryContract.InventoryEntries._ID,
                InventoryContract.InventoryEntries.COLUMN_PRODUCT_NAME,
                InventoryContract.InventoryEntries.COLUMN_QUANTITY,
                InventoryContract.InventoryEntries.COLUMN_PRICE,
                InventoryContract.InventoryEntries.COLUMN_SUPPLIER,
                InventoryContract.InventoryEntries.COLUMN_SUPPLIER_PHONE_NUMBER };

        // This loader will execute the ContentProvider's query method on a background thread
        return new CursorLoader(this,   // Parent activity context
                mCurrentInventoryUri,         // Query the content URI for the current pet
                projection,             // Columns to include in the resulting Cursor
                null,                   // No selection clause
                null,                   // No selection arguments
                null);                  // Default sort order

    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor cursor) {
        if (cursor == null || cursor.getCount() < 1) {
            return;
        }

        // Proceed with moving to the first row of the cursor and reading data from it
        // (This should be the only row in the cursor)
        if (cursor.moveToFirst()) {
            // Find the columns of pet attributes that we're interested in
            int productNameIndex = cursor.getColumnIndex(InventoryContract.InventoryEntries.COLUMN_PRODUCT_NAME);
            int quantityColumnIndex = cursor.getColumnIndex(InventoryContract.InventoryEntries.COLUMN_QUANTITY);
            int priceColumnIndex = cursor.getColumnIndex(InventoryContract.InventoryEntries.COLUMN_PRICE);
            int supplierColumnIndex = cursor.getColumnIndex(InventoryContract.InventoryEntries.COLUMN_SUPPLIER);

            // Extract out the value from the Cursor for the given column index
            String name = cursor.getString(productNameIndex);
            int quantity = cursor.getInt(quantityColumnIndex);
            int price = cursor.getInt(priceColumnIndex);
            String supplier = cursor.getString(supplierColumnIndex);

            // Update the views on the screen with the values from the database
            productName.setText(name);
            quantityTextView.setText(Integer.toString(quantity));
            priceTextView.setText(Integer.toString(price));

            // Gender is a dropdown spinner, so map the constant value from the database
            // into one of the dropdown options (0 is Unknown, 1 is Male, 2 is Female).
            // Then call setSelection() so that option is displayed on screen as the current selection.

        }
    }
    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {

    }

}
