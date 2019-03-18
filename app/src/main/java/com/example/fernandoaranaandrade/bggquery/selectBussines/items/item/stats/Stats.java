package com.example.fernandoaranaandrade.bggquery.selectBussines.items.item.stats;

import com.example.fernandoaranaandrade.bggquery.selectBussines.items.item.stats.rating.Rating;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;

import java.io.Serializable;

public class Stats implements Serializable{

    @Element(required = false)
    private Rating rating;

    @Attribute(required = false)
    private Double minplayers;

    @Attribute(required = false)
    private Double maxplayers;

    @Attribute(required = false)
    private Double minplaytime;

    @Attribute(required = false)
    private Double maxplaytime;

    @Attribute(required = false)
    private Double playingtime;

    @Attribute(required = false)
    private Double numowned;

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public Double getMinplayers() {
        return minplayers;
    }

    public void setMinplayers(Double minplayers) {
        this.minplayers = minplayers;
    }

    public Double getMaxplayers() {
        return maxplayers;
    }

    public void setMaxplayers(Double maxplayers) {
        this.maxplayers = maxplayers;
    }

    public Double getMinplaytime() {
        return minplaytime;
    }

    public void setMinplaytime(Double minplaytime) {
        this.minplaytime = minplaytime;
    }

    public Double getMaxplaytime() {
        return maxplaytime;
    }

    public void setMaxplaytime(Double maxplaytime) {
        this.maxplaytime = maxplaytime;
    }

    public Double getPlayingtime() {
        return playingtime;
    }

    public void setPlayingtime(Double playingtime) {
        this.playingtime = playingtime;
    }

    public Double getNumowned() {
        return numowned;
    }

    public void setNumowned(Double numowned) {
        this.numowned = numowned;
    }
}
