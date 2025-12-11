package com.example.comp2000new;



import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class employee_add_menu_item extends AppCompatActivity {

    EditText title, description, price;
    ImageView add_image;
    Button select_image, save;
    MyDatabaseHelper db;

    String selectedImageUri = null;

    int selectedImageRes = R.drawable.cheeseburger; // fallback default


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_menu_item);

        title = findViewById(R.id.add_title);
        description = findViewById(R.id.add_des);
        price = findViewById(R.id.add_price);
        add_image = findViewById(R.id.add_image);
        select_image = findViewById(R.id.select_image);
        save = findViewById(R.id.save);

    }


}
