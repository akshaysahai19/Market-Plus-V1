package com.test.marketpulse.model;

import android.text.Spannable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Criteria implements Serializable {

    private String type;
    private String text;
    private Spannable updatedText;
    private List<Variable> variables;

    public Criteria() {
    }

    public Criteria(String type, String text, Spannable updatedText, List<Variable> variables) {
        this.type = type;
        this.text = text;
        this.updatedText = updatedText;
        this.variables = variables;
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

    public Spannable getUpdatedText() {
        return updatedText;
    }

    public void setUpdatedText(Spannable updatedText) {
        this.updatedText = updatedText;
    }

    public List<Variable> getVariables() {
        return variables;
    }

    public void setVariables(List<Variable> variables) {
        this.variables = variables;
    }
}
