package com.example.forin;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.forin.adapter.OrderFoodAdapter;
import com.example.forin.datamodel.DBOrderDataModel;
import com.example.forin.datamodel.Food;
import com.example.forin.datamodel.Order;
import com.example.forin.firebasemethod.ForinFirebase;

import java.util.ArrayList;
import java.util.Date;

public class PesananActivity extends AppCompatActivity {
    public static final String EXTRA_ITEM = "extra_item";
    private RecyclerView rvOrder;
    private ArrayList<Food> foodList = new ArrayList<>();
    private ArrayList<Order> orderList = new ArrayList<>();
    private ArrayList<Order> orderDB = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesanan);
        Button btnFinal = findViewById(R.id.btn_finalOrder);
        EditText edtNote, edtName, edtTableNum;

        edtName = findViewById(R.id.edt_nama);
        edtNote = findViewById(R.id.edt_catatan);
        edtTableNum = findViewById(R.id.edt_noMeja);
        rvOrder = findViewById(R.id.rv_orders);
        rvOrder.setHasFixedSize(true);


        foodList.addAll(getIntent().getParcelableArrayListExtra(EXTRA_ITEM));
        transferFoodToOrder(foodList);
        showOrderList();

        btnFinal.setOnClickListener(v -> {
            if (edtName.getText().toString().equalsIgnoreCase("")) {
                edtName.setError("Field Harus Diisi");

            } else if (edtTableNum.getText().toString().equalsIgnoreCase("")) {
                edtTableNum.setError("Field Harus Diisi");

            } else if (orderList.isEmpty()) {
                btnFinal.setError("Anda Belum Memesan Makanan");
                Toast.makeText(this, "Anda Belum Memesan Makanan", Toast.LENGTH_SHORT).show();

            } else {
                Date date = new Date();
                String name = edtName.getText().toString();
                String note = edtNote.getText().toString();
                String tableNum = edtTableNum.getText().toString();
                DBOrderDataModel order = new DBOrderDataModel(name, note, tableNum, date, orderList);
                order.setOnProcess(true);
                ForinFirebase DBForin = new ForinFirebase();

                DBForin.add(order).addOnSuccessListener(suc->{
                    Toast.makeText(this, "Pesanan Anda Berhasil Ditambahkan", Toast.LENGTH_SHORT).show();
                }).addOnFailureListener(er->{
                    Toast.makeText(this, "Terjadi Kesalahan Dalam Memasukkan Pesananan Anda", Toast.LENGTH_LONG).show();
                });
            }
        });

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Pesanan");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    private void showOrderList() {
        rvOrder.setLayoutManager(new LinearLayoutManager(this));
        OrderFoodAdapter orderFoodAdapter = new OrderFoodAdapter(orderList, 0);
        rvOrder.setAdapter(orderFoodAdapter);
    }

    public void setOrderDB(ArrayList<Order> orderDB) {
        this.orderDB = orderDB;
    }

    /*buat mindahin list Food ke list Order, cukup bagian nama, jumlah
    * makananya, sama total harga */
    private void transferFoodToOrder (ArrayList<Food> foodList ) {
        try {
            Food foodTemp = new Food();
            for (int i = 0; i < foodList.size(); i++) {
                Order orderTemp = new Order();
                foodTemp = foodList.get(i);
                if (foodTemp.getFoodCount() != 0) {
                    orderTemp.setFoodCount(String.valueOf(foodTemp.getFoodCount()));
                    orderTemp.setFoodName(foodTemp.getTitleFood());
                    orderTemp.setTotalPrice(String.valueOf(foodTemp.getFoodCount() * Double.parseDouble(foodTemp.getPriceFood())));
                    orderList.add(orderTemp);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}