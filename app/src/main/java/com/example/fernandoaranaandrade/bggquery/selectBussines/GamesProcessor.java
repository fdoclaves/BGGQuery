package com.example.fernandoaranaandrade.bggquery.selectBussines;

import com.example.fernandoaranaandrade.bggquery.selectBussines.items.Items;
import com.example.fernandoaranaandrade.bggquery.selectBussines.items.item.Item;

import java.util.*;

public class GamesProcessor {

    public Result bestGame(Items... items) {

        Map<Long, Item> all = new HashMap<>();

        List<Map<Long, Item>> maps = new ArrayList<>();

        List<GamesRated> someGamesRatedList = new ArrayList<GamesRated>();

        for (Items userItems : items) {
            if(userItems != null && userItems.getItem() != null){
                Map<Long, Item> map = new HashMap<>();
                for (Item item : userItems.getItem()) {
                    item.setUser(userItems.getUser());
                    map.put(item.getObjectid(), item);
                    all.put(item.getObjectid(), item);
                }
                maps.add(map);
            }
        }
        for (Map.Entry<Long, Item> entry : all.entrySet()) {
            Long objectid = entry.getKey();
            Average average = getAverage(maps,objectid);
            someGamesRatedList.add(new GamesRated(average));
        }
        Collections.sort(someGamesRatedList, new Comparator() {
            @Override
            public int compare(Object item, Object item2) {
                return ((GamesRated)item2).getTotalToWantToPlay()
                        .compareTo(((GamesRated)item).getTotalToWantToPlay());
            }
        });

        Collections.sort(someGamesRatedList, new Comparator() {
            @Override
            public int compare(Object item, Object item2) {
                return ((GamesRated)item2).getAllAverage()
                        .compareTo(((GamesRated)item).getAllAverage());
            }
        });
        Collections.sort(someGamesRatedList, new Comparator() {
            @Override
            public int compare(Object item, Object item2) {
                return ((GamesRated)item2).getNumberRated()
                        .compareTo(((GamesRated)item).getNumberRated());
            }
        });
        return new Result(someGamesRatedList);
    }

    private Average getAverage(List<Map<Long, Item>> maps, Long objectid) {
        Average average = new Average();
        for (Map<Long, Item> map : maps) {
            Item item = map.get(objectid);
            if(item != null){
                if(!isNA(item)){
                    average.add(Double.parseDouble(item.getStats().getRating().getValue()));
                    average.addRates();
                }
                average.add(item);
            }
        }
        return average;
    }

    private boolean isNA(Item item) {
        return item.getStats().getRating().getValue().equalsIgnoreCase("N/A");
    }
}