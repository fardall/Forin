package com.example.forin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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
import java.util.Locale;

public class PesananActivity extends AppCompatActivity {
    public static final String EXTRA_ITEM = "extra_item";
    private RecyclerView rvOrder;
    private ArrayList<Food> foodList = new ArrayList<>();
    private ArrayList<Order> orderList = new ArrayList<>();
    private ArrayList<Order> orderDB = new ArrayList<>();
    private String noMeja = "", totalharga;
    private RadioGroup rdgMeja1, rdgMeja2;
    private RadioButton rdButMeja1, rdButMeja2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.title_layout_pesanan_activity);
        setContentView(R.layout.activity_pesanan);

        setContentView(R.layout.activity_pesanan);
        Button btnFinal = findViewById(R.id.btn_finalOrder);
        EditText edtNote, edtName;
        TextView tvTotal;

        edtName = findViewById(R.id.edt_nama);
        edtNote = findViewById(R.id.edt_catatan);
        rvOrder = findViewById(R.id.rv_orders);
        rvOrder.setHasFixedSize(true);
        tvTotal = findViewById(R.id.tv_total);
        rdgMeja1 = findViewById(R.id.rdg_btnMeja);
        rdgMeja2 = findViewById(R.id.rdg_btnMeja2);


        foodList.addAll(getIntent().getParcelableArrayListExtra(EXTRA_ITEM));
        transferFoodToOrder(foodList);
        showOrderList();

        String tvTotalHarga = "     Total: \n" + "Rp. " + String.format(Locale.ENGLISH,"%,d",
                Integer.parseInt(totalharga)).replace(',','.');
        tvTotal.setText(tvTotalHarga);

        rdgMeja1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId != -1) {
                    rdgMeja2.clearCheck();
                    rdgMeja1.check(checkedId);
                    rdButMeja1 = findViewById(checkedId);
                    noMeja = rdButMeja1.getText().toString();
                }
            }
        });

        rdgMeja2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId != -1) {
                    rdgMeja1.clearCheck();
                    rdgMeja2.check(checkedId);
                    rdButMeja2 = findViewById(checkedId);
                    noMeja = rdButMeja2.getText().toString();
                }
            }
        });

        btnFinal.setOnClickListener(v -> {
            btnFinal.setEnabled(false);

            if (edtName.getText().toString().equalsIgnoreCase("")) {
                edtName.setError("Field Harus Diisi");
                btnFinal.setEnabled(true);

            } else if (noMeja == "") {
                Toast.makeText(this, "Pilih No Meja", Toast.LENGTH_SHORT).show();
                btnFinal.setEnabled(true);

            } else if (orderList.isEmpty()) {
                btnFinal.setError("Anda Belum Memesan Makanan");
                Toast.makeText(this, "Anda Belum Memesan Makanan", Toast.LENGTH_SHORT).show();
                btnFinal.setEnabled(true);
            } else {
                Date date = new Date();
                String name = edtName.getText().toString();
                String note = edtNote.getText().toString();
                DBOrderDataModel order = new DBOrderDataModel(name, note, noMeja, date, orderList, totalharga);
                order.setOnProcess(true);
                ForinFirebase DBForin = new ForinFirebase();

                DBForin.add(order).addOnSuccessListener(suc -> {
                    Toast.makeText(this, "Pesanan Anda Berhasil Ditambahkan", Toast.LENGTH_SHORT).show();
                    finish();
                    startActivity(new Intent(PesananActivity.this, Keterangan.class));
                }).addOnFailureListener(er -> {
                    Toast.makeText(this, "Terjadi Kesalahan Dalam Memasukkan Pesananan Anda", Toast.LENGTH_LONG).show();
                });
            }
        });

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
            int totalHargaOrder = 0;
            for (int i = 0; i < foodList.size(); i++) {
                Order orderTemp = new Order();
                foodTemp = foodList.get(i);
                if (foodTemp.getFoodCount() != 0) {
                    orderTemp.setFoodCount(String.valueOf(foodTemp.getFoodCount()));
                    orderTemp.setFoodName(foodTemp.getTitleFood());
                    orderTemp.setTotalPrice(String.valueOf(foodTemp.getFoodCount() * Integer.parseInt(foodTemp.getPriceFood())));
                    totalHargaOrder += foodTemp.getFoodCount() * Integer.parseInt(foodTemp.getPriceFood());
                    orderList.add(orderTemp);
                }
            }
            totalharga = String.valueOf(totalHargaOrder);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


}