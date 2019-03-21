package com.example.fernandoaranaandrade.bggquery.selectBussines.items;

import com.example.fernandoaranaandrade.bggquery.selectBussines.items.item.Item;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementArray;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.io.Serializable;
import java.util.List;

@Root
public class Items implements Serializable{

    @ElementList(entry = "item", inline = true, required = false)
    private List<Item> item;

    @Attribute(required = false)
    private Long totalitems;

    @Attribute(required = false)
    private String termsofuse;

    @Attribute(required = false)
    private String pubdate;

    private String user;

    public Long getTotalitems() {
        return totalitems;
    }

    public void setTotalitems(Long totalitems) {
        this.totalitems = totalitems;
    }

    public String getTermsofuse() {
        return termsofuse;
    }

    public void setTermsofuse(String termsofuse) {
        this.termsofuse = termsofuse;
    }

    public String getPubdate() {
        return pubdate;
    }

    public void setPubdate(String pubdate) {
        this.pubdate = pubdate;
    }

    public List<Item> getItem() {
        return item;
    }

    public void setItem(Item[] item) {

    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

}
