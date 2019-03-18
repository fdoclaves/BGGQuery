package com.example.fernandoaranaandrade.bggquery.selectBussines;

import com.example.fernandoaranaandrade.bggquery.selectBussines.items.item.Item;

public class GamesRated {

    private Average average;

    public GamesRated(Average average) {
        this.average = average;
    }

    public Double getAllAverage() {
        return average.getAllRakings()/getNumberRated();
    }

    public Integer getNumberRated() {
        return average.getRates();
    }

    public String getName() {
        for (int i = 0; i < average.getItems().size(); i++) {
            if(!average.getItems().isEmpty()){
                return average.getItems().get(i).getName().getName();
            }
        }
        return null;
    }

    public Integer getTotalToWantToPlay() {
        int wantPlay = 0;
        for (Item item : average.getItems()) {
            if(item.getStatus().getWanttoplay()==1 || item.getStatus().getWishlist()==1){
                wantPlay++;
            }
        }
        return wantPlay;
    }

    public boolean hasRating() {
        return getNumberRated() > 0;
    }

    public String getAverage(String user) {
        for (Item item : average.getItems()) {
              if(item.getUser().equals(user)){
                 return item.getStats().getRating().getValue();
              }
        }
        return null;
    }

    public String getWantToPlay(String user) {
        for (Item item : average.getItems()) {
            if(item.getUser().equals(user)){
                String text = null;
                if(item.getStatus().getWanttoplay()==1){
                    text = "Want to play";
                }
                if(item.getStatus().getWishlist()==1){
                    if(text == null){
                        text = "WishList";
                    } else {
                        text = text + ", WishList";
                    }
                }
                return text;
            }
        }
        return null;
    }

}
