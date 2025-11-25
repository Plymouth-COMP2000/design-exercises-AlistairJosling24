package com.example.comp2000new;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class main_activity extends AppCompatActivity {

    private Button employee;
    private Button customer;

    String url = "http://10.240.72.69/comp2000/coursework/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        employee = findViewById(R.id.staffButton);
        employee.setOnClickListener(new listener1());

        customer = findViewById(R.id.CustomerButton);
        customer.setOnClickListener(new listener2());


    }
    class listener1 implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            Intent staff = new Intent(main_activity.this, employee_login.class);
            startActivity(staff);

        }
    }

    class listener2 implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            Intent customer = new Intent(main_activity.this, customer_login.class);
            startActivity(customer);

        }
    }
}