package com.example.kakashi.inventoryapplication;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

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
    public void bindView(View view, Context context, Cursor cursor) {
        TextView productNameTextView= view.findViewById(R.id.name);
        TextView quantityTextView= view.findViewById(R.id.quantity);
        TextView priceTextView= view.findViewById(R.id.price);

        if(cursor.size()>0)
        {   cursor.moveToNext()
            while(cursor.next){

            }
        }

        int nameIndex= cursor.getColumnIndex(InventoryContract.InventoryEntries.COLUMN_PRODUCT_NAME);
        int priceIndex= cursor.getColumnIndex(InventoryContract.InventoryEntries.COLUMN_PRICE);
        int quantityIndex= cursor.getColumnIndex(InventoryContract.InventoryEntries.COLUMN_QUANTITY);

        String name= cursor.getString(nameIndex);
        int price= cursor.getInt(priceIndex);
        int quantity= cursor.getInt(quantityIndex);


        productNameTextView.setText(name);
        quantityTextView.setText(price);
       priceTextView.setText(quantity);

    }
}
