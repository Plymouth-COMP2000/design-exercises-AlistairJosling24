package com.example.comp2000new;

import android.content.Intent;
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

public class employee_notifications extends AppCompatActivity {
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_customer_notifications);

        username = getIntent().getStringExtra("username");

        MyDatabaseHelper db = new MyDatabaseHelper(this);
        ArrayList<String> allMessages = new ArrayList<>();

        // ---------------- READ NOTIFICATIONS ----------------
        Cursor cursor = db.getReadableDatabase().query(
                "notifications",
                new String[]{"text", "created_at"},
                null, null, null, null,
                "created_at DESC"
        );

        if (cursor.moveToFirst()) {
            do {
                String msg = cursor.getString(cursor.getColumnIndexOrThrow("text"));
                allMessages.add("NOTIFICATION: " + msg);
            } while (cursor.moveToNext());
        }
        cursor.close();


        // ---------------- DISPLAY LIST ----------------
        ListView listView = findViewById(R.id.notification_list_view);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                R.layout.notification_item,
                R.id.notification_text,
                allMessages
        );
        listView.setAdapter(adapter);


        // ---------------- BACK BUTTON ----------------
        Button back = findViewById(R.id.back_button);
        back.setOnClickListener(v -> {
            Intent backIntent = new Intent(employee_notifications.this, employee_homescreen.class);
            backIntent.putExtra("username", username);
            startActivity(backIntent);
        });
    }
}
