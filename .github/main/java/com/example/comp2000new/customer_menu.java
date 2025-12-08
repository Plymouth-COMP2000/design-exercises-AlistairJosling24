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


public class customer_menu extends AppCompatActivity {
    private String username;
    ArrayList<food_item> Menu;
    ListView listview;
    FoodAdaptor adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_customer_menu);
        username = getIntent().getStringExtra("username");
        listview = findViewById(R.id.list_view);

        Button back;

        back = findViewById(R.id.back_button);
        back.setOnClickListener(new listener1());



        Menu = new ArrayList<>();

        Menu.add(new food_item(
                "Cheeseburger", "Juicy double smashburger packed with flavour. Onions , homemade burger" +
                "sauce, pickles , tomatoe , lettuce , bacon and cheddar cheese." , "£5.50" , R.drawable.cheeseburger
        ));

        Menu.add(new food_item(
                "Pizza", "A classic triple cheese pizza packed with flavour! " +
                "With cheese, cheese and more cheese! Also having a tomatoe base " , "£5.50" , R.drawable.pizza
        ));

        adapter = new FoodAdaptor(this, Menu);
        listview.setAdapter(adapter);
    }

    class listener1 implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            Intent back = new Intent(customer_menu.this, Customer_homescreen.class);
            back.putExtra("username", username);
            startActivity(back);

        }
    }

}