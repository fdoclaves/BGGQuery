package com.faaya.fernandoaranaandrade.bggquery.beans;

import android.database.Cursor;

public class Username {
    private Long id;
    private String username;

    public Username(){

    }

    public Username(final String username) {
        setUsername(username);
    }

    public Username(final Cursor cursor) {
        setId(cursor.getLong(0));
        setUsername(cursor.getString(1));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
