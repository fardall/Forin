package com.example.forin.datamodel;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Date;

public class DBOrderDataModel implements Parcelable {

    private String name;
    private String note;
    private String noMeja;
    private String noPesanan;
    private String key;
    private ArrayList<String> foodName = new ArrayList<>();
    private ArrayList<String> totalFood = new ArrayList<>();
    private ArrayList<String> totalPrice = new ArrayList<>();
    private Date date;
    private boolean onProcess;

    public DBOrderDataModel() {}

    public DBOrderDataModel(String name, String note, String noMeja, ArrayList<String> foodName, ArrayList<String> totalFood, ArrayList<String> totalPrice, Date date) {
        this.name = name;
        this.note = note;
        this.noMeja = noMeja;
        this.foodName = foodName;
        this.totalFood = totalFood;
        this.totalPrice = totalPrice;
        this.date = date;
    }

    public DBOrderDataModel(String name, String note, String noMeja) {
        this.name = name;
        this.note = note;
        this.noMeja = noMeja;
    }

    public DBOrderDataModel(String name, String note, String noMeja, Date date, ArrayList<Order> order) {
        this.name = name;
        this.note = note;
        this.noMeja = noMeja;
        this.date = date;
        transferData(order);
    }


    protected DBOrderDataModel(Parcel in) {
        name = in.readString();
        note = in.readString();
        noMeja = in.readString();
        noPesanan = in.readString();
        key = in.readString();
        foodName = in.createStringArrayList();
        totalFood = in.createStringArrayList();
        totalPrice = in.createStringArrayList();
        onProcess = in.readByte() != 0;
    }

    public static final Creator<DBOrderDataModel> CREATOR = new Creator<DBOrderDataModel>() {
        @Override
        public DBOrderDataModel createFromParcel(Parcel in) {
            return new DBOrderDataModel(in);
        }

        @Override
        public DBOrderDataModel[] newArray(int size) {
            return new DBOrderDataModel[size];
        }
    };

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

    public String getNoPesanan() {
        return noPesanan;
    }

    public void setNoPesanan(String noPesanan) {
        this.noPesanan = noPesanan;
    }

    public boolean isOnProcess() {
        return onProcess;
    }

    public void setOnProcess(boolean onProcess) {
        this.onProcess = onProcess;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(note);
        dest.writeString(noMeja);
        dest.writeString(noPesanan);
        dest.writeString(key);
        dest.writeStringList(foodName);
        dest.writeStringList(totalFood);
        dest.writeStringList(totalPrice);
        dest.writeByte((byte) (onProcess ? 1 : 0));
    }
}
