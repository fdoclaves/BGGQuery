package com.faaya.fernandoaranaandrade.bggquery.selectBussines.items.item;

import com.faaya.fernandoaranaandrade.bggquery.selectBussines.items.item.stats.Stats;
import com.faaya.fernandoaranaandrade.bggquery.selectBussines.items.item.status.Status;


import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import java.io.Serializable;

@Root
public class Item implements Serializable {

    @Attribute(required = false)
    private String objecttype;

    @Attribute(required = false)
    private Long objectid;

    @Attribute(required = false)
    private String subtype;

    @Attribute(required = false)
    private Long collid;

    @Element(required = false)
    private Name name;

    @Element(required = false)
    private String yearpublished;

    @Element(required = false)
    private String image;

    @Element(required = false)
    private String thumbnail;

    @Element(required = false)
    private Stats stats;

    @Element(required = false)
    private Status status;

    @Element(required = false)
    private Long numplays;

    @Element(required = false)
    private String comment;

    @Element(required = false)
    private String originalname;

    @Element(required = false)
    private String haspartslist;

    @Element(required = false)
    private String wishlistcomment;

    private String user;

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public String getYearpublished() {
        return yearpublished;
    }

    public void setYearpublished(String yearpublished) {
        this.yearpublished = yearpublished;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public Stats getStats() {
        return stats;
    }

    public void setStats(Stats stats) {
        this.stats = stats;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Long getNumplays() {
        return numplays;
    }

    public void setNumplays(Long numplays) {
        this.numplays = numplays;
    }

    public Long getObjectid() {
        return objectid;
    }

    public void setObjectid(Long objectid) {
        this.objectid = objectid;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getUser() {
        return user;
    }

    public String getObjecttype() {
        return objecttype;
    }

    public void setObjecttype(String objecttype) {
        this.objecttype = objecttype;
    }

    public String getSubtype() {
        return subtype;
    }

    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }

    public Long getCollid() {
        return collid;
    }

    public void setCollid(Long collid) {
        this.collid = collid;
    }

    public String getOriginalname() {
        return originalname;
    }

    public void setOriginalname(String originalname) {
        this.originalname = originalname;
    }

    public String getWishlistcomment() {
        return wishlistcomment;
    }

    public void setWishlistcomment(String wishlistcomment) {
        this.wishlistcomment = wishlistcomment;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getHaspartslist() {
        return haspartslist;
    }

    public void setHaspartslist(String haspartslist) {
        this.haspartslist = haspartslist;
    }
}
