package com.example.comp2000new;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;



public class employee_homescreen extends AppCompatActivity {

    private Button reservations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_employee_homescreen);
        reservations = findViewById(R.id.ReservationsButton);
        reservations.setOnClickListener(new listener());


    }
    class listener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(employee_homescreen.this, employee_bookings.class);
            startActivity(intent);

        }
    }
}
