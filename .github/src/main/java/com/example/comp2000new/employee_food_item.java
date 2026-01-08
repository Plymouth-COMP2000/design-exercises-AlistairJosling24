package com.example.comp2000new;

import android.os.Bundle;
import android.util.EventLogTags;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class employee_food_item {
    private String Title;
    private String Description;

    private String Price;
    private int Image;



    public employee_food_item(String Title , String Description , String Price , int Image){
        this.Title = Title;
        this.Description = Description;
        this.Price = Price;
        this.Image = Image;
    }

    public String getTitle() { return Title; }
    public String getDescription() { return Description; }
    public String getPrice() { return Price; }
    public int getImage() { return Image; }
}
