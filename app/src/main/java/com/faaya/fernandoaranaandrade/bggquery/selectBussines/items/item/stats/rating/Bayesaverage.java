package com.faaya.fernandoaranaandrade.bggquery.selectBussines.items.item.stats.rating;

import org.simpleframework.xml.Attribute;

import java.io.Serializable;

public class Bayesaverage implements Serializable {

    @Attribute
    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
