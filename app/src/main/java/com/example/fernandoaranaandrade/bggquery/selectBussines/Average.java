package com.example.fernandoaranaandrade.bggquery.selectBussines;

import com.example.fernandoaranaandrade.bggquery.selectBussines.items.item.Item;

import java.util.ArrayList;
import java.util.List;

public class Average {

    private List<Item> items = new ArrayList<>();
    private int rates = 0;
    private Double allRakings = new Double(0);

    public void addRates() {
        this.rates++;
    }

    public int getRates() {
        return rates;
    }

    public void add(Double average) {
        this.allRakings = this.allRakings + average;
    }

    public Double getAllRakings() {
        return allRakings;
    }

    public void add(Item item) {
        items.add(item);
    }

    public List<Item> getItems(){
        return items;
    }
}
