package com.test.marketpulse.model;

import java.util.List;

public class Scan {

    private long id;
    private String name;
    private String tag;
    private String color;
    private List<Object> criteria;

    public Scan(long id, String name, String tag, String color, List<Object> criteria) {
        this.id = id;
        this.name = name;
        this.tag = tag;
        this.color = color;
        this.criteria = criteria;
    }

    public Scan() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public List<Object> getCriteria() {
        return criteria;
    }

    public void setCriteria(List<Object> criteria) {
        this.criteria = criteria;
    }
}
