package com.example.android.justjava.R;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the plus button is clicked.
     */
    public void increment(View view) {
        if(quantity>100){
            Toast toast= Toast.makeText(this,"Can't go more than 100", Toast.LENGTH_LONG);
            toast.show();
            return;
        }

        quantity = quantity + 1;
        displayQuantity(quantity);
    }

    /**
     * This method is called when the minus button is clicked.
     */
    public void decrement(View view) {
        if(quantity<1){
            Toast toast= Toast.makeText(this,"Can't go below 1", Toast.LENGTH_LONG);
            toast.show();
            return;
        }

        quantity = quantity - 1;
        displayQuantity(quantity);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        // Figure out if the user wants whipped cream topping
        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_check_box);
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();

        // Figure out if the user wants chocolate topping
        CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.chocolate_check_box);
        boolean hasChocolate = chocolateCheckBox.isChecked();

        // Calculate the price
        int price = calculatePrice(hasWhippedCream,hasChocolate);
        EditText nameTextView= (EditText) findViewById(R.id.name_edit_text);
        String name= nameTextView.getText().toString();
        // Display the order summary on the screen
        createOrderSummary(price, hasWhippedCream, hasChocolate,name);

    }

    /**
     * Calculates the price of the order.
     *
     * @return total price
     */
    private int calculatePrice(boolean hasWhippedCream, boolean chocolate) {
       int baseprice = 5;
        if(hasWhippedCream){
            baseprice += 1;
        }
        if(chocolate){
            baseprice+=2;
        }

        return quantity * baseprice;
    }

    /**
     * Create summary of the order.
     *
     * @param price           of the order
     * @param addWhippedCream is whether or not to add whipped cream to the coffee
     * @param addChocolate    is whether or not to add chocolate to the coffee
     * @return text summary
     */
    private void createOrderSummary(int price, boolean addWhippedCream, boolean addChocolate,String name) {

//        String priceMessage = "Name:"+name;
//        priceMessage += "\nAdd whipped cream? " + addWhippedCream;
//        priceMessage += "\nAdd chocolate? " + addChocolate;
//        priceMessage += "\nQuantity: " + quantity;
//        priceMessage += "\nTotal: $" + price;
//        priceMessage += "\nThank you!";
//        return priceMessage;

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("*/*");
        intent.putExtra(Intent.EXTRA_SUBJECT, "just java order for"+name
                );
        intent.putExtra(Intent.EXTRA_STREAM, "this is Stream IDK what it is");
        intent.putExtra(Intent.EXTRA_TEXT,"\n Add whipped cream"+addWhippedCream+"\n Add chocoate" +
                "Quantity" + quantity+
                " \n Total Price" + price+
                " Add chocolate"+addChocolate);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }

    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int numberOfCoffees) {
        TextView quantityTextView = (TextView) findViewById(
                R.id.quantity_text_view);
        quantityTextView.setText("" + numberOfCoffees);
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }

}