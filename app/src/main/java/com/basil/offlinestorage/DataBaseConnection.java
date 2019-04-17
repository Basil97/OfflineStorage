package com.basil.offlinestorage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DataBaseConnection extends SQLiteOpenHelper {

    private static String mDBName = "Students.db";
    private static int mVersion = 1;

    DataBaseConnection(Context context) {
        super(context, mDBName, null, mVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS Students (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, age INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Students");
        onCreate(db);
    }

    void addStudent(String name, int age) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("age", age);
        db.insert("Students", null, values);
    }

    ArrayList<String> getAll() {
        ArrayList<String> data = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Students", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            data.add(cursor.getString(cursor.getColumnIndex("name")) +
                    "\n" + cursor.getInt(cursor.getColumnIndex("age")));
            cursor.moveToNext();
        }
        cursor.close();
        return data;
    }
}
