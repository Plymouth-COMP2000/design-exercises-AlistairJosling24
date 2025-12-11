package com.example.comp2000new;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;


public class employee_menu extends AppCompatActivity {
    private String username;
    ArrayList<food_item> Menu;
    ListView listview;
    employee_FoodAdaptor adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_employee_menu);
        username = getIntent().getStringExtra("username");
        listview = findViewById(R.id.list_view);

        Button back;
        Button add;

        back = findViewById(R.id.back_button);
        add = findViewById(R.id.Add);

        back.setOnClickListener(new listener1());



        MyDatabaseHelper db = new MyDatabaseHelper(this);
        Menu = db.getMenuItems();  // load from DB
        adapter = new employee_FoodAdaptor(this, Menu, db);
        listview.setAdapter(adapter);
    }


    class listener1 implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            Intent back = new Intent(employee_menu.this, employee_homescreen.class);
            back.putExtra("username", username);
            startActivity(back);

        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Reload menu items from database
        MyDatabaseHelper db = new MyDatabaseHelper(this);
        Menu.clear();
        Menu.addAll(db.getMenuItems());
        adapter.notifyDataSetChanged();
    }

}