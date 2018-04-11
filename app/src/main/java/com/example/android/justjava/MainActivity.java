/**
 * IMPORTANT: Make sure you are using the correct package name.
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/

package com.example.android.justjava;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.android.justjava.R.id.check_chocolate;
import static com.example.android.justjava.R.id.check_whippedcream;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    int quantity = 2;
    boolean hasWhippedCream = false , hasChocolate = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */


    public void checkedStateWhippedCream(View view) {
        CheckBox checkBox = (CheckBox) findViewById(check_whippedcream);
        if (checkBox.isChecked()) {
            hasWhippedCream = true;
        } else hasWhippedCream = false;
    }
    public void checkedStateChocolate(View view){
        CheckBox checkBox = (CheckBox)findViewById(check_chocolate);
        if(checkBox.isChecked())
            hasChocolate = true;
        else
            hasChocolate = false;
    }
    public void submitOrder(View view) {


        int price = calculatePrice();
        EditText showName = (EditText)findViewById(R.id.name_edit_text);
        String name = showName.getText().toString();
        String priceMessage = createOrderSummary(price , name);

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setData(Uri.parse("mail to:"));
        intent.putExtra(Intent.EXTRA_SUBJECT , "coffee ordered bill");
        intent.putExtra(Intent.EXTRA_TEXT , priceMessage);
        intent.setType("message/rfc822");
        Intent chooser = Intent.createChooser(intent , "send email");
        startActivity(chooser);


    }



    private int calculatePrice() {
        int price = 5;
        if(hasWhippedCream == true)price++;
        if(hasChocolate == true)price += 2;
        price = price * quantity;
        return price;
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    public void increment(View View) {
        if(quantity==100){
            Toast.makeText(this , "You cannot have  more than 100 coffees " , Toast.LENGTH_SHORT).show();
            return;
        }
        quantity++;
        display(quantity);
    }

    public void decrement(View View) {
        if(quantity == 1){
            Toast.makeText(this , "You cannot have less than 1 cup of coffee" , Toast.LENGTH_SHORT).show();
            return ;
        }
        quantity--;
        display(quantity);
    }

    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    private String createOrderSummary(int price , String name) {
        String summary = getString(R.string.order_summary_name , name ) + "\n" +
                getString(R.string.order_summary_whipped_cream , hasWhippedCream) + "\n" +
                getString(R.string.order_summary_chocolate , hasChocolate) + "\n" +
                getString(R.string.order_summary_quantity , quantity) + "\n" +
                getString(R.string.order_summary_price , price) + "\n" +
                getString(R.string.thank_you);
        return summary;
    }

//    /**
//     * This method displays the given text on the screen.
//     */
//    private void displayMessage(String message) {
//        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
//        orderSummaryTextView.setText(message);
//    }

}