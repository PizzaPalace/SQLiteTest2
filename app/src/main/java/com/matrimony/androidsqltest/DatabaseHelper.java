package com.matrimony.androidsqltest;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

import java.util.HashMap;

/**
 * Created by syscon on 28/4/15.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public DatabaseHelper(Context context){

        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    public static abstract class Table implements BaseColumns {

        private static final String TABLE_NAME = "RECORDS_TABLE";
        private static final String NAME = "NAME";
        private static final String NUMBER = "NUMBER";
    }

    private static final String DATABASE_NAME ="DATABASE_NAME";
    private static final int DATABASE_VERSION = 1;
    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEPARATOR = " ,";
    private static final String TABLE_CREATE_ENTRIES = "CREATE TABLE " + Table.TABLE_NAME + " (" + Table._ID + " INTEGER PRIMARY KEY," +
            Table.NAME + TEXT_TYPE + COMMA_SEPARATOR +
            Table.NUMBER + TEXT_TYPE + " )";




    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(TABLE_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS" + Table.TABLE_NAME);

        onCreate(db);
    }

    public void addData(){

        SQLiteDatabase db =  getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(Table.NAME,"RAHUL");
        contentValues.put(Table.NUMBER,"00000");

        db.insert(Table.TABLE_NAME,null,contentValues);
    }

    public void addData(String columnValue){

        SQLiteDatabase db =  getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(Table.NAME,columnValue);
        contentValues.put(Table.NUMBER,"00000");

        db.update(Table.TABLE_NAME,contentValues,null,null);
    }

    public HashMap<String,String> getData(){

        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.query(Table.TABLE_NAME,null,null,null,null,null,null);

        HashMap<String,String> map = new HashMap<String,String>();
        if(cursor.moveToFirst()){

            int columnLength = cursor.getColumnCount();

            for(int i=0; i<columnLength; i++){

                map.put(cursor.getColumnName(i),cursor.getString(i));
            }
        }

        Log.v("MAP", map.toString());

        return map;

    }

    public String getData(String key){

        SQLiteDatabase db = getReadableDatabase();

        //Cursor cursor = db.query(Table.TABLE_NAME,null,null,null,null,null,null);
        String[] columns = {key};
        Cursor cursor = db.query(Table.TABLE_NAME,columns,null,null,null,null,null);

        String output = "";
        if(cursor.moveToFirst()){

            int columnLength = cursor.getColumnCount();

            for(int i=0; i<columnLength; i++){

                output = cursor.getString(i);
            }
        }



        return null;

    }
}
