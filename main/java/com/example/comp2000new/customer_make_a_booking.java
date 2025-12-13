package com.example.comp2000new;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class customer_make_a_booking extends AppCompatActivity {

    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_customer_make_abooking);

        //  Get username from previous screen
        username = getIntent().getStringExtra("username");
        Toast.makeText(this, "Username: " + username, Toast.LENGTH_SHORT).show();

        Spinner locationSpinner = findViewById(R.id.location_spinner);
        Spinner timespinner = findViewById(R.id.time_spinner);
        Spinner table_size_spinner = findViewById(R.id.table_size_spinner);

        String[] locations = {"Location", "London", "Manchester", "Bristol", "Birmingham"};
        String[] times = {"Time", "1", "2"};
        String[] table_sizes = {"Table Size" , "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" , "11" , "12" , "13" , "14",
                "15", "16", "17", "18" , "19" , "20"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, locations);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, times);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, table_sizes);

        locationSpinner.setAdapter(adapter);
        timespinner.setAdapter(adapter1);
        table_size_spinner.setAdapter(adapter2);

        Button back = findViewById(R.id.back_button);
        Button submit = findViewById(R.id.submit_button);

        back.setOnClickListener(new listener1());
        submit.setOnClickListener(new listener2());
    }

    class listener1 implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent back = new Intent(customer_make_a_booking.this, Customer_homescreen.class);
            back.putExtra("username", username);
            startActivity(back);
        }
    }

    class listener2 implements View.OnClickListener {

        @Override
        public void onClick(View v) {

            // Get fields
            EditText dateField = findViewById(R.id.date);
            Spinner timeSpinner = findViewById(R.id.time_spinner);
            Spinner sizeSpinner = findViewById(R.id.table_size_spinner);

            // Extract values
            String date = dateField.getText().toString().trim();
            String time = timeSpinner.getSelectedItem().toString();
            int size = Integer.parseInt(sizeSpinner.getSelectedItem().toString());
            SharedPreferences prefs = getSharedPreferences("customer_settings", MODE_PRIVATE);
            boolean enabled = prefs.getBoolean(username + "_notif", true);


            MyDatabaseHelper db = new MyDatabaseHelper(customer_make_a_booking.this);

            boolean success = db.addbooking(username, date, time, size);

            if (success) {
                Toast.makeText(customer_make_a_booking.this, "Booking added!", Toast.LENGTH_SHORT).show();
                String message = "Booking for " + date + " at " + time + " for " + size + " people has been confirmed!";
                boolean notifSuccess = db.addnotification(username, message);
                if(enabled){
                    if(notifSuccess) {
                        Toast.makeText(customer_make_a_booking.this, "\"Booking added", Toast.LENGTH_LONG).show();
                    } else {
                        Log.d("DB", "Failed to add notification for " + username);}
                }
            } else {
                Toast.makeText(customer_make_a_booking.this, "Failed to add booking.", Toast.LENGTH_SHORT).show();
            }

        }
    }
}
