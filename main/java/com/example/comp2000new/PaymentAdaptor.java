package com.example.comp2000new;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;


public class PaymentAdaptor extends BaseAdapter {

    private Context context;

    private List<payment_item> items ;    // Data Source




    private customer_payment paymentActivity;

    public PaymentAdaptor(customer_payment activity, List<payment_item> items) {
        this.paymentActivity = activity;
        this.context = activity;
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
            //              improve the performance of your list
            convertView= LayoutInflater.from(context)
                    .inflate(R.layout.activity_payment_item, parent, false);

            holder = new ViewHolder();
            holder.title = convertView.findViewById(R.id.title);
            holder.price = convertView.findViewById(R.id.price);
            holder.delete = convertView.findViewById(R.id.delete);
            convertView.setTag(holder);

        }else{
            holder = (ViewHolder) convertView.getTag();
        }




        payment_item item = items.get(position);

        // Bind data
        holder.title.setText(item.getTitle());
        holder.price.setText(item.getPrice());

        holder.delete.setOnClickListener(v ->{
            items.remove(position);
            paymentActivity.updateTotal();
            Toast.makeText(context, "\"Item Deleted" , Toast.LENGTH_LONG).show();

            notifyDataSetChanged();
        });








        return convertView;

    }

    static class ViewHolder{
        TextView title;

        TextView price;

        ImageButton delete;





    }
}