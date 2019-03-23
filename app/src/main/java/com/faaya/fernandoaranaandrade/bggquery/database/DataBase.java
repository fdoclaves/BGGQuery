package com.faaya.fernandoaranaandrade.bggquery.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBase extends SQLiteOpenHelper {

    public static final String USERNAME_TABLE = "USERNAME";
    public static final String USERNAME_FIELD = "username";
    public static String SCHEMA = "BGGQUERY";

    String usernameTable = "CREATE TABLE USERNAME(ID INTEGER PRIMARY KEY AUTOINCREMENT, username TEXT)";

    public DataBase(Context context,String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(usernameTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        switch (oldVersion){
            case 1:
                //hola
        }
    }
}
