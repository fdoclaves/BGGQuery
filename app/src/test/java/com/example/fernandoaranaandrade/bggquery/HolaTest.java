package com.example.fernandoaranaandrade.bggquery;


import com.example.fernandoaranaandrade.bggquery.selectBussines.DataGetter;
import com.example.fernandoaranaandrade.bggquery.selectBussines.GamesProcessor;
import com.example.fernandoaranaandrade.bggquery.selectBussines.GamesRated;
import com.example.fernandoaranaandrade.bggquery.selectBussines.InvalidUserName;
import com.example.fernandoaranaandrade.bggquery.selectBussines.Result;
import com.example.fernandoaranaandrade.bggquery.selectBussines.items.Items;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class HolaTest {

    private DataGetter dataGetter = new DataGetter();

    String xmlString = "<items totalitems=\"210\" termsofuse=\"https://boardgamegeek.com/xmlapi/termsofuse\" pubdate=\"Thu, 14 Mar 2019 16:13:38 +0000\">\n" +
            "<item objecttype=\"thing\" objectid=\"173346\" subtype=\"boardgame\" collid=\"38043883\">\n" +
            "<name sortindex=\"1\">7 Wonders Duel</name>\n" +
            "<yearpublished>2015</yearpublished>\n" +
            "<image>\n" +
            "https://cf.geekdo-images.com/original/img/M6wL1YFgW-PsdtJ328m2QiJf1K8=/0x0/pic3376065.jpg\n" +
            "</image>\n" +
            "<thumbnail>\n" +
            "https://cf.geekdo-images.com/thumb/img/cwWMq5feF7O4O82HJOK3WE5IZ6o=/fit-in/200x150/pic3376065.jpg\n" +
            "</thumbnail>\n" +
            "<stats minplayers=\"2\" maxplayers=\"2\" minplaytime=\"30\" maxplaytime=\"30\" playingtime=\"30\" numowned=\"60572\">\n" +
            "<rating value=\"6\">\n" +
            "<usersrated value=\"39176\"/>\n" +
            "<average value=\"8.12749\"/>\n" +
            "<bayesaverage value=\"7.99379\"/>\n" +
            "<stddev value=\"1.16274\"/>\n" +
            "<median value=\"0\"/>\n" +
            "</rating>\n" +
            "</stats>\n" +
            "<status own=\"0\" prevowned=\"0\" fortrade=\"0\" want=\"0\" wanttoplay=\"0\" wanttobuy=\"0\" wishlist=\"1\" wishlistpriority=\"3\" preordered=\"0\" lastmodified=\"2017-08-13 21:17:33\"/>\n" +
            "<numplays>0</numplays>\n" +
            "</item>\n" +
            "<item objecttype=\"thing\" objectid=\"5\" subtype=\"boardgame\" collid=\"44093348\">\n" +
            "<name sortindex=\"1\">Acquire</name>\n" +
            "<yearpublished>1964</yearpublished>\n" +
            "<image>\n" +
            "https://cf.geekdo-images.com/original/img/Bz4tTHNpq6gUKFkJs0fJdVIGR1s=/0x0/pic3299296.jpg\n" +
            "</image>\n" +
            "<thumbnail>\n" +
            "https://cf.geekdo-images.com/thumb/img/ZODhu5g13HRQEcOgMP8oiRooQQc=/fit-in/200x150/pic3299296.jpg\n" +
            "</thumbnail>\n" +
            "<stats minplayers=\"2\" maxplayers=\"6\" minplaytime=\"90\" maxplaytime=\"90\" playingtime=\"90\" numowned=\"21159\">\n" +
            "<rating value=\"7\">\n" +
            "<usersrated value=\"16867\"/>\n" +
            "<average value=\"7.35385\"/>\n" +
            "<bayesaverage value=\"7.1887\"/>\n" +
            "<stddev value=\"1.33018\"/>\n" +
            "<median value=\"0\"/>\n" +
            "</rating>\n" +
            "</stats>\n" +
            "<status own=\"1\" prevowned=\"0\" fortrade=\"0\" want=\"0\" wanttoplay=\"0\" wanttobuy=\"0\" wishlist=\"0\" preordered=\"0\" lastmodified=\"2018-05-04 21:05:02\"/>\n" +
            "<numplays>0</numplays>\n" +
            "</item>\n" +
            "</items>";


    String xmlString2 = "<items totalitems=\"210\" termsofuse=\"https://boardgamegeek.com/xmlapi/termsofuse\" pubdate=\"Thu, 14 Mar 2019 16:13:38 +0000\">\n" +
            "<item objecttype=\"thing\" objectid=\"173346\" subtype=\"boardgame\" collid=\"38043883\">\n" +
            "<name sortindex=\"1\">7 Wonders Duel</name>\n" +
            "<yearpublished>2015</yearpublished>\n" +
            "<image>\n" +
            "https://cf.geekdo-images.com/original/img/M6wL1YFgW-PsdtJ328m2QiJf1K8=/0x0/pic3376065.jpg\n" +
            "</image>\n" +
            "<thumbnail>\n" +
            "https://cf.geekdo-images.com/thumb/img/cwWMq5feF7O4O82HJOK3WE5IZ6o=/fit-in/200x150/pic3376065.jpg\n" +
            "</thumbnail>\n" +
            "<stats minplayers=\"2\" maxplayers=\"2\" minplaytime=\"30\" maxplaytime=\"30\" playingtime=\"30\" numowned=\"60572\">\n" +
            "<rating value=\"10\">\n" +
            "<usersrated value=\"39176\"/>\n" +
            "<average value=\"8.12749\"/>\n" +
            "<bayesaverage value=\"7.99379\"/>\n" +
            "<stddev value=\"1.16274\"/>\n" +
            "<median value=\"0\"/>\n" +
            "</rating>\n" +
            "</stats>\n" +
            "<status own=\"0\" prevowned=\"0\" fortrade=\"0\" want=\"0\" wanttoplay=\"0\" wanttobuy=\"0\" wishlist=\"1\" wishlistpriority=\"3\" preordered=\"0\" lastmodified=\"2017-08-13 21:17:33\"/>\n" +
            "<numplays>0</numplays>\n" +
            "</item>\n" +
            "<item objecttype=\"thing\" objectid=\"5\" subtype=\"boardgame\" collid=\"44093348\">\n" +
            "<name sortindex=\"1\">Acquire</name>\n" +
            "<yearpublished>1964</yearpublished>\n" +
            "<image>\n" +
            "https://cf.geekdo-images.com/original/img/Bz4tTHNpq6gUKFkJs0fJdVIGR1s=/0x0/pic3299296.jpg\n" +
            "</image>\n" +
            "<thumbnail>\n" +
            "https://cf.geekdo-images.com/thumb/img/ZODhu5g13HRQEcOgMP8oiRooQQc=/fit-in/200x150/pic3299296.jpg\n" +
            "</thumbnail>\n" +
            "<stats minplayers=\"2\" maxplayers=\"6\" minplaytime=\"90\" maxplaytime=\"90\" playingtime=\"90\" numowned=\"21159\">\n" +
            "<rating value=\"10\">\n" +
            "<usersrated value=\"16867\"/>\n" +
            "<average value=\"7.35385\"/>\n" +
            "<bayesaverage value=\"7.1887\"/>\n" +
            "<stddev value=\"1.33018\"/>\n" +
            "<median value=\"0\"/>\n" +
            "</rating>\n" +
            "</stats>\n" +
            "<status own=\"1\" prevowned=\"0\" fortrade=\"0\" want=\"0\" wanttoplay=\"0\" wanttobuy=\"0\" wishlist=\"0\" preordered=\"0\" lastmodified=\"2018-05-04 21:05:02\"/>\n" +
            "<numplays>0</numplays>\n" +
            "</item>\n" +
            "</items>";

    String xmlString3 = "<items totalitems=\"210\" termsofuse=\"https://boardgamegeek.com/xmlapi/termsofuse\" pubdate=\"Thu, 14 Mar 2019 16:13:38 +0000\">\n" +
            "<item objecttype=\"thing\" objectid=\"173346\" subtype=\"boardgame\" collid=\"38043883\">\n" +
            "<name sortindex=\"1\">7 Wonders Duel</name>\n" +
            "<yearpublished>2015</yearpublished>\n" +
            "<image>\n" +
            "https://cf.geekdo-images.com/original/img/M6wL1YFgW-PsdtJ328m2QiJf1K8=/0x0/pic3376065.jpg\n" +
            "</image>\n" +
            "<thumbnail>\n" +
            "https://cf.geekdo-images.com/thumb/img/cwWMq5feF7O4O82HJOK3WE5IZ6o=/fit-in/200x150/pic3376065.jpg\n" +
            "</thumbnail>\n" +
            "<stats minplayers=\"2\" maxplayers=\"2\" minplaytime=\"30\" maxplaytime=\"30\" playingtime=\"30\" numowned=\"60572\">\n" +
            "<rating value=\"10\">\n" +
            "<usersrated value=\"39176\"/>\n" +
            "<average value=\"8.12749\"/>\n" +
            "<bayesaverage value=\"7.99379\"/>\n" +
            "<stddev value=\"1.16274\"/>\n" +
            "<median value=\"0\"/>\n" +
            "</rating>\n" +
            "</stats>\n" +
            "<status own=\"0\" prevowned=\"0\" fortrade=\"0\" want=\"0\" wanttoplay=\"0\" wanttobuy=\"0\" wishlist=\"1\" wishlistpriority=\"3\" preordered=\"0\" lastmodified=\"2017-08-13 21:17:33\"/>\n" +
            "<numplays>0</numplays>\n" +
            "</item>\n" +
            "</items>";

    String wait = "<?xml version=\"1.0\" encoding=\"utf-8\" standalone=\"yes\"?>\n" +
            "<message>Your request for this collection has been accepted and will be processed. Please try again later for access.</message>";
    private GamesProcessor gamesProcessor = new GamesProcessor();;

    @Test
    public void ddd() throws Exception {
        String hols = "<items totalitems=\"58\" termsofuse=\"https://boardgamegeek.com/xmlapi/termsofuse\" pubdate=\"Fri, 15 Mar 2019 05:17:53 +0000\">\n" +
                "<item objecttype=\"thing\" objectid=\"432\" subtype=\"boardgame\" collid=\"35624142\">\n" +
                "<name sortindex=\"1\">6 nimmt!</name>\n" +
                "<yearpublished>1994</yearpublished>\n" +
                "<image>\n" +
                "https://cf.geekdo-images.com/original/img/3Gg4GrqJwbhQHSYcw1TJJQDMsw8=/0x0/pic2602138.jpg\n" +
                "</image>\n" +
                "<thumbnail>\n" +
                "https://cf.geekdo-images.com/thumb/img/X51KVi_wJEP3x7i65tQUBNOmR3g=/fit-in/200x150/pic2602138.jpg\n" +
                "</thumbnail>\n" +
                "<stats minplayers=\"2\" maxplayers=\"10\" minplaytime=\"45\" maxplaytime=\"45\" playingtime=\"45\" numowned=\"20611\">\n" +
                "<rating value=\"N/A\">\n" +
                "<usersrated value=\"15192\"/>\n" +
                "<average value=\"6.88277\"/>\n" +
                "<bayesaverage value=\"6.75359\"/>\n" +
                "<stddev value=\"1.24606\"/>\n" +
                "<median value=\"0\"/>\n" +
                "</rating>\n" +
                "</stats>\n" +
                "<status own=\"0\" prevowned=\"0\" fortrade=\"0\" want=\"0\" wanttoplay=\"0\" wanttobuy=\"0\" wishlist=\"0\" preordered=\"0\" lastmodified=\"2016-07-10 23:18:42\"/>\n" +
                "<numplays>1</numplays>\n" +
                "</item>\n" +
                "<item objecttype=\"thing\" objectid=\"161537\" subtype=\"boardgame\" collid=\"56481312\">\n" +
                "<name sortindex=\"1\">7 Steps</name>\n" +
                "<yearpublished>2014</yearpublished>\n" +
                "<image>\n" +
                "https://cf.geekdo-images.com/original/img/jgnCLaOGPU7WiAyJ6AkqaECRXMw=/0x0/pic2087756.jpg\n" +
                "</image>\n" +
                "<thumbnail>\n" +
                "https://cf.geekdo-images.com/thumb/img/ksK5uXegSj3xeJmaikPtljhgd4o=/fit-in/200x150/pic2087756.jpg\n" +
                "</thumbnail>\n" +
                "<stats minplayers=\"2\" maxplayers=\"4\" minplaytime=\"30\" maxplaytime=\"30\" playingtime=\"30\" numowned=\"202\">\n" +
                "<rating value=\"6\">\n" +
                "<usersrated value=\"244\"/>\n" +
                "<average value=\"6.27863\"/>\n" +
                "<bayesaverage value=\"5.62351\"/>\n" +
                "<stddev value=\"1.36021\"/>\n" +
                "<median value=\"0\"/>\n" +
                "</rating>\n" +
                "</stats>\n" +
                "<status own=\"0\" prevowned=\"0\" fortrade=\"0\" want=\"0\" wanttoplay=\"0\" wanttobuy=\"0\" wishlist=\"0\" preordered=\"0\" lastmodified=\"2018-12-09 09:01:58\"/>\n" +
                "<numplays>29</numplays>\n" +
                "</item>\n" +
                "</items>";
        Items items = dataGetter.convertToItems(hols, "yop");
        System.out.println(items.getTotalitems());
        System.out.println(items.getItem().size());
        System.out.println(items.getItem().get(0).getObjectid());
        System.out.println(items.getItem().get(0).getName().getName());
        System.out.println(items.getItem().get(0).getYearpublished());
        System.out.println(items.getItem().get(0).getStatus().getOwn());
        System.out.println(items.getItem().get(0).getStats().getMaxplayers());
        System.out.println(items.getItem().get(0).getStats().getRating().getValue());
        System.out.println(items.getItem().get(0).getStats().getRating().getUsersrated().getValue());
    }

    @Test
    public void holas() throws Exception {
        Items items = dataGetter.convertToItems(xmlString, "yop");
        Items items2 = dataGetter.convertToItems(xmlString2, "el");
        Result result = gamesProcessor.bestGame(items, items2);
        assertEquals("Acquire", result.getAllGames().get(0).getName());
        assertEquals("7 Wonders Duel", result.getAllGames().get(result.getAllGames().size() -1).getName());

    }

    @Test
    public void holas2() throws Exception {
        Items items = dataGetter.convertToItems(xmlString, "yop");
        Items items2 = dataGetter.convertToItems(xmlString2, "el");
        Items items3 = dataGetter.convertToItems(xmlString3, "ella");
        Result result = gamesProcessor.bestGame(items, items2, items3);
        assertEquals(2, result.getAllGames().size());
        assertEquals("7 Wonders Duel", result.getAllGames().get(0).getName());
        assertEquals("Acquire", result.getAllGames().get(result.getAllGames().size() -1).getName());

    }

    @Test
    public void holas3() throws Exception {
        Items items = dataGetter.convertToItems(xmlString, "yop");
        Result result = gamesProcessor.bestGame(items);
        assertEquals("Acquire", result.getAllGames().get(0).getName());
        assertEquals("7 Wonders Duel", result.getAllGames().get(result.getAllGames().size() -1).getName());
    }

    @Test
    public void lufemanu() throws  IOException, InterruptedException {
        Items items = dataGetter.getDataFromUser("lufemanu", null, 4000l, 5);
        System.out.println(items.getItem().size());
        System.out.println(items.getItem().get(0).getStats().getRating().getValue());
        System.out.println(items.getItem().get(items.getItem().size()-1).getStats().getRating().getValue());
    }

    @Test
    public void RequiemOnline() throws  IOException, InterruptedException {
        Items items = dataGetter.getDataFromUser("Requiem Online", null, 4000l, 5);
        System.out.println(items.getItem().size());
        System.out.println(items.getItem().get(0).getStats().getRating().getValue());
        System.out.println(items.getItem().get(items.getItem().size()-1).getStats().getRating().getValue());
    }

    @Test
    public void hola() throws  IOException, InterruptedException {
        Items items = dataGetter.getDataFromUser("fdo_claves", "rated", 4000l, 5);
        System.out.println(items.getItem().size());
        System.out.println(items.getItem().get(0).getStats().getRating().getValue());
        System.out.println(items.getItem().get(items.getItem().size()-1).getStats().getRating().getValue());
    }

    @Test
    public void pepe444() throws IOException, InterruptedException {
        try {
            dataGetter.getDataFromUser("pepe444", "rated", 3000l, 5);
            fail();
        } catch (InvalidUserName e){
            System.out.println("No exite el usuario");
        }
    }

    @Test
    public void holass() throws InterruptedException, IOException {
        Items items = dataGetter.getDataFromUser("fdo_claves", "rated", 3000l, 5);
        Items items2 = dataGetter.getDataFromUser("jsv444", "rated", 3000l, 5);
        Result result = gamesProcessor.bestGame(items, items2);
        System.out.println("lista............");
        for (GamesRated bestGame : result.getAllGames()) {
            System.out.println(bestGame.getName() + " - " + bestGame.getAllAverage());
        }
    }

    @Test
    public void holass2() throws InterruptedException, IOException {
        Items items = dataGetter.getDataFromUser("fdo_claves", "rated", 3000l, 5);
        Items items2 = dataGetter.getDataFromUser("xobxela", "rated", 3000l, 5);
        Result result = gamesProcessor.bestGame(items, items2);
        System.out.println("lista...........");
        for (GamesRated bestGame : result.getAllGames()) {
            System.out.println(bestGame.getName() + " - " + bestGame.getAllAverage());
        }
    }

    @Test
    public void holass3() throws InterruptedException, IOException {
        Items items = dataGetter.getDataFromUser("fdo_claves", "rated", 3000l, 5);
        Items items2 = dataGetter.getDataFromUser("xobxela", "rated", 3000l, 5);
        Items items3 = dataGetter.getDataFromUser("jsv444", "rated", 3000l, 5);
        Result result = gamesProcessor.bestGame(items, items2, items3);
        System.out.println("semi-lista...........");
        for (GamesRated bestGame : result.getAllGames()) {
            System.out.println(bestGame.getName() + " - " + bestGame.getAllAverage() + "(" + bestGame.getNumberRated()+")");
        }
    }

    @Test
    public void holasss() throws InterruptedException, IOException {
        Items items = dataGetter.getDataFromUser("fdo_claves", null, 3000l, 5);
        Items items2 = dataGetter.getDataFromUser("xobxela", null, 3000l, 5);
        Items items3 = dataGetter.getDataFromUser("agriancito", null, 3000l, 5);
        Result result = gamesProcessor.bestGame(items, items2, items3);
        System.out.println("lista...........");
        for (GamesRated bestGame : result.getAllGames()) {
            if(bestGame.hasRating()){
                System.out.println(bestGame.getName() + " - " + bestGame.getAllAverage() + "(" + bestGame.getNumberRated()+") ");
                System.out.println(bestGame.getAverage("fdo_claves") + " - " + bestGame.getAverage("xobxela") + " - " + bestGame.getAverage("agriancito"));
            }
        }
        System.out.println("Mencion especial.......");
        for (GamesRated bestGame : result.getAllGames()) {
            if(!bestGame.hasRating() && bestGame.getTotalToWantToPlay() > 0){
                System.out.println(bestGame.getName());
                System.out.println(bestGame.getWantToPlay("fdo_claves") + " - " + bestGame.getWantToPlay("xobxela") + " - " + bestGame.getWantToPlay("agriancito"));

            }
        }
    }

}
