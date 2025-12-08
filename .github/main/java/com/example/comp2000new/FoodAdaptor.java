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

import java.util.List;


public class FoodAdaptor extends BaseAdapter {

    private Context context;

    private List<food_item> items ;    // Data Source

    public FoodAdaptor(Context context, List<food_item> items) {
        this.context = context;
        this.items = items;

    }

    @Override
    public int getCount() {
        return items.size();  // Returns the number of items in your data source
    }

    @Override
    public Object getItem(int position) {
        return items.get(position); // Returns the data item at the given position
    }

    @Override
    public long getItemId(int position) {
        return position; // Returns a unique Identifier for the item at the given position
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;



        if (convertView == null){
            // convertView: is a recycled View that you can reuse to
            //              improve the performance of your list.
            convertView= LayoutInflater.from(context)
                    .inflate(R.layout.activity_food_item, parent, false);

            holder = new ViewHolder();
            holder.title = convertView.findViewById(R.id.title);
            holder.description = convertView.findViewById(R.id.description);
            holder.price = convertView.findViewById(R.id.price);
            holder.image = convertView.findViewById(R.id.image);
            convertView.setTag(holder);
        }else{
            // Reusing the View (that's recycled)
            holder = (ViewHolder) convertView.getTag();
        }


        food_item item = items.get(position);

        // Bind data
        holder.title.setText(item.getTitle());
        holder.description.setText(item.getDescription());
        holder.price.setText(item.getPrice());
        holder.image.setImageResource(item.getImage());


        Button add = convertView.findViewById(R.id.add);






        return convertView;

    }

    static class ViewHolder{
        TextView title;
        // Holds references to the
        // views within an item layout
        TextView description;
        TextView price;

        ImageView image;


    }
}