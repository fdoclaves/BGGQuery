package com.faaya.fernandoaranaandrade.bggquery.selectBussines;

public class GamesRatedTitle extends GamesRated {

    private String title;
    private Integer numberRates;

    public GamesRatedTitle(Average average, String title, Integer numberRates) {
        super(average);
        this.title = title;
        this.numberRates = numberRates;
    }

    @Override
    public Double getAllAverage() {
        return 10d;
    }

    @Override
    public Integer getNumberRated() {
        return numberRates;
    }

    @Override
    public String getName() {
        return title;
    }

    @Override
    public Integer getTotalToWantToPlay() {
        return 0;
    }

    @Override
    public boolean hasRating() {
        return true;
    }

    @Override
    public String getAverage(String user) {
        return "10";
    }

    @Override
    public String getWantToPlay(String user) {
        return null;
    }

    @Override
    public Boolean isTitle() {
        return true;
    }

}
