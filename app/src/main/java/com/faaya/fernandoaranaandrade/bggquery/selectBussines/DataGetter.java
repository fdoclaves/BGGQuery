package com.faaya.fernandoaranaandrade.bggquery.selectBussines;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class DataGetter {

    public static final String HTTPS_WWW_BOARDGAMEGEEK_COM_XMLAPI_COLLECTION = "https://www.boardgamegeek.com/xmlapi/collection/";
    public static final String HTTPS_WWW_BOARDGAMEGEEK_COM_XMLAPI2_HOT_BOARDGAME = "https://www.boardgamegeek.com/xmlapi2/hot?boardgame";

    public DataGetterData getDataFromUser(String user, String filter, int tries, File directory) throws InterruptedException, IOException {
        if (tries > 0) {
            StringBuffer xml = sentRequest(user, filter);
            String content = xml.toString();
            if (content.startsWith("<?xml version=\"1.0\" encoding=\"utf-8\" standalone=\"yes\"?><items totalitems=") || content.startsWith("<items totalitems")) {
                File outputFile = File.createTempFile(user, ".xml", directory);
                FileOutputStream fileInputStream = new FileOutputStream(outputFile);
                fileInputStream.write(content.getBytes());
                fileInputStream.close();
                return new DataGetterData(outputFile, user);
            } else {
                System.out.println(content);
                if (content.contains("Please try again later for access")) {
                    System.out.println("vuelve a intentar");
                    Thread.sleep(4000L);
                    return getDataFromUser(user, filter, tries--, directory);
                }
                if (content.contains("Invalid username specified")) {
                    throw new InvalidUserName();
                }
                throw new XmlInvalid();
            }
        } else {
            System.out.println("Ya no hay mas intentos");
        }
        return null;
    }

    public File getHotnessData(int tries, File directory) throws InterruptedException, IOException {
        if (tries > 0) {
            StringBuffer xml = sentHotnessRequest();
            String content = xml.toString();
            if (content.startsWith("<?xml version=\"1.0\" encoding=\"utf-8\"?><items termsofuse") || content.startsWith("<items termsofuse=\"https://boardgamegeek.com/xmlapi/termsofuse\">")) {
                File outputFile = File.createTempFile("hot", ".xml", directory);
                FileOutputStream fileInputStream = new FileOutputStream(outputFile);
                fileInputStream.write(content.getBytes());
                fileInputStream.close();
                return outputFile;
            } else {
                System.out.println(content);
                if (content.contains("Please try again later for access")) {
                    System.out.println("vuelve a intentar");
                    Thread.sleep(4000l);
                    return getHotnessData(tries--, directory);
                }
                throw new XmlInvalid();
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


    private StringBuffer sentHotnessRequest() throws IOException {
        URL url = new URL(HTTPS_WWW_BOARDGAMEGEEK_COM_XMLAPI2_HOT_BOARDGAME);
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
            String url = HTTPS_WWW_BOARDGAMEGEEK_COM_XMLAPI_COLLECTION + user.replace(" ", "%20");
            System.out.println(url);
            return url;
        }
        return "https://www.boardgamegeek.com/xmlapi/collection/" + user.replace(" ", "%20") + "?" + filter + "=1";
    }

}
