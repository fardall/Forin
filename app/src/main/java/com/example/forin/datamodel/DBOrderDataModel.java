package com.example.forin.datamodel;

import java.util.ArrayList;
import java.util.Date;

public class DBOrderDataModel {

    private String name;
    private String note;
    private String noMeja;
    private ArrayList<String> foodName, totalFood, totalPrice = new ArrayList<>();
    private Date date;
    private ArrayList<Order> order;

    public DBOrderDataModel() {}

    public DBOrderDataModel(String name, String note, String noMeja, Date date) {
        this.name = name;
        this.note = note;
        this.noMeja = noMeja;
        this.date = date;
    }

    public DBOrderDataModel(String name, String note, String noMeja, Date date, ArrayList<Order> order) {
        this.name = name;
        this.note = note;
        this.noMeja = noMeja;
        this.date = date;
        this.order = order;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public ArrayList<Order> getOrder() {
        return order;
    }

    public void setOrder(ArrayList<Order> order) {
        this.order = order;
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

    public ArrayList<String> getFoodName() {
        return foodName;
    }

    public void setFoodName(ArrayList<String> foodName) {
        this.foodName = foodName;
    }
}
