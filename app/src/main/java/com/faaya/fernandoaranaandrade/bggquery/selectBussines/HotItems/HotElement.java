package com.faaya.fernandoaranaandrade.bggquery.selectBussines.HotItems;

import org.simpleframework.xml.Attribute;

import java.io.Serializable;

public class HotElement implements Serializable {

    @Attribute(required = false)
    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}