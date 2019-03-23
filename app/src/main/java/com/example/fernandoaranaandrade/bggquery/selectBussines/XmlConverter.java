package com.example.fernandoaranaandrade.bggquery.selectBussines;

import com.example.fernandoaranaandrade.bggquery.selectBussines.HotItems.HotItems;
import com.example.fernandoaranaandrade.bggquery.selectBussines.items.Items;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.io.File;

public class XmlConverter {

    public Items convertToItems(final DataGetterData dataGetterData) throws Exception {
        Serializer serializer = new Persister();
        Items items = serializer.read(Items.class, dataGetterData.getXml());
        dataGetterData.getXml().delete();
        items.setUser(dataGetterData.getUser());
        return items;
    }

    public HotItems convertToItems(final File file) throws Exception {
        Serializer serializer = new Persister();
        HotItems items = serializer.read(HotItems.class, file);
        file.delete();
        return items;
    }
}
