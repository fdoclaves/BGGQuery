package com.faaya.fernandoaranaandrade.bggquery.selectBussines.items.item.stats.rating;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import java.io.Serializable;

@Root
public class Rating implements Serializable{

    @Element
    private Usersrated usersrated;

    @Element
    Average average;

    @Element
    Bayesaverage bayesaverage;

    @Element
    Stddev stddev;

    @Element
    Median median;

    @Attribute
    String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Usersrated getUsersrated() {
        return usersrated;
    }

    public void setUsersrated(Usersrated usersrated) {
        this.usersrated = usersrated;
    }

    public Average getAverage() {
        return average;
    }

    public void setAverage(Average average) {
        this.average = average;
    }

    public Bayesaverage getBayesaverage() {
        return bayesaverage;
    }

    public void setBayesaverage(Bayesaverage bayesaverage) {
        this.bayesaverage = bayesaverage;
    }

    public Stddev getStddev() {
        return stddev;
    }

    public void setStddev(Stddev stddev) {
        this.stddev = stddev;
    }

    public Median getMedian() {
        return median;
    }

    public void setMedian(Median median) {
        this.median = median;
    }
}
