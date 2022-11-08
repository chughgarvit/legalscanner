package com.mxn.soul.flowingdrawer.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "student.db";
    public static final String TABLE_NAME = "student_table";
    public static final String col_1 = "ID";
    public static final String col_2 = "NAME";
    public DatabaseHelper(Context context) {
        super(context,DATABASE_NAME,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

            db.execSQL("create table "+TABLE_NAME+"(ID TEXT PRIMARY KEY,NAME TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
            onCreate(db);
    }

    public boolean InsertData(String id, String name){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(col_1,id);
        contentValues.put(col_2,name);

        long result  = db.insert(TABLE_NAME,null,contentValues);

        if(result == 1)
            return true;
        else
            return false;

    }

    public Cursor getAllData()
    {

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+TABLE_NAME,null);
        return cursor;
    }

    public  boolean updateData(String id, String name){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(col_1,id);
        contentValues.put(col_2,name);

        db.update(TABLE_NAME,contentValues,"id = ?",new String[] { id });

        return true;

    }

    public Integer deleteData(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        Integer result = db.delete(TABLE_NAME,"id = ?",new String[] { id });
        return result;
    }


}
