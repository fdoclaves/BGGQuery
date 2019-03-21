package com.example.fernandoaranaandrade.bggquery.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import com.example.fernandoaranaandrade.bggquery.beans.Username;

import java.util.ArrayList;
import java.util.List;

public class Queries {

    private SQLiteDatabase sqLiteDatabase;

    public Queries(final Context context) {
        DataBase dataBase = new DataBase(context, DataBase.SCHEMA, null, 5);
        sqLiteDatabase = dataBase.getWritableDatabase();
    }

    public List<Username> getAllUsername() {
        return selectUsername("SELECT * FROM " + DataBase.USERNAME_TABLE);
    }

    @NonNull
    private List<Username> selectUsername(final String sql, final String... args) {
        Cursor cursor = sqLiteDatabase.rawQuery(sql, args);
        final List<Username> usernames = new ArrayList<Username>();
        if (cursor.moveToFirst()) {
            do {
                usernames.add(new Username(cursor));
            } while (cursor.moveToNext());
        }
        return usernames;
    }

    public void saveOrUpdateUsername(final Username username) {
        if (username.getId() == null || username.getId() == 0) {
            if(getUsername(username.getUsername()) == null){
                saveUsername(username);
            }
        } else {
            updateUsername(username);
        }
    }

    private void updateUsername(final Username username) {
        String[] ids = new String[1];
        ids[0] = username.getId().toString();
        sqLiteDatabase.update(DataBase.USERNAME_TABLE, fillUsernameContentValues(username), "ID = ?", ids);
    }

    @NonNull
    private ContentValues fillUsernameContentValues(final Username username) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DataBase.USERNAME_FIELD, username.getUsername());
        return contentValues;
    }

    private void saveUsername(final Username username) {
        if (sqLiteDatabase != null) {
            sqLiteDatabase.insert(DataBase.USERNAME_TABLE, null, fillUsernameContentValues(username));
        }
    }

    public Username getUsername(final String username) {
        List<Username> usernames = selectUsername("SELECT * FROM " + DataBase.USERNAME_TABLE + " WHERE username = ?", username);
        if (usernames.size() == 0) {
            return null;
        }
        return usernames.get(0);
    }

    public void deleteUsername(final String username) {
        if (username != null) {
            String[] ids = new String[1];
            ids[0] = username;
            sqLiteDatabase.delete(DataBase.USERNAME_TABLE, "username=?", ids);
        }
    }

    public long getCountUsername() {
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT COUNT(1) FROM " + DataBase.USERNAME_TABLE, new String[0]);
        if (cursor.moveToFirst()) {
            do {
                return cursor.getInt(0);
            } while (cursor.moveToNext());
        }
        return 0;
    }

}