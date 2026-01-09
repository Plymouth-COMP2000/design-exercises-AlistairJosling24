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

    private EditText specialRequests;


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
        specialRequests = findViewById(R.id.special_requests);

        String[] locations = {"Location", "London", "Manchester", "Bristol", "Birmingham"};
        String[] times = {"Time", "10am", "11am" , "12pm" ,"1pm" , "2pm" , "3pm", "4pm", "5pm" , "6pm" , "7pm" , "8pm"};
        String[] table_sizes = {"Table Size", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14",
                "15", "16", "17", "18", "19", "20"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, locations);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, times);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, table_sizes);


        locationSpinner.setAdapter(adapter);
        timeSpinner.setAdapter(adapter1);
        tableSizeSpinner.setAdapter(adapter2);

        Button submit = findViewById(R.id.submit_button);
        submit.setOnClickListener(new listener2());
    }



    class listener2 implements View.OnClickListener{
        public void onClick (View v){
            String newDate = date.getText().toString().trim();
            String newTime = timeSpinner.getSelectedItem().toString();
            String newLocation = locationSpinner.getSelectedItem().toString();
            String newSizeStr = tableSizeSpinner.getSelectedItem().toString();
            String special = specialRequests.getText().toString().trim();

            if (newDate.isEmpty()) {
                date.setError("Date is required");
                date.requestFocus();
                return;
            }

            if (newTime.equals("Time")) {
                Toast.makeText(Customer_editbooking.this,
                        "Please select a time", Toast.LENGTH_SHORT).show();
                return;
            }

            if (newLocation.equals("Location")) {
                Toast.makeText(Customer_editbooking.this,
                        "Please select a location", Toast.LENGTH_SHORT).show();
                return;
            }

            if (newSizeStr.equals("Table Size")) {
                Toast.makeText(Customer_editbooking.this,
                        "Please select a table size", Toast.LENGTH_SHORT).show();
                return;
            }





            MyDatabaseHelper db = new MyDatabaseHelper(Customer_editbooking.this);
            SQLiteDatabase database = db.getWritableDatabase();


            ContentValues cv = new ContentValues();
            cv.put("date", newDate);
            cv.put("time", newTime);
            cv.put("size", Integer.parseInt(newSizeStr));

            int rows = database.update("bookings", cv, "booking_id=?",
                    new String[]{String.valueOf(bookingId)}
            );

            if (rows > 0) {
                Toast.makeText(Customer_editbooking.this, "Booking updated!", Toast.LENGTH_SHORT).show();
                String message =
                                "UPDATED\n" +
                                " Location: " + newLocation + "\n" +
                                " Date: " + newDate + "\n" +
                                " Time: " + newTime + "\n" +
                                " Table for: " + newSizeStr+
                                special;
                boolean notifSuccess = db.addnotification(username, message);
                if(notifSuccess) {
                    Toast.makeText(Customer_editbooking.this, "\"Your booking for " + newDate + " at " + newTime + " for " + newSizeStr + " people has been upadated!", Toast.LENGTH_LONG).show();
                } else {
                    Log.d("DB", "Failed to add notification for " + username);
                }
            } else {
                Toast.makeText(Customer_editbooking.this, "Failed to add booking.", Toast.LENGTH_SHORT).show();
            }

                // Return to bookings page
                finish();
            }
        }


        }


