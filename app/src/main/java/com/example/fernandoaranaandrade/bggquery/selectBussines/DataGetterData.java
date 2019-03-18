package com.example.fernandoaranaandrade.bggquery.selectBussines;

import java.io.File;
import java.io.Serializable;

public class DataGetterData implements Serializable {
    private File xml;
    private String user;

    public DataGetterData(File xml, String user){
        this.xml = xml;
        this.user = user;
    }

    public File getXml() {
        return xml;
    }

    public String getUser() {
        return user;
    }
}
