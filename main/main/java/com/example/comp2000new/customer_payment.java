package com.example.comp2000new;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;




public class customer_payment extends AppCompatActivity {

    ArrayList<payment_item> Selected;
    ListView listview;
    PaymentAdaptor adapter;
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        username = getIntent().getStringExtra("username");
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_customer_payment);
        listview = findViewById(R.id.listview);


        Selected = FoodAdaptor.cart;
        adapter = new PaymentAdaptor(this, Selected);
        listview.setAdapter(adapter);

        double total = 0;
        for (payment_item item : Selected) {
            total += item.getActualPrice();
        }

        Button back = findViewById(R.id.back);
        back.setOnClickListener(new listener1());


        Button pay = findViewById(R.id.pay);
        pay.setOnClickListener(new listener2());

        TextView total_price = findViewById(R.id.total_price);
        total_price.setText("Total: £" + String.format("%.2f", total));


    }

    private String buildItemsString(ArrayList<payment_item> items) {
        StringBuilder sb = new StringBuilder();

        for (payment_item item : items) {
            sb.append(item.getTitle())
                    .append(" (")
                    .append(item.getPrice())
                    .append("), ");
        }

        if (sb.length() > 2) {
            sb.setLength(sb.length() - 2); // remove last ", "
        }

        return sb.toString();
    }

    private String saveOrderToDatabase() {

        MyDatabaseHelper db = new MyDatabaseHelper(customer_payment.this);

        // Build items string
        StringBuilder sb = new StringBuilder();
        for (payment_item item : Selected) {
            sb.append(item.getTitle())
                    .append(" (")
                    .append(item.getPrice())
                    .append("), ");
        }
        if (sb.length() > 2) sb.setLength(sb.length() - 2);

        String itemsString = sb.toString();

        // Calculate total price
        double total = 0;
        for (payment_item item : Selected) {
            total += Double.parseDouble(item.getPrice().replace("£", ""));
        }

        boolean success = db.addOrder(username, itemsString, total);

        return success ? itemsString : null;   // return order text if success
    }


    class listener1 implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            Intent back = new Intent(customer_payment.this, customer_menu.class);
            back.putExtra("username", username);
            startActivity(back);

        }
    }

    class listener2 implements View.OnClickListener {


        @Override
        public void onClick(View v) {
            EditText tablenum;
            String orderText = saveOrderToDatabase();
            tablenum = findViewById(R.id.table_num);
            SharedPreferences prefs = getSharedPreferences("customer_settings", MODE_PRIVATE);
            boolean enabled = prefs.getBoolean(username + "_notif", true);

            if (orderText != null) {

                String table = tablenum.getText().toString().trim();

                String message = "Order for table " + table + " has been confirmed. Items: " + orderText;

                MyDatabaseHelper db = new MyDatabaseHelper(customer_payment.this);
                db.addnotification(username, message);
                if (enabled){
                    Toast.makeText(customer_payment.this, "Order added!", Toast.LENGTH_SHORT).show();
                }



            } else {
                Toast.makeText(customer_payment.this, "Failed to add order.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}

