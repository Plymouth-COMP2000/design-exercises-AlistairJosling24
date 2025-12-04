package com.example.comp2000new;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;



public class employee_homescreen extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        Button menu;
        Button notifications;
        Button bookings;


        setContentView(R.layout.activity_employee_homescreen);
        menu = findViewById(R.id.MenuButton);
        menu.setOnClickListener(new listener1());

        notifications = findViewById(R.id.NotificationsButton);
        notifications.setOnClickListener(new listener2());

        bookings = findViewById(R.id.ReservationsButton);
        bookings.setOnClickListener(new listener3());





    }
    class listener1 implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            Intent menu = new Intent(employee_homescreen.this, employee_menu.class);
            startActivity(menu);

        }
    }



    class listener2 implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            Intent notifications = new Intent(employee_homescreen.this, employee_notifications.class);
            startActivity(notifications);

        }
    }

class listener3 implements View.OnClickListener {

    @Override
    public void onClick(View v) {
        Intent bookings = new Intent(employee_homescreen.this, employee_bookings.class);
        startActivity(bookings);

    }
}
}
