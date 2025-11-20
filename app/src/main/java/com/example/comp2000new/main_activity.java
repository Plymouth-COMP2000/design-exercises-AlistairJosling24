package com.example.comp2000new;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class main_activity extends AppCompatActivity {

    private Button employee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        employee = findViewById(R.id.staffButton);
        employee.setOnClickListener(new listener());


    }
    class listener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(main_activity.this, employee_homescreen.class);
            startActivity(intent);

        }
    }
}