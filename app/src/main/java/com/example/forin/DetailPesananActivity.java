package com.example.forin;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.Button;
import android.widget.TextView;

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
import java.util.Locale;

public class DetailPesananActivity extends AppCompatActivity {
    public static final String EXTRA_ITEM = "extra_item";
    private RecyclerView rvDetailPesanan;
    private ArrayList<DBOrderDataModel> dataList = new ArrayList<>();
    private ArrayList<Order> orderData = new ArrayList<>();
    public DatabaseReference dbRef;
    private FirebaseDatabase db;
    private ArrayList<String> keys = new ArrayList<>();

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_pesanan);
        Button btnFinishOrder = findViewById(R.id.btn_finish_order);

        getKey();

        DBOrderDataModel order = getIntent().getParcelableExtra(EXTRA_ITEM);

        rvDetailPesanan = findViewById(R.id.rv_detail);
        rvDetailPesanan.setHasFixedSize(true);

        TextView tvName = findViewById(R.id.tvName);
        TextView tvNote = findViewById(R.id.tvNote);
        tvName.setText("Nama : " + order.getName());
        tvNote.setText("Catatan : " + order.getNote());
        tvNote.setMovementMethod(new ScrollingMovementMethod());

        TextView tvTotalHarga = findViewById(R.id.total_harga);
        tvTotalHarga.setText(String.format(Locale.ENGLISH,"%,d",
                Integer.parseInt(order.getTotal())).replace(',','.'));

        dataList.add(order);
        modelToOrder();
        showList();

        btnFinishOrder.setOnClickListener(v -> {
            dbRef.child(keys.get(Integer.parseInt(order.getKey()))).child("onProcess").setValue(false);
            finish();
        });

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Detail Pesanan");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    public void showList() {
        rvDetailPesanan.setLayoutManager(new LinearLayoutManager(this));
        OrderFoodAdapter orderFoodAdapter = new OrderFoodAdapter(orderData);
        rvDetailPesanan.setAdapter(orderFoodAdapter);
    }

    public void modelToOrder() {
        for (int i = 0; i < dataList.get(0).getTotalPrice().size(); i++) {
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