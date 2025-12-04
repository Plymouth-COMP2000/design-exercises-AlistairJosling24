package com.example.comp2000new;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MyDatabaseHelper extends SQLiteOpenHelper {
    public MyDatabaseHelper(Context context) {
        super(context, "my_database", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createbookingsTable = "CREATE TABLE bookings (booking_id INTEGER PRIMARY KEY AUTOINCREMENT\n, username TEXT, " +
                "date TEXT, time TEXT, size INTEGER)";
        db.execSQL(createbookingsTable);

        String createordersTable = "CREATE TABLE orders (order_id INTEGER PRIMARY KEY AUTOINCREMENT\n, username TEXT," +
                "items TEXT, price REAL , timestamp TEXT)";
        db.execSQL(createordersTable);

        String createnotificationTable = "CREATE TABLE notifications(notifications_id INTEGER PRIMARY KEY AUTOINCREMENT\n, " +
                "username TEXT , text TEXT , created_at TEXT , read INTEGER DEFAULT 0)";
        db.execSQL(createnotificationTable);
}



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS users");
        onCreate(db);
    }

    public boolean addbooking(String username, String date, String time, int size){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("username", username);
        cv.put("date", date);
        cv.put("time" , time);
        cv.put("size", size);

        long result = db.insert("bookings", null, cv);
        Log.d("DB", "Insert result = " + result);
        if(result == -1){
            return false;
        }else{
            return true;
        }

    }
}