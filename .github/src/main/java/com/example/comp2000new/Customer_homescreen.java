package com.example.comp2000new;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Customer_homescreen extends AppCompatActivity {
    private String username;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        Button menu;
        Button notifications;
        Button bookings;
        Button mybookings;
        ImageButton settings;


        setContentView(R.layout.activity_customer_homescreen);
        username = getIntent().getStringExtra("username");
        menu = findViewById(R.id.MenuButton);
        menu.setOnClickListener(new listener1());

        notifications = findViewById(R.id.NotificationsButton);
        notifications.setOnClickListener(new listener2());

        bookings = findViewById(R.id.ReservationsButton);
        bookings.setOnClickListener(new listener3());

        mybookings = findViewById(R.id.existing_bookingsButton);
        mybookings.setOnClickListener(new listener4());

        settings = findViewById(R.id.settings_button);
        settings.setOnClickListener(new listener5());





    }
    class listener1 implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            Intent menu = new Intent(Customer_homescreen.this, customer_menu.class);
            menu.putExtra("username", username);
            startActivity(menu);

        }
    }



    class listener2 implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            Intent notifications = new Intent(Customer_homescreen.this, customer_notifications.class);
            notifications.putExtra("username", username);
            startActivity(notifications);

        }
    }

    class listener3 implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            Intent bookings = new Intent(Customer_homescreen.this, customer_make_a_booking.class);
            bookings.putExtra("username", username);
            startActivity(bookings);

        }
    }

    class listener4 implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            Intent mybookings = new Intent(Customer_homescreen.this, customer_bookings.class);
            mybookings.putExtra("username", username);
            startActivity(mybookings);

        }
    }

    class listener5 implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            Intent settings = new Intent(Customer_homescreen.this, Settings.class);
            settings.putExtra("username", username);
            startActivity(settings);

        }
    }
}
