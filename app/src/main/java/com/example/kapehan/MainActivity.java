package com.example.kapehan;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
//import android.support.v7.app.ActionBarActivity;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

/** * This app displays an order form to order coffee. */

public class MainActivity extends AppCompatActivity {

    int c_quantity = 0;
    int d_quantity = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    // COFFEE
    public void coffee_decrement(View view) {
        if (c_quantity == 0) {
            return;
        }
        c_quantity--;
        displayQuantity_c(c_quantity);
    }

    public void coffee_increment(View view) {

        c_quantity++;
        displayQuantity_c(c_quantity);
    }




    //DESSERT
    public void dessert_decrement(View view) {
        if (d_quantity == 0) {
            return;
        }
        d_quantity--;
        displayQuantity_d(d_quantity);
    }

    public void dessert_increment(View view) {
        d_quantity++;
        displayQuantity_d(d_quantity);
    }












    //BUTTON
    public void submitOrder(View view) {

        //Find the user's name
        EditText nameField = (EditText) findViewById(R.id.name_field);
        String value = nameField.getText().toString();
        //Figure out if the user wants Espresso
        CheckBox espressoCheckBox = (CheckBox) findViewById(R.id.espresso_checkbox);
        boolean hasEspresso = espressoCheckBox.isChecked();
        //Figure out if the user wants Cappuccino
        CheckBox cappuccinoCheckBox = (CheckBox) findViewById(R.id.cappuccino_checkbox);
        boolean hasCappuccino = cappuccinoCheckBox.isChecked();
        //Figure out if the user wants Cheese Cake
        CheckBox cheeseCheckBox = (CheckBox) findViewById(R.id.cheese_checkbox);
        boolean hasCheese = cheeseCheckBox.isChecked();
        //Figure out if the user wants Ube Halaya
        CheckBox ubeCheckBox = (CheckBox) findViewById(R.id.ube_checkbox);
        boolean hasUbe = ubeCheckBox.isChecked();


        int price = calculatePrice(hasEspresso, hasCappuccino, hasCheese, hasUbe);
        String priceMessage = createOrderSummary(value,price, price, hasEspresso, hasCappuccino, hasCheese, hasUbe);
        displayMessage(priceMessage);
        int price2 = calculatePrice2(hasEspresso, hasCappuccino, hasCheese, hasUbe);
        String priceMessage2 = createOrderSummary(value,price, price2, hasEspresso, hasCappuccino, hasCheese, hasUbe);
        displayMessage(priceMessage2);
    }

    private int calculatePrice2(boolean addEspresso, boolean addCappuccino, boolean addCheese, boolean addUbe) {
        int  basePrice = 0;

        if (addCheese) {
            basePrice = basePrice+ 89;

        }

        //ube halaya
        if (addUbe) {
            basePrice = basePrice+ 59;

        }
        return d_quantity*basePrice;
    }
    /**  * This method calculates the total price.  */
    private int calculatePrice(boolean addEspresso, boolean addCappuccino, boolean addCheese, boolean addUbe)
    {  //Price of 1 cup of coffee
        int basePrice = 0;
        // espresso
        if (addEspresso) {
            basePrice = basePrice + 110;

        }

        // cappuccino
        if (addCappuccino)
        {  basePrice = basePrice + 129;

        }

        // Calculate the total order price
        return c_quantity*basePrice;
    }

private String createOrderSummary(String name, int price2 , int price, boolean addEspresso, boolean addCappuccino, boolean addCheese, boolean addUbe){

    String priceMessage = "Name:Mr./Ms. " + name;
    if (addEspresso == false & addCappuccino == false & addCheese == false & addUbe == false) {

        priceMessage += "\nWhat's your Order?";
    }
    else {

        priceMessage += "\nYou Ordered";
    }

    if(addEspresso == true) {
        priceMessage += "\nEspresso    ₱110" + " x " + c_quantity;
    }
    if (addCappuccino == true) {
        priceMessage += "\nCappucino   ₱129" + " x " + c_quantity;
    }

    if (addCheese == true) {
         priceMessage += "\nCheese Cake ₱89" + " x " + d_quantity;

    }
    if (addUbe == true){
        priceMessage += "\nUbe Halaya ₱59" + " x " + d_quantity;
    }
    priceMessage += "\nTotal Price: ₱" + (price+price2);
    priceMessage += "\nThank you!";
    return priceMessage;


}

    /**  * This method displays the given quantity value on the screen.  */
    private void displayQuantity_c(int numberOfCoffees)
    {  TextView quantityTextView = (TextView) findViewById(  R.id.coffee_quantity);
    quantityTextView.setText("" + numberOfCoffees);
    }

    private void displayQuantity_d(int numberOfDessert)
    {  TextView quantityTextView = (TextView) findViewById(  R.id.dessert_quantity);
        quantityTextView.setText("" + numberOfDessert);
    }



    private void displayMessage(String message) {
        TextView OrderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        OrderSummaryTextView.setText(message);
    }
}