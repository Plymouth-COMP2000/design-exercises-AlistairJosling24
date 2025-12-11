package com.example.comp2000new;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Response;


public class customer_login extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_customer_login);
        Api.init(this);


        EditText Username = findViewById(R.id.username);
        EditText Password = findViewById(R.id.password);
        Button Login = findViewById(R.id.login);
        Button Sign_up = findViewById(R.id.sign_up);





        
        Login.setOnClickListener(v -> {
            String enteredUser = Username.getText().toString().trim();
            String enteredPass = Password.getText().toString().trim();


            // NOW call get_customer
            Api.get_customer(
                    this,
                    enteredUser,
                    customer -> {
                        if (customer.getPassword().equals(enteredPass)) {
                            if (!customer.getUsertype().equalsIgnoreCase("Customer")) {
                                Toast.makeText(this, "Only customer accounts can log in here.", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            Toast.makeText(this, "Login successful!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(customer_login.this, Customer_homescreen.class);
                            intent.putExtra("username", customer.getUsername()); // OPTIONAL
                            startActivity(intent);
                        } else {
                            Toast.makeText(this, "Incorrect password", Toast.LENGTH_SHORT).show();
                        }
                    },
                    error -> {
                        Toast.makeText(this, "User not found", Toast.LENGTH_SHORT).show();
                    }
            );
        });

        Sign_up.setOnClickListener(v -> {
            Intent intent = new Intent(customer_login.this, customer_sign_up.class);
            startActivity(intent);
        });



    }
}

