package com.example.comp2000new;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    public MyDatabaseHelper(Context context) {
        super(context, "my_database", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // BOOKINGS TABLE
        db.execSQL("CREATE TABLE bookings (booking_id INTEGER PRIMARY KEY AUTOINCREMENT, username TEXT, date TEXT, time TEXT, size INTEGER)");

        // ORDERS TABLE
        db.execSQL("CREATE TABLE orders (order_id INTEGER PRIMARY KEY AUTOINCREMENT, username TEXT, items TEXT, price REAL, timestamp TEXT)");

        // NOTIFICATIONS TABLE
        db.execSQL("CREATE TABLE notifications (notifications_id INTEGER PRIMARY KEY AUTOINCREMENT, username TEXT, text TEXT, created_at TEXT, read INTEGER DEFAULT 0)");

        // MENU TABLE (IMAGE STORED AS INTEGER)
        db.execSQL("CREATE TABLE menu (menu_id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT, description TEXT, price TEXT, image INTEGER)");

        // default menu items using drawable integers
        db.execSQL("INSERT INTO menu (title, description, price, image) VALUES " +
                "('Cheeseburger', 'Juicy double...', '£5.50', " + R.drawable.cheeseburger + "), " +
                "('Pizza', 'A classic...', '£5.50', " + R.drawable.pizza + ") , " +
                "('Fajitas', 'Hot and spicy...' , '£5.50' , " + R.drawable.fajitas + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS menu");
        onCreate(db);
    }

    public ArrayList<food_item> getMenuItems() {
        ArrayList<food_item> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor c = db.rawQuery("SELECT * FROM menu", null);

        while (c.moveToNext()) {
            list.add(new food_item(
                    c.getString(c.getColumnIndexOrThrow("title")),
                    c.getString(c.getColumnIndexOrThrow("description")),
                    c.getString(c.getColumnIndexOrThrow("price")),
                    c.getInt(c.getColumnIndexOrThrow("image"))
            ));
        }
        c.close();
        return list;
    }

    public boolean updateMenuItem(String oldTitle, String newTitle, String desc, String price) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("title", newTitle);
        cv.put("description", desc);
        cv.put("price", price);

        return db.update("menu", cv, "title=?", new String[]{oldTitle}) > 0;
    }

    public boolean addMenuItem(String title, String desc, String price, int image) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("title", title);
        cv.put("description", desc);
        cv.put("price", price);
        cv.put("image", image);

        return db.insert("menu", null, cv) != -1;
    }

    public void deleteMenuItem(String title) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("menu", "title=?", new String[]{title});
    }

    public boolean addnotification(String username, String text) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("username", username);
        cv.put("text", text);
        cv.put("created_at", System.currentTimeMillis() + ""); // store timestamp as String

        long result = db.insert("notifications", null, cv);
        return result != -1;
    }

    public boolean addbooking(String username, String date, String time, int size) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("username", username);
        cv.put("date", date);
        cv.put("time", time);
        cv.put("size", size);

        long result = db.insert("bookings", null, cv);

        return result != -1;
    }

    public boolean addOrder(String username, String items, double price) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("username", username);
        cv.put("items", items);
        cv.put("price", price);
        cv.put("timestamp", System.currentTimeMillis() + "");  // store as text

        long result = db.insert("orders", null, cv);

        return result != -1;
    }



}
