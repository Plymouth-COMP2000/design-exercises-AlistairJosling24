package com.example.comp2000new;

import android.annotation.SuppressLint;
import android.content.Context;
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

    EditText Username;
    EditText Password;
    Button Login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_customer_login);
        Api.init(this);
        Api.get_customer(this,"ali_josling",customer -> {        // Success callback
                    Log.d("TEST", "Firstname: " + customer.getFirstname());
                    Log.d("TEST", "Lastname: " + customer.getLastname());
                },
                error -> {           // Error callback
                    Log.e("TEST", "Error: " + error.getMessage());
                }
        );



    }
}

