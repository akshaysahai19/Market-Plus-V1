package com.test.marketpulse.model;

import java.io.Serializable;

public class Criteria implements Serializable {

    String type;
    String text;

    public Criteria() {
    }

    public Criteria(String type, String text) {
        this.type = type;
        this.text = text;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
