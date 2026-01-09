package com.example.comp2000new;



import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class employee_add_menu_item extends AppCompatActivity {

    EditText title, description, price;
    Button save , back;
    MyDatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_menu_item);

        db = new MyDatabaseHelper(this);

        title = findViewById(R.id.add_title);
        description = findViewById(R.id.add_des);
        price = findViewById(R.id.add_price);
        save = findViewById(R.id.save);
        back = findViewById(R.id.back);

        back.setOnClickListener(v -> {
            Intent back = new Intent(employee_add_menu_item.this , employee_menu.class);
            startActivity(back);
        });

        save.setOnClickListener(v -> {
            db.addMenuItem(
                    title.getText().toString(),
                    description.getText().toString(),
                    price.getText().toString(),
                    R.drawable.placeholdee
            );
            Toast.makeText(employee_add_menu_item.this ,"\"Item added to menu!" ,Toast.LENGTH_LONG ).show();
            finish();
        });
    }
}
