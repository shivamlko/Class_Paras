package com.navoki.class_paras;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class Database extends SQLiteOpenHelper {

    public Database(Context context) {
        super(context, "MyDB.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE STUDENT(id INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT,subject TEXT)";
        db.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    void insert(String name, String subject) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("INSERT INTO STUDENT(name,subject) VALUES('" + name + "','" + subject + "')");

    }

    ArrayList<Student> selectAll() {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM STUDENT", null);
        ArrayList<Student> list = new ArrayList<>();

        if (cursor.moveToNext()) {
            do {
                Student student = new Student();
                student.setName(cursor.getString(1));
                student.setBranch(cursor.getString(2));

                list.add(student);
            }
            while (cursor.moveToNext());
        }

        return list;


    }

    ArrayList<Student> filter(String subject) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM STUDENT WHERE SUBJECT='" + subject + "'", null);
        ArrayList<Student> list = new ArrayList<>();

        if (cursor.moveToNext()) {
            do {
                Student student = new Student();
                student.setName(cursor.getString(1));
                student.setBranch(cursor.getString(2));

                list.add(student);
            }
            while (cursor.moveToNext());
        }

        return list;

    }

}
