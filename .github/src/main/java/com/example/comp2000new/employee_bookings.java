package com.example.comp2000new;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class employee_bookings extends AppCompatActivity {
    private String username;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_employee_bookings);
        username = getIntent().getStringExtra("username");

        // Load bookings from database
        MyDatabaseHelper db = new MyDatabaseHelper(this);
        ArrayList<Booking> bookings = new ArrayList<>();


        Cursor cursor = db.getReadableDatabase().query(
                "bookings",
                new String[]{"booking_id","date", "time", "size"},
                null,
                null,
                null, null,
                null
        );

        while (cursor.moveToNext()) {
            String date = cursor.getString(cursor.getColumnIndexOrThrow("date"));
            String time = cursor.getString(cursor.getColumnIndexOrThrow("time"));
            int size = cursor.getInt(cursor.getColumnIndexOrThrow("size"));
            int id = cursor.getInt(cursor.getColumnIndexOrThrow("booking_id"));
            bookings.add(new Booking(date, time, size, id));
        }
        cursor.close();

        // Attach adapter to ListView
        ListView listView = findViewById(R.id.booking_list);
        BookingAdaptor adapter = new BookingAdaptor(this, bookings, username);
        listView.setAdapter(adapter);

        // Back button listener
        Button back = findViewById(R.id.back_button);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backIntent = new Intent(employee_bookings.this, employee_homescreen.class);
                backIntent.putExtra("username", username);
                startActivity(backIntent);
            }
        });
    }




}