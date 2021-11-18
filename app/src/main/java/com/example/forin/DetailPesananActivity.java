package com.example.forin;

import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.forin.adapter.OrderFoodAdapter;
import com.example.forin.datamodel.DBOrderDataModel;
import com.example.forin.datamodel.Order;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DetailPesananActivity extends AppCompatActivity {
    public static final String EXTRA_ITEM = "extra_item";
    private RecyclerView rvDetailPesanan;
    private ArrayList<DBOrderDataModel> dataList = new ArrayList<>();
    private ArrayList<Order> orderData = new ArrayList<>();
    public DatabaseReference dbRef;
    private FirebaseDatabase db;
    private ArrayList<String> keys = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_pesanan);
        Button btnFinishOrder = findViewById(R.id.btn_finish_order);
        getKey();
        dbRef = FirebaseDatabase.getInstance("https://forin-170e6-default-rtdb.asia-southeast1.firebasedatabase.app")
                .getReference(DBOrderDataModel.class.getSimpleName());

        DBOrderDataModel order = getIntent().getParcelableExtra(EXTRA_ITEM);

        rvDetailPesanan = findViewById(R.id.rv_detail);
        rvDetailPesanan.setHasFixedSize(true);

        dataList.add(order);
        modelToOrder();
        showList();

        btnFinishOrder.setOnClickListener(v -> {
            dbRef.child(keys.get(Integer.parseInt(order.getKey()))).child("onProcess").setValue(false);
            finish();
        });
    }

    public void showList () {
        rvDetailPesanan.setLayoutManager(new LinearLayoutManager(this));
        OrderFoodAdapter orderFoodAdapter = new OrderFoodAdapter(orderData);
        rvDetailPesanan.setAdapter(orderFoodAdapter);
        orderFoodAdapter.notifyDataSetChanged();
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

    public void getKey() {
        db = FirebaseDatabase.getInstance("https://forin-170e6-default-rtdb.asia-southeast1.firebasedatabase.app");
        dbRef = db.getReference(DBOrderDataModel.class.getSimpleName());
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    keys.add(ds.getKey());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}