package com.faaya.fernandoaranaandrade.bggquery.selectBussines.HotItems;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root
public class HotItems {

    @Attribute(required = false)
    private String termsofuse;

    @ElementList(entry = "item", inline = true, required = false)
    private List<HotItem> item;

    public List<HotItem> getItem() {
        return item;
    }
}
