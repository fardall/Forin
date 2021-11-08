package com.example.forin.datamodel;

import java.util.ArrayList;
import java.util.Date;

public class DBOrderDataModel {

    private String name;
    private String note;
    private String noMeja;
    private ArrayList<String> foodName = new ArrayList<>();
    private ArrayList<String> totalFood = new ArrayList<>();
    private ArrayList<String> totalPrice = new ArrayList<>();
    private Date date;

    public DBOrderDataModel() {}

    public DBOrderDataModel(String name, String note, String noMeja, Date date, ArrayList<Order> order) {
        this.name = name;
        this.note = note;
        this.noMeja = noMeja;
        this.date = date;
        transferData(order);
    }
    public void transferData (ArrayList<Order> orderArrayListList) {
        Order order = new Order();
        for (int i = 0; i < orderArrayListList.size(); i++) {
            order = orderArrayListList.get(i);
            foodName.add(order.getFoodName());
            totalFood.add(order.getFoodCount());
            totalPrice.add(order.getTotalPrice());
        }
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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
