package com.example.fernandoaranaandrade.bggquery.selectBussines;

import java.util.ArrayList;
import java.util.List;

public class Result {

    private List<GamesRated> allGames;
    private List<GamesRated> ratingGames;
    private List<GamesRated> wantToPlayGames;

    public Result(List<GamesRated> allGames) {
        this.allGames = allGames;
        this.ratingGames = new ArrayList<>();
        this.wantToPlayGames = new ArrayList<>();
        for (GamesRated gamesRated : allGames) {
            if (gamesRated.hasRating()) {
                ratingGames.add(gamesRated);
            } else if (gamesRated.getTotalToWantToPlay() > 0) {
                wantToPlayGames.add(gamesRated);
            }
        }
    }

    public List<GamesRated> getAllGames() {
        return allGames;
    }

    public List<GamesRated> getRatingGames() {
        return ratingGames;
    }

    public List<GamesRated> getWantToPlayGames() {
        return wantToPlayGames;
    }
}
