package com.example.forin.datamodel;

public class Order {

    private String foodName, foodCount, totalPrice;

    public Order () {}

    public Order(String foodName, String foodCount, String totalPrice) {
        this.foodName = foodName;
        this.foodCount = foodCount;
        this.totalPrice = totalPrice;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getFoodCount() {
        return foodCount;
    }

    public void setFoodCount(String foodCount) {
        this.foodCount = foodCount;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }
}
