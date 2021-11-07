package com.example.forin.datamodel;

import java.util.ArrayList;

public class DBOrderDataModel {

    private String name;
    private String note;
    private String noMeja;
    private ArrayList<String> foodName, totalFood, totalPrice = new ArrayList<>();

    public DBOrderDataModel() {}

    public DBOrderDataModel(String name, String note, String noMeja) {
        this.name = name;
        this.note = note;
        this.noMeja = noMeja;
    }

    public DBOrderDataModel(String name, String note, String noMeja, ArrayList<Order> order) {
        this.name = name;
        this.note = note;
        this.noMeja = noMeja;
        for (int i = 0; i < order.size(); i++) {
            Order orderTemp = new Order();
            orderTemp = order.get(i);
            foodName.add(orderTemp.getFoodName());
            totalFood.add(orderTemp.getFoodCount());
            totalPrice.add(orderTemp.getTotalPrice());
        }
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
