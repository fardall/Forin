package com.example.forin;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.forin.adapter.OrderFoodAdapter;
import com.example.forin.datamodel.DBOrderDataModel;
import com.example.forin.datamodel.Order;

import java.util.ArrayList;

public class DetailPesananActivity extends AppCompatActivity {
    public static final String EXTRA_ITEM = "extra_item";
    private RecyclerView rvDetailPesanan;
    private ArrayList<DBOrderDataModel> dataList = new ArrayList<>();
    private ArrayList<Order> orderData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_pesanan);

        DBOrderDataModel order = getIntent().getParcelableExtra(EXTRA_ITEM);

        rvDetailPesanan = findViewById(R.id.rv_detail);
        rvDetailPesanan.setHasFixedSize(true);

        dataList.add(order);
        modelToOrder();
        showList();

    }

    public void showList () {
        rvDetailPesanan.setLayoutManager(new LinearLayoutManager(this));
        OrderFoodAdapter orderFoodAdapter = new OrderFoodAdapter(orderData);
        rvDetailPesanan.setAdapter(orderFoodAdapter);
    }

    public void modelToOrder () {
        for (int i = 0; i < dataList.get(0).getTotalPrice().size() ; i++) {
            Order order = new Order();
            order.setFoodName(dataList.get(0).getFoodName().get(i));
            order.setTotalPrice(dataList.get(0).getTotalPrice().get(i));
            order.setFoodCount(dataList.get(0).getTotalFood().get(i));
            orderData.add(order);
        }
    }
}