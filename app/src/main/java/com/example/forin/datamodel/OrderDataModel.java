package com.example.forin.datamodel;

import com.example.forin.Food;

import java.util.ArrayList;

public class OrderDataModel {

    private String name;
    private String note;
    private String noMeja;
    private ArrayList<Food> food = new ArrayList<>();
    private ArrayList<String> foodName, totalFood, totalPrice = new ArrayList<>();

    public OrderDataModel () {}

    public OrderDataModel(String name, String note, String noMeja) {
        this.name = name;
        this.note = note;
        this.noMeja = noMeja;
//        Food foodTemp = new Food();
//        for (int i = 0; i < food.size(); i++) {
//            foodTemp = food.get(i);
//            foodName.add(foodTemp.getTitleFood());
//            totalFood.add(String.valueOf(foodTemp.getFoodCount()));
//            totalPrice.add(foodTemp.getPriceFood());
//        }
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

    public ArrayList<Food> getFood() {
        return food;
    }

    public void setFood(ArrayList<Food> food) {
        this.food = food;
    }

    public ArrayList<String> getFoodName() {
        return foodName;
    }

    public void setFoodName(ArrayList<String> foodName) {
        this.foodName = foodName;
    }
}
