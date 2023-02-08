package com.example.demo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String USER_TABLE = "USER_TABLE";
    public static final String USER_ID = "ID";
    public static final String USER_FIRSTNAME = "FIRSTNAME";
    public static final String USER_LASTNAME = "LASTNAME";
    public static final String USER_USERNAME = "USERNAME";
    public static final String USER_PASSWORD = "PASSWORD";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "demo.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery = "CREATE TABLE " + USER_TABLE + " (" + USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + USER_FIRSTNAME + " TEXT, " + USER_LASTNAME + " TEXT, " + USER_USERNAME + " TEXT, " + USER_PASSWORD + " TEXT)";
        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean register(UserData userData) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(USER_FIRSTNAME, userData.getFirstName());
        cv.put(USER_LASTNAME, userData.getLastName());
        cv.put(USER_USERNAME, userData.getUsername());
        cv.put(USER_PASSWORD, userData.getPassword());

        long res = db.insert(USER_TABLE, null, cv);
        if (res == -1) return false;
        else return true;
    }

    public UserData check(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + USER_TABLE + " WHERE " + USER_USERNAME + "='" + username + "' AND " + USER_PASSWORD + "='" + password + "';";
        UserData userData;
        Cursor cursor =  db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            String firstName = cursor.getString(1);
            String lastName = cursor.getString(2);
            userData = new UserData(firstName, lastName, username, password);
        } else userData = null;

        cursor.close();
        db.close();

        return userData;
    }
}
