package com.example.comp2000new;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;


public class BookingAdaptor extends BaseAdapter {

    private Context context;
    private String username;
    private List<Booking> items ;    // Data Source

    public BookingAdaptor(Context context, List<Booking> items, String username) {
        this.context = context;
        this.items = items;
        this.username = username;
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
                    .inflate(R.layout.booking_item, parent, false);

            holder = new ViewHolder();
            holder.date = convertView.findViewById(R.id.booking_date);
            holder.time = convertView.findViewById(R.id.booking_time);
            holder.size = convertView.findViewById(R.id.booking_size);
            convertView.setTag(holder);
        }else{
            // Reusing the View (that's recycled)
            holder = (ViewHolder) convertView.getTag();
        }
        // Get the current booking
        Booking booking = items.get(position);

        // Bind data
        holder.date.setText("Date: " + booking.date);
        holder.time.setText("Time: " + booking.time);
        holder.size.setText("Table Size: " + booking.size);



        Button cancel = convertView.findViewById(R.id.cancel_btn);
        Button edit = convertView.findViewById(R.id.edit_btn);
        cancel.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Booking bookingtocancel = items.get(position);

                MyDatabaseHelper db = new MyDatabaseHelper(context);
                SQLiteDatabase database = db.getWritableDatabase();
                database.delete("bookings", "booking_id=?", new String[]{String.valueOf(bookingtocancel.id)});
                items.remove(position);       // remove from adapter's list
                notifyDataSetChanged();


            }


        });

        edit.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Booking bookingtoedit = items.get(position);
                Intent intent = new Intent(context, Customer_editbooking.class);
                intent.putExtra("bookingId", bookingtoedit.id);
                intent.putExtra("username", username);;
                context.startActivity(intent);




            }


        });

        return convertView;

    }

    static class ViewHolder{
        // Holds references to the
        // views within an item layout
        TextView date;
        TextView time;
        TextView size;

    }
}