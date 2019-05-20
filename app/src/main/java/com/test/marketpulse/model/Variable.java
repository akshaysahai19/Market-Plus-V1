package com.test.marketpulse.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Variable {

    @SerializedName("type")
    private String type;
    @SerializedName("values")
    private List<Double> valuesList;
    @SerializedName("study_type")
    private String study_type;
    @SerializedName("parameter_name")
    private String parameter_name;
    @SerializedName("min_value")
    private long min_value;
    @SerializedName("max_value")
    private long max_value;
    @SerializedName("default_value")
    private long default_value;

    public Variable(String type, List<Double> valuesList, String study_type, String parameter_name,
                    long min_value, long max_value, long default_value) {
        this.type = type;
        this.valuesList = valuesList;
        this.study_type = study_type;
        this.parameter_name = parameter_name;
        this.min_value = min_value;
        this.max_value = max_value;
        this.default_value = default_value;
    }

    public Variable() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Double> getValuesList() {
        return valuesList;
    }

    public void setValuesList(List<Double> valuesList) {
        this.valuesList = valuesList;
    }

    public String getStudy_type() {
        return study_type;
    }

    public void setStudy_type(String study_type) {
        this.study_type = study_type;
    }

    public String getParameter_name() {
        return parameter_name;
    }

    public void setParameter_name(String parameter_name) {
        this.parameter_name = parameter_name;
    }

    public long getMin_value() {
        return min_value;
    }

    public void setMin_value(long min_value) {
        this.min_value = min_value;
    }

    public long getMax_value() {
        return max_value;
    }

    public void setMax_value(long max_value) {
        this.max_value = max_value;
    }

    public long getDefault_value() {
        return default_value;
    }

    public void setDefault_value(long default_value) {
        this.default_value = default_value;
    }
}
