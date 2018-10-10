package com.example.kakashi.inventoryapplication;

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

import com.example.kakashi.inventoryapplication.data.InventoryContract;


public class ItemCursorAdapter extends CursorAdapter {
    public ItemCursorAdapter(Context context, Cursor c) {
        super(context, c,0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return LayoutInflater.from(context).inflate(R.layout.list_items,viewGroup,false);

    }

    @Override
    public void bindView(View view, final Context context, Cursor cursor) {
        TextView productNameTextView= view.findViewById(R.id.name_edit);
        TextView quantityTextView= view.findViewById(R.id.quantity);
        TextView priceTextView= view.findViewById(R.id.price);


        final int idColumnIndex = cursor.getInt(cursor.getColumnIndex(InventoryContract.InventoryEntries._ID));

        int nameIndex= cursor.getColumnIndex(InventoryContract.InventoryEntries.COLUMN_PRODUCT_NAME);
        int priceIndex= cursor.getColumnIndex(InventoryContract.InventoryEntries.COLUMN_PRICE);
        final int quantityIndex= cursor.getColumnIndex(InventoryContract.InventoryEntries.COLUMN_QUANTITY);

        String name= cursor.getString(nameIndex);
        int price= cursor.getInt(priceIndex);
        final int quantity= cursor.getInt(quantityIndex);


        productNameTextView.setText(name);
        quantityTextView.setText(String.valueOf(price));
        priceTextView.setText(String.valueOf(quantity));

//        Button sale= view.findViewById(R.id.subtract);
//        sale.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(context,"hello",Toast.LENGTH_SHORT);
//                Uri contentUri= ContentUris.withAppendedId(InventoryContract.InventoryEntries.CONTENT_URI,idColumnIndex);
//                ContentValues contentValues= new ContentValues();
//                contentValues.put(InventoryContract.InventoryEntries.COLUMN_QUANTITY,quantity-1);
//                context.getContentResolver().update(contentUri,contentValues,null,null);
//
//            }
//        });

    }
}
