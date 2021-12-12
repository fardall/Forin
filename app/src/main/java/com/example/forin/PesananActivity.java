package com.example.forin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
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

public class PesananActivity extends AppCompatActivity implements View.OnClickListener{
    public static final String EXTRA_ITEM = "extra_item";
    private RecyclerView rvOrder;
    private ArrayList<Food> foodList = new ArrayList<>();
    private ArrayList<Order> orderList = new ArrayList<>();
    private ArrayList<Order> orderDB = new ArrayList<>();
    private String noMeja = "", totalharga;
    private Button btnMeja1, btnMeja2, btnMeja3, btnMeja4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.title_layout_pesanan_activity);
        setContentView(R.layout.activity_pesanan);

        setContentView(R.layout.activity_pesanan);
        Button btnFinal = findViewById(R.id.btn_finalOrder);
        EditText edtNote, edtName, edtTableNum;
        TextView tvTotal;

        edtName = findViewById(R.id.edt_nama);
        edtNote = findViewById(R.id.edt_catatan);
        rvOrder = findViewById(R.id.rv_orders);
        rvOrder.setHasFixedSize(true);
        btnMeja1 = findViewById(R.id.btn_noMeja1);
        btnMeja1.setOnClickListener(this);
        btnMeja2 = findViewById(R.id.btn_noMeja2);
        btnMeja2.setOnClickListener(this);
        btnMeja3 = findViewById(R.id.btn_noMeja3);
        btnMeja3.setOnClickListener(this);
        btnMeja4 = findViewById(R.id.btn_noMeja4);
        btnMeja4.setOnClickListener(this);
        tvTotal = findViewById(R.id.tv_total);

        foodList.addAll(getIntent().getParcelableArrayListExtra(EXTRA_ITEM));
        transferFoodToOrder(foodList);
        showOrderList();

        String tvTotalHarga = "     Total: \n" + "Rp. " + totalharga;
        tvTotal.setText(tvTotalHarga);

        btnFinal.setOnClickListener(v -> {
            if (edtName.getText().toString().equalsIgnoreCase("")) {
                edtName.setError("Field Harus Diisi");

            } else if (noMeja == "") {
                Toast.makeText(this, "Pilih No Meja", Toast.LENGTH_SHORT).show();

            } else if (orderList.isEmpty()) {
                btnFinal.setError("Anda Belum Memesan Makanan");
                Toast.makeText(this, "Anda Belum Memesan Makanan", Toast.LENGTH_SHORT).show();

            } else {
                Date date = new Date();
                String name = edtName.getText().toString();
                String note = edtNote.getText().toString();
                String tableNum = noMeja;
                DBOrderDataModel order = new DBOrderDataModel(name, note, tableNum, date, orderList, totalharga);
                order.setOnProcess(true);
                ForinFirebase DBForin = new ForinFirebase();

                DBForin.add(order).addOnSuccessListener(suc->{
                    Toast.makeText(this, "Pesanan Anda Berhasil Ditambahkan", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(PesananActivity.this, Keterangan.class));
                }).addOnFailureListener(er->{
                    Toast.makeText(this, "Terjadi Kesalahan Dalam Memasukkan Pesananan Anda", Toast.LENGTH_LONG).show();
                });
            }
        });

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_noMeja1:
                noMeja = "1";
                break;
            case R.id.btn_noMeja2:
                noMeja = "2";
                break;
            case R.id.btn_noMeja3:
                noMeja = "3";
                break;
            case R.id.btn_noMeja4:
                noMeja = "4";
                break;
        }

    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    /**
     * Manggil adapter
     */
    private void showOrderList() {
        rvOrder.setLayoutManager(new LinearLayoutManager(this));
        OrderFoodAdapter orderFoodAdapter = new OrderFoodAdapter(orderList, 0);
        rvOrder.setAdapter(orderFoodAdapter);
    }

    public void setOrderDB(ArrayList<Order> orderDB) {
        this.orderDB = orderDB;
    }

    /**
     * buat mindahin list Food ke list Order, cukup bagian nama, jumlah
     * makananya, sama total harga
     * @param foodList
     */
    private void transferFoodToOrder (ArrayList<Food> foodList ) {
        try {
            Food foodTemp = new Food();
            double totalHargaOrder = 0;
            for (int i = 0; i < foodList.size(); i++) {
                Order orderTemp = new Order();
                foodTemp = foodList.get(i);
                if (foodTemp.getFoodCount() != 0) {
                    orderTemp.setFoodCount(String.valueOf(foodTemp.getFoodCount()));
                    orderTemp.setFoodName(foodTemp.getTitleFood());
                    orderTemp.setTotalPrice(String.valueOf(foodTemp.getFoodCount() * Double.parseDouble(foodTemp.getPriceFood())));
                    totalHargaOrder += foodTemp.getFoodCount() * Double.parseDouble(foodTemp.getPriceFood());
                    orderList.add(orderTemp);
                }
            }
            totalharga = String.valueOf(totalHargaOrder)+"00";
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


}