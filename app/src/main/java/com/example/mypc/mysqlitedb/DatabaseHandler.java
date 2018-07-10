package com.example.mypc.mysqlitedb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MY PC on 26-03-2018.
 */

public class DatabaseHandler extends SQLiteOpenHelper
{
    public static final int DATABASE_VERSION = 1;

    public static final String DATABASE_NAME = "Criminals";

    //define table name

    public static final String TABLE_NAME = "Criminal_record";

    //Columns name

    public static final String ID = "id";

    public static final String NAME = "name";

    public static final String DISP = "disp";

    public static final String CITY = "city";


    //this constructor is responsible to create database


    public DatabaseHandler(Context context)               //Context is reference
    {
        super(context,DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String query="CREATE TABLE " + TABLE_NAME + "(" + ID + " NUMBER PRIMARY KEY," + NAME + " TEXT," + DISP + " TEXT," + CITY + " TEXT" + ");";

        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }


    public void addCriminalRecord(CriminalRecord reference)
    {
        SQLiteDatabase database = getWritableDatabase();     //to insert or write data

        ContentValues values = new ContentValues();    //ContentValues  is class and values is its reference

        values.put(ID,reference.getId());
        values.put(NAME,reference.getName());
        values.put(DISP,reference.getDisp());
        values.put(CITY,reference.getCity());

        database.insert(TABLE_NAME,null,values);
        database.close();

    }


    public CriminalRecord getSingleRecord(int id)
    {
        SQLiteDatabase database = getReadableDatabase();          //to read or get data

        Cursor cursor = database.query(TABLE_NAME,new String[] {ID,NAME,DISP,CITY} , ID +"=?", new String[]{String.valueOf(id)},null,null,null);

        //cursor class contain result of query from database

        if(cursor!=null)
            cursor.moveToFirst();

        CriminalRecord  record= new CriminalRecord(cursor.getInt(0) , cursor.getString(1) , cursor.getString(2) , cursor.getString(3));

        return  record;
    }


    public List<CriminalRecord> getAllRecord()
    {
        ArrayList<CriminalRecord> record = new ArrayList<CriminalRecord>();

        SQLiteDatabase database = getReadableDatabase();

        String query = "SELECT * FROM " + TABLE_NAME;

        Cursor cursor = database.rawQuery(query, null);      //null = coloumn name...we need all columns so write null

        if (cursor.moveToFirst())
        {
            do
            {
                CriminalRecord ref = new CriminalRecord();
                ref.setId(cursor.getInt(0));
                ref.setName(cursor.getString(1));
                ref.setDisp(cursor.getString(2));
                ref.setCity(cursor.getString(3));

                //add all record to array list
                record.add(ref);
            }
            while (cursor.moveToNext());
        }
        //return all records....

        return record;
    }

    public void deleteCriminalRecord(int id)
    {
        SQLiteDatabase database = getWritableDatabase();

        database.delete(TABLE_NAME,ID+"=?",new String[]{String.valueOf(id)});

        database.close();

    }


    public void UpdateCriminalRecord(CriminalRecord record)
    {
        SQLiteDatabase database = getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(NAME,record.getName());
        values.put(DISP,record.getDisp());
        values.put(CITY,record.getCity());

        database.update(TABLE_NAME,values, ID+"=?" ,new String[]{String.valueOf(record.getId())});

        database.close();
    }

}
