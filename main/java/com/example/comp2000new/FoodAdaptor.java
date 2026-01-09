package com.example.comp2000new;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class FoodAdaptor extends BaseAdapter {

    private Context context;

    private List<food_item> items;    // Data Source

    public static ArrayList<payment_item> cart = new ArrayList<>();

    public FoodAdaptor(Context context, List<food_item> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {

            convertView = LayoutInflater.from(context)
                    .inflate(R.layout.activity_food_item, parent, false);

            holder = new ViewHolder();
            holder.title = convertView.findViewById(R.id.title);
            holder.description = convertView.findViewById(R.id.description);
            holder.price = convertView.findViewById(R.id.price);
            holder.image = convertView.findViewById(R.id.image);
            holder.add = convertView.findViewById(R.id.add);
            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        food_item item = items.get(position);

        holder.add.setOnClickListener(v -> {
            Toast.makeText(context ,"\"Added to basket" , Toast.LENGTH_LONG).show();
            cart.add(new payment_item(
                    item.getTitle(),
                    item.getPrice()
            ));
        });

        // Bind data
        holder.title.setText(item.getTitle());
        holder.description.setText(item.getDescription());
        holder.price.setText(item.getPrice());
        holder.image.setImageResource(item.getImage());

        return convertView;
    }

    static class ViewHolder {
        TextView title;
        TextView description;
        TextView price;

        ImageView image;
        Button add;
    }
}
