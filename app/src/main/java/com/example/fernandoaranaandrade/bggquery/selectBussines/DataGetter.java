package com.example.fernandoaranaandrade.bggquery.selectBussines;

import android.content.Context;

import com.example.fernandoaranaandrade.bggquery.selectBussines.items.Items;
import com.example.fernandoaranaandrade.bggquery.selectBussines.items.item.Item;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class DataGetter {

    public DataGetterData getDataFromUser(String user, String filter, int tries, File directory) throws IOException, InterruptedException {
        if (tries > 0) {
            StringBuffer xml = sentRequest(user, filter);
            try {
                File outputFile = File.createTempFile(user, "txt", directory);
                FileOutputStream fileInputStream = new FileOutputStream(outputFile);
                fileInputStream.write(xml.toString().getBytes());
                fileInputStream.close();
                return new DataGetterData(outputFile,user);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Ya no hay mas intentos");
        }
        return null;
    }



    private StringBuffer sentRequest(String user, String filter) throws IOException {
        URL url = new URL(getBaseURL(user, filter));
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();
        return content;
    }


    private String getBaseURL(String user, String filter) {
        if(filter == null){
            String url = "https://www.boardgamegeek.com/xmlapi/collection/" + user.replace(" ", "%20");
            System.out.println(url);
            return url;
        }
        return "https://www.boardgamegeek.com/xmlapi/collection/" + user.replace(" ","%20") + "?" + filter + "=1";
    }

}
