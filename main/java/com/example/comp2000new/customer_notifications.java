package com.example.comp2000new;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class customer_notifications extends AppCompatActivity {
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_customer_notifications);
        username = getIntent().getStringExtra("username");

        Button back;

        back = findViewById(R.id.back_button);
        back.setOnClickListener(new listener1());
    }

    class listener1 implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            Intent back = new Intent(customer_notifications.this, Customer_homescreen.class);
            back.putExtra("username",  username);
            startActivity(back);

        }
    }

}
