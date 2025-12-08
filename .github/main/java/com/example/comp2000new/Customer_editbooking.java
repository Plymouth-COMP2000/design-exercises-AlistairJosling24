package com.example.comp2000new;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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

public class Customer_editbooking extends AppCompatActivity {

    private Spinner locationSpinner;
    private Spinner timeSpinner;
    private Spinner tableSizeSpinner;
    private EditText date;


    private String username;
    private int bookingId;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bookingId = getIntent().getIntExtra("bookingId", -1);
        username = getIntent().getStringExtra("username");
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_customer_editbooking);

        MyDatabaseHelper db = new MyDatabaseHelper(this);
        SQLiteDatabase database = db.getReadableDatabase();

        Cursor c = database.query("bookings", new String[]{"date", "time", "size"}, "booking_id=?",
                new String[]{String.valueOf(bookingId)},
                null, null, null
        );

        //  Get username from previous screen
        username = getIntent().getStringExtra("username");
        Toast.makeText(this, "Username: " + username, Toast.LENGTH_SHORT).show();

        locationSpinner = findViewById(R.id.location_spinner);
        timeSpinner = findViewById(R.id.time_spinner);
        tableSizeSpinner = findViewById(R.id.table_size_spinner);
        date = findViewById(R.id.date);

        String[] locations = {"Location", "London", "Manchester", "Bristol", "Birmingham"};
        String[] times = {"Time", "1", "2"};
        String[] table_sizes = {"Table Size", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14",
                "15", "16", "17", "18", "19", "20"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, locations);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, times);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, table_sizes);


        locationSpinner.setAdapter(adapter);
        timeSpinner.setAdapter(adapter1);
        tableSizeSpinner.setAdapter(adapter2);

        Button back = findViewById(R.id.back_button);
        Button submit = findViewById(R.id.submit_button);

        back.setOnClickListener(new listener1());
        submit.setOnClickListener(new listener2());
    }

    class listener1 implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent back = new Intent(Customer_editbooking.this, Customer_homescreen.class);
            back.putExtra("username", username);
            startActivity(back);
        }
    }

    class listener2 implements View.OnClickListener{
        public void onClick (View v){
            String newLocation = locationSpinner.getSelectedItem().toString();
            String newTime = timeSpinner.getSelectedItem().toString();
            String newSize = tableSizeSpinner.getSelectedItem().toString();
            String newDate = date.getText().toString();

            MyDatabaseHelper db = new MyDatabaseHelper(Customer_editbooking.this);
            SQLiteDatabase database = db.getWritableDatabase();

            ContentValues cv = new ContentValues();
            cv.put("date", newDate);
            cv.put("time", newTime);
            cv.put("size", Integer.parseInt(newSize));

            int rows = database.update("bookings", cv, "booking_id=?",
                    new String[]{String.valueOf(bookingId)}
            );

            if (rows > 0) {
                Toast.makeText(Customer_editbooking.this, "Booking updated!", Toast.LENGTH_SHORT).show();
                String message = "Your booking for " + date + " at " + newTime + " for " + newSize + " people has been confirmed!";
                boolean notifSuccess = db.addnotification(username, message);
                if(notifSuccess) {
                    Toast.makeText(Customer_editbooking.this, "\"Your booking for " + newDate + " at " + newTime + " for " + newSize + " people has been upadated!", Toast.LENGTH_LONG).show();
                } else {
                    Log.d("DB", "Failed to add notification for " + username);
                }
            } else {
                Toast.makeText(Customer_editbooking.this, "Failed to add booking.", Toast.LENGTH_SHORT).show();
            }

                // Return to bookings page
                Intent i = new Intent(Customer_editbooking.this, customer_bookings.class);
                i.putExtra("username", username);
                startActivity(i);
            }
        }


        }


