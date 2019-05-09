package com.example.myapplication.model;


import java.io.Serializable;
import java.util.List;

import com.google.gson.annotations.SerializedName;

public class Scan implements Serializable {

    @SerializedName("id")
    private Integer id;

    @SerializedName("name")
    private String name;

    @SerializedName("tag")
    private String tag;

    @SerializedName("color")
    private String color;

    @SerializedName("criteria")
    private List<Criterium> criteria = null;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public List<Criterium> getCriteria() {
        return criteria;
    }

    public void setCriteria(List<Criterium> criteria) {
        this.criteria = criteria;
    }

}
