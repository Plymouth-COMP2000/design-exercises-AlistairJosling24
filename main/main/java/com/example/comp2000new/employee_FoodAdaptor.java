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

public class employee_FoodAdaptor extends BaseAdapter {

    private Context context;
    private List<food_item> items;
    private MyDatabaseHelper db;

    public employee_FoodAdaptor(Context context, List<food_item> items, MyDatabaseHelper db) {
        this.context = context;
        this.items = items;
        this.db = db;
    }

    @Override
    public int getCount() { return items.size(); }

    @Override
    public Object getItem(int position) { return items.get(position); }

    @Override
    public long getItemId(int position) { return position; }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(context)
                    .inflate(R.layout.activity_employee_food_item, parent, false);

            holder = new ViewHolder();
            holder.title = convertView.findViewById(R.id.title);
            holder.description = convertView.findViewById(R.id.description);
            holder.price = convertView.findViewById(R.id.price);
            holder.image = convertView.findViewById(R.id.image);
            holder.delete = convertView.findViewById(R.id.Delete);
            holder.edit = convertView.findViewById(R.id.Edit);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        food_item item = items.get(position);

        holder.title.setText(item.getTitle());
        holder.description.setText(item.getDescription());
        holder.price.setText(item.getPrice());
        holder.image.setImageResource(item.getImage());

        holder.delete.setOnClickListener(v -> {
            db.deleteMenuItem(item.getTitle());
            items.remove(position);
            notifyDataSetChanged();
        });

        holder.edit.setOnClickListener(v -> {
            Intent i = new Intent(context, employee_edit_menu_item.class);
            i.putExtra("title", item.getTitle());
            i.putExtra("description", item.getDescription());
            i.putExtra("price", item.getPrice());
            i.putExtra("image", item.getImage());
            context.startActivity(i);
        });

        return convertView;
    }

    static class ViewHolder {
        TextView title, description, price;
        ImageView image;
        Button delete, edit;
    }
}
