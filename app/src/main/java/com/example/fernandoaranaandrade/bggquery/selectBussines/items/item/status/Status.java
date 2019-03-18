package com.example.fernandoaranaandrade.bggquery.selectBussines.items.item.status;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

import java.io.Serializable;

@Root
public class Status implements Serializable{

    @Attribute(required = false)
    Integer own;

    @Attribute(required = false)
    Integer prevowned;

    @Attribute(required = false)
    Integer fortrade;

    @Attribute(required = false)
    Integer want;

    @Attribute(required = false)
    Integer wanttoplay;

    @Attribute(required = false)
    Integer wanttobuy;

    @Attribute(required = false)
    Integer wishlist;

    @Attribute(required = false)
    Integer wishlistpriority;

    @Attribute(required = false)
    Integer preordered;

    @Attribute(required = false)
    String lastmodified;

    public Integer getOwn() {
        return own;
    }

    public void setOwn(Integer own) {
        this.own = own;
    }

    public Integer getPrevowned() {
        return prevowned;
    }

    public void setPrevowned(Integer prevowned) {
        this.prevowned = prevowned;
    }

    public Integer getFortrade() {
        return fortrade;
    }

    public void setFortrade(Integer fortrade) {
        this.fortrade = fortrade;
    }

    public Integer getWant() {
        return want;
    }

    public void setWant(Integer want) {
        this.want = want;
    }

    public Integer getWanttoplay() {
        return wanttoplay;
    }

    public void setWanttoplay(Integer wanttoplay) {
        this.wanttoplay = wanttoplay;
    }

    public Integer getWanttobuy() {
        return wanttobuy;
    }

    public void setWanttobuy(Integer wanttobuy) {
        this.wanttobuy = wanttobuy;
    }

    public Integer getWishlist() {
        return wishlist;
    }

    public void setWishlist(Integer wishlist) {
        this.wishlist = wishlist;
    }

    public Integer getWishlistpriority() {
        return wishlistpriority;
    }

    public void setWishlistpriority(Integer wishlistpriority) {
        this.wishlistpriority = wishlistpriority;
    }

    public Integer getPreordered() {
        return preordered;
    }

    public void setPreordered(Integer preordered) {
        this.preordered = preordered;
    }

    public String getLastmodified() {
        return lastmodified;
    }

    public void setLastmodified(String lastmodified) {
        this.lastmodified = lastmodified;
    }
}
