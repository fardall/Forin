package com.example.forin.datamodel;

import java.util.ArrayList;

public class OrderDataModel {

    private String name;
    private String note;
    private String noMeja;
    private ArrayList<String> food = new ArrayList<>();
    private ArrayList<String> totalFood = new ArrayList<>();
    private ArrayList<String> totalPrice = new ArrayList<>();

    public OrderDataModel () {};

    public OrderDataModel(String name, String note, String noMeja, ArrayList<String> food, ArrayList<String> totalFood, ArrayList<String> totalPrice) {
        this.name = name;
        this.note = note;
        this.noMeja = noMeja;
        this.food = food;
        this.totalFood = totalFood;
        this.totalPrice = totalPrice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getNoMeja() {
        return noMeja;
    }

    public void setNoMeja(String noMeja) {
        this.noMeja = noMeja;
    }

    public ArrayList<String> getFood() {
        return food;
    }

    public void setFood(ArrayList<String> food) {
        this.food = food;
    }

    public ArrayList<String> getTotalFood() {
        return totalFood;
    }

    public void setTotalFood(ArrayList<String> totalFood) {
        this.totalFood = totalFood;
    }

    public ArrayList<String> getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(ArrayList<String> totalPrice) {
        this.totalPrice = totalPrice;
    }
}
