package com.example.fernandoaranaandrade.bggquery.selectBussines.items.item;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Text;

import java.io.Serializable;

public class Name implements Serializable {

    @Attribute(required = false)
    private Long sortindex;

    @Text(required = false)
    private String name;

    public Long getSortindex() {
        return sortindex;
    }

    public void setSortindex(Long sortindex) {
        this.sortindex = sortindex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
