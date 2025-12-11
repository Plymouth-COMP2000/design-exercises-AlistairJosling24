package com.example.comp2000new;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class employee_edit_menu_item extends AppCompatActivity {

    EditText title, description, price;
    Button save;
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

        save.setOnClickListener(v -> {
            db.addMenuItem(
                    title.getText().toString(),
                    description.getText().toString(),
                    price.getText().toString(),
                    R.drawable.cheeseburger   // default image
            );
            finish();
        });
    }
}
