package com.example.fernandoaranaandrade.bggquery.selectBussines;

import android.content.Intent;

import com.example.fernandoaranaandrade.bggquery.ChoseGamesActivity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class DataGetter {

    public DataGetterData getDataFromUser(String user, String filter, int tries, File directory) {
        try {
            if (tries > 0) {
                StringBuffer xml = sentRequest(user, filter);
                String content = xml.toString();
                if(content.startsWith("<?xml version=\"1.0\" encoding=\"utf-8\" standalone=\"yes\"?><items totalitems=") || content.startsWith("<items totalitems")){
                    File outputFile = File.createTempFile(user, ".xml", directory);
                    FileOutputStream fileInputStream = new FileOutputStream(outputFile);
                    fileInputStream.write(content.getBytes());
                    fileInputStream.close();
                    return new DataGetterData(outputFile, user);
                } else {
                    System.out.println(content);
                    if (content.contains("Please try again later for access")) {
                        System.out.println("vuelve a intentar");
                        Thread.sleep(4000l);
                        return getDataFromUser(user, filter,tries--,directory);
                    }
                    if (content.contains("Invalid username specified")) {
                        throw new InvalidUserName();
                    }
                }
            } else {
                System.out.println("Ya no hay mas intentos");
            }
        } catch (Exception e) {
            e.printStackTrace();
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
        if (filter == null) {
            String url = "https://www.boardgamegeek.com/xmlapi/collection/" + user.replace(" ", "%20");
            System.out.println(url);
            return url;
        }
        return "https://www.boardgamegeek.com/xmlapi/collection/" + user.replace(" ", "%20") + "?" + filter + "=1";
    }

}
