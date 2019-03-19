package com.example.fernandoaranaandrade.bggquery;


import com.example.fernandoaranaandrade.bggquery.selectBussines.DataGetter;
import com.example.fernandoaranaandrade.bggquery.selectBussines.DataGetterData;
import com.example.fernandoaranaandrade.bggquery.selectBussines.GamesProcessor;
import com.example.fernandoaranaandrade.bggquery.selectBussines.GamesRated;
import com.example.fernandoaranaandrade.bggquery.selectBussines.InvalidUserName;
import com.example.fernandoaranaandrade.bggquery.selectBussines.Result;
import com.example.fernandoaranaandrade.bggquery.selectBussines.XmlConverter;
import com.example.fernandoaranaandrade.bggquery.selectBussines.items.Items;

import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

public class HolaTest {

    private DataGetter dataGetter = new DataGetter();
    private XmlConverter xmlConverter = new XmlConverter();

    private GamesProcessor gamesProcessor = new GamesProcessor();

    private File directory;

    @Before
    public void before(){
        directory = new File("conf");
        directory.mkdir();
    }

    @Test
    public void ddd() throws Exception {
        Items items = xmlConverter.convertToItems(new DataGetterData(new File("prueba1.xml"),"yop"));
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
    public void lufemanu() throws Exception {
        DataGetterData dataGetterData = dataGetter.getDataFromUser("lufemanu", null, 5, directory);
        Items items = xmlConverter.convertToItems(dataGetterData);
        System.out.println(items.getItem().size());
        System.out.println(items.getItem().get(0).getStats().getRating().getValue());
        System.out.println(items.getItem().get(items.getItem().size()-1).getStats().getRating().getValue());
    }

    @Test
    public void pepe44() throws Exception {
        DataGetterData dataGetterData = dataGetter.getDataFromUser("pepe44", null, 5, directory);
        Items items = xmlConverter.convertToItems(dataGetterData);
        assertNull(items.getItem());
    }

    @Test
    public void RequiemOnline() throws Exception {
        DataGetterData dataGetterData = dataGetter.getDataFromUser("Requiem Online", null, 5, directory);
        Items items = xmlConverter.convertToItems(dataGetterData);
        System.out.println(items.getItem().size());
        System.out.println(items.getItem().get(0).getStats().getRating().getValue());
        System.out.println(items.getItem().get(items.getItem().size()-1).getStats().getRating().getValue());
    }

    @Test
    public void hola() throws Exception {
        DataGetterData dataGetterData = dataGetter.getDataFromUser("fdo_claves", null, 5, directory);
        Items items = xmlConverter.convertToItems(dataGetterData);
        System.out.println(items.getItem().size());
        System.out.println(items.getItem().get(0).getStats().getRating().getValue());
        System.out.println(items.getItem().get(items.getItem().size()-1).getStats().getRating().getValue());
    }

    @Test
    public void pepe444() throws Exception {
        try {
            DataGetterData dataGetterData = dataGetter.getDataFromUser("pepe444", null, 5, directory);
            xmlConverter.convertToItems(dataGetterData);
            fail();
        } catch (InvalidUserName e){
            System.out.println("No exite el usuario");
        }
    }

    @Test
    public void holass() throws Exception {
        DataGetterData dataGetterData = dataGetter.getDataFromUser("fdo_claves", null, 5, directory);
        Items items = xmlConverter.convertToItems(dataGetterData);
        DataGetterData dataGetterData2 = dataGetter.getDataFromUser("jsv444", null, 5, directory);
        Items items2 = xmlConverter.convertToItems(dataGetterData2);
        Result result = gamesProcessor.bestGame(items, items2);
        System.out.println("lista............");
        for (GamesRated bestGame : result.getAllGames()) {
            System.out.println(bestGame.getName() + " - " + bestGame.getAllAverage());
        }
    }

    @Test
    public void holass2() throws Exception {
        DataGetterData dataGetterData = dataGetter.getDataFromUser("fdo_claves", null, 5, directory);
        Items items = xmlConverter.convertToItems(dataGetterData);
        DataGetterData dataGetterData2 = dataGetter.getDataFromUser("xobxela", null, 5, directory);
        Items items2 = xmlConverter.convertToItems(dataGetterData2);

        Result result = gamesProcessor.bestGame(items, items2);
        System.out.println("lista...........");
        for (GamesRated bestGame : result.getAllGames()) {
            System.out.println(bestGame.getName() + " - " + bestGame.getAllAverage());
        }
    }

    @Test
    public void holass3() throws Exception {
        DataGetterData dataGetterData = dataGetter.getDataFromUser("fdo_claves", null, 5, directory);
        Items items = xmlConverter.convertToItems(dataGetterData);
        DataGetterData dataGetterData2 = dataGetter.getDataFromUser("xobxela", null, 5, directory);
        Items items2 = xmlConverter.convertToItems(dataGetterData2);
        DataGetterData dataGetterData3 = dataGetter.getDataFromUser("jsv444", null, 5, directory);
        Items items3 = xmlConverter.convertToItems(dataGetterData3);
        Result result = gamesProcessor.bestGame(items, items2, items3);
        System.out.println("semi-lista...........");
        for (GamesRated bestGame : result.getAllGames()) {
            System.out.println(bestGame.getName() + " - " + bestGame.getAllAverage() + "(" + bestGame.getNumberRated()+")");
        }
    }

    @Test
    public void holasss() throws Exception {
        DataGetterData dataGetterData = dataGetter.getDataFromUser("fdo_claves", null, 5, directory);
        Items items = xmlConverter.convertToItems(dataGetterData);
        DataGetterData dataGetterData2 = dataGetter.getDataFromUser("xobxela", null, 5, directory);
        Items items2 = xmlConverter.convertToItems(dataGetterData2);
        DataGetterData dataGetterData3 = dataGetter.getDataFromUser("agriancito", null, 5, directory);
        Items items3 = xmlConverter.convertToItems(dataGetterData3);

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
