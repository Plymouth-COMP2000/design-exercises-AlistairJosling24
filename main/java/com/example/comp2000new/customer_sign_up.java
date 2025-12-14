package com.example.comp2000new;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class customer_sign_up extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_customer_sign_up);
        Api.init(this);

        EditText Username = findViewById(R.id.username);
        EditText Password = findViewById(R.id.password);
        EditText Firstname = findViewById(R.id.firstname);
        EditText Lastname = findViewById(R.id.lastname);
        EditText Email = findViewById(R.id.email);
        EditText Contact = findViewById(R.id.contact);

        Button SignUp = findViewById(R.id.sign_up);

        SignUp.setOnClickListener(v -> {

            String u = Username.getText().toString().trim();
            String p = Password.getText().toString().trim();
            String f = Firstname.getText().toString().trim();
            String l = Lastname.getText().toString().trim();
            String e = Email.getText().toString().trim();
            String c = Contact.getText().toString().trim();

            if (u.isEmpty() || p.isEmpty() || f.isEmpty() || l.isEmpty() || e.isEmpty() || c.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            Api.create_user(
                    this,
                    u, p, f, l, e, c, "Customer",
                    response -> {
                        Toast.makeText(this, "Account created!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(customer_sign_up.this, customer_login.class);
                        startActivity(intent);
                    },
                    error -> {
                        Toast.makeText(this, "Sign-up failed. Try again.", Toast.LENGTH_SHORT).show();
                    }
            );
        });
    }
}
