package com.example.comp2000new;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import androidx.appcompat.app.AppCompatActivity;

public class Settings extends AppCompatActivity {

    String username;
    Button back;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Button back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backIntent = new Intent(Settings.this, Customer_homescreen.class);
                backIntent.putExtra("username", username);
                startActivity(backIntent);
            }
        });

        username = getIntent().getStringExtra("username");
        Switch notifications = findViewById(R.id.notification);

        // Load their personal setting
        notifications.setChecked(loadSetting(username));

        // When switched, save JUST for this customer
        notifications.setOnCheckedChangeListener((buttonView, isChecked) -> {
            saveSetting(username, isChecked);
        });
    }

    private boolean loadSetting(String user) {
        SharedPreferences prefs = getSharedPreferences("customer_settings", MODE_PRIVATE);
        return prefs.getBoolean(user + "_notif", true); // enabled by default
    }

    private void saveSetting(String user, boolean enabled) {
        SharedPreferences prefs = getSharedPreferences("customer_settings", MODE_PRIVATE);
        prefs.edit().putBoolean(user + "_notif", enabled).apply();
    }


}