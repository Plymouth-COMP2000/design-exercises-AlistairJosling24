package com.example.comp2000new;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class customer_notifications extends AppCompatActivity {
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_customer_notifications);
        username = getIntent().getStringExtra("username");

        SharedPreferences prefs = getSharedPreferences("customer_settings", MODE_PRIVATE);
        boolean enabled = prefs.getBoolean(username + "_notif", true);

        if (enabled) {
            MyDatabaseHelper db = new MyDatabaseHelper(this);
            Cursor cursor = db.getReadableDatabase().query(
                    "notifications",
                    new String[]{"text", "created_at"},
                    "username = ?",
                    new String[]{username},
                    null, null,
                    "created_at DESC"
            );

            ArrayList<String> notifications = new ArrayList<>();
            if (cursor.moveToFirst()) {
                do {
                    String msg = cursor.getString(cursor.getColumnIndexOrThrow("text"));
                    notifications.add(msg);
                } while (cursor.moveToNext());
            }
            cursor.close();


            ListView listView = findViewById(R.id.notification_list_view);


// Display in ListView
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.notification_item,
                    R.id.notification_text, notifications);
            listView.setAdapter(adapter);
        }

        Button back;

        back = findViewById(R.id.back_button);

        back.setOnClickListener(new listener1());
    }
        class listener1 implements View.OnClickListener {

            @Override
            public void onClick(View v) {
                Intent back = new Intent(customer_notifications.this, Customer_homescreen.class);
                back.putExtra("username", username);
                startActivity(back);

            }
        }


    }
