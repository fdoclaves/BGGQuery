package com.example.fernandoaranaandrade.bggquery.selectBussines.HotItems;


import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root
public class HotItem {

    @Attribute(required = false)
    private Long id;

    @Attribute(required = false)
    private Long rank;

    @Element(required = false)
    private HotElement name;

    @Element(required = false)
    private HotElement yearpublished;

    @Element(required = false)
    private HotElement thumbnail;

    public HotElement getName() {
        return name;
    }

    public void setName(HotElement name) {
        this.name = name;
    }

    public HotElement getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(HotElement thumbnail) {
        this.thumbnail = thumbnail;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRank() {
        return rank;
    }

    public void setRank(Long rank) {
        this.rank = rank;
    }

    public HotElement getYearpublished() {
        return yearpublished;
    }

    public void setYearpublished(HotElement yearpublished) {
        this.yearpublished = yearpublished;
    }
}
