package com.example.comp2000new;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class employee_edit_menu_item extends AppCompatActivity {


    EditText title, description, price;
    Button save;

    MyDatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_edit_menu_item);

        db = new MyDatabaseHelper(this);

        title = findViewById(R.id.edit_title);
        description = findViewById(R.id.edit_des);
        price = findViewById(R.id.edit_price);
        save = findViewById(R.id.save);

        Intent intent = getIntent();
        String passedTitle = intent.getStringExtra("title");
        String passedDesc = intent.getStringExtra("description");
        String passedPrice = intent.getStringExtra("price");

        title.setText(passedTitle);
        description.setText(passedDesc);
        price.setText(passedPrice);

        save.setOnClickListener(v -> {
            db.updateMenuItem(title.getText().toString(),
                    title.getText().toString(),
                    description.getText().toString(),
                    price.getText().toString()
            );
            finish();
        });
    }
}
