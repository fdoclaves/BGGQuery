package com.example.fernandoaranaandrade.bggquery;

import com.example.fernandoaranaandrade.bggquery.selectBussines.DataGetterData;
import com.example.fernandoaranaandrade.bggquery.selectBussines.items.Items;

import java.io.Serializable;

public class MainActivityMetadata implements Serializable {

    private DataGetterData[] itemsArray;
    private String[] users;

    public MainActivityMetadata(DataGetterData[] itemsArray, String[] users) {
        this.itemsArray = itemsArray;
        this.users = users;
    }

    public DataGetterData[] getDataGetterDataArray() {
        return itemsArray;
    }

    public String[] getUsers() {
        return users;
    }
}
