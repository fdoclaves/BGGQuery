package com.example.fernandoaranaandrade.bggquery.selectBussines;

import com.example.fernandoaranaandrade.bggquery.selectBussines.items.Items;
import com.example.fernandoaranaandrade.bggquery.selectBussines.items.item.Item;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

public class XmlConverter {

    public Items convertToItems(DataGetterData dataGetterData) throws Exception {
        Serializer serializer = new Persister();
        Items items = serializer.read(Items.class, dataGetterData.getXml());
        items.setUser(dataGetterData.getUser());
        //print(items.getItem().get(0));
        //print(items.getItem().get(1));
        //print(items.getItem().get(items.getItem().size() - 1));
        return items;
    }

    private void print(Item item) {
        System.out.println(item.getImage());
        System.out.println(item.getThumbnail());
        System.out.println(item.getName());
        System.out.println(item.getYearpublished());
        System.out.println(item.getNumplays());
        System.out.println(item.getStats().getRating().getValue());
        System.out.println(item.getStats().getRating().getAverage().getValue());
        System.out.println(item.getStats().getMaxplayers());
        System.out.println(item.getStats().getMinplayers());
        System.out.println(item.getStatus().getLastmodified());
        System.out.println(item.getObjectid());
    }
}
