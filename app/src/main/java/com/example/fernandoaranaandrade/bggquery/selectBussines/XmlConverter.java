package com.example.fernandoaranaandrade.bggquery.selectBussines;

import com.example.fernandoaranaandrade.bggquery.selectBussines.items.Items;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

public class XmlConverter {

    public Items convertToItems(final DataGetterData dataGetterData) throws Exception {
        Serializer serializer = new Persister();
        Items items = serializer.read(Items.class, dataGetterData.getXml());
        dataGetterData.getXml().delete();
        items.setUser(dataGetterData.getUser());
        return items;
    }
}
