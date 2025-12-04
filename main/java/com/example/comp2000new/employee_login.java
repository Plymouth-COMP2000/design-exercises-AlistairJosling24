package com.example.comp2000new;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class employee_login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_employee_login);
        Api.init(this);


        EditText Username = findViewById(R.id.username);
        EditText Password = findViewById(R.id.password);
        Button Login = findViewById(R.id.login);

        Login.setOnClickListener(v -> {
            String enteredUser = Username.getText().toString().trim();
            String enteredPass = Password.getText().toString().trim();

            // NOW call get_customer
            Api.get_customer(
                    this,
                    enteredUser,
                    customer -> {
                        if (customer.getPassword().equals(enteredPass)) {
                            if (!customer.getUsertype().equalsIgnoreCase("Staff")) {
                                Toast.makeText(this, "Only Staff accounts can log in here.", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            Toast.makeText(this, "Login successful!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(employee_login.this, employee_homescreen.class);
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

    }
}