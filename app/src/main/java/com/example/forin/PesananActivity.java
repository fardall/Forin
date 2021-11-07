package com.example.forin;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.forin.datamodel.OrderDataModel;

import java.util.ArrayList;

public class PesananActivity extends AppCompatActivity {
    public static final String EXTRA_ITEM = "extra_item";
    private RecyclerView rvOrder;
    private ArrayList<Food> foodList = new ArrayList<>();

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
        showOrderList();

        btnFinal.setOnClickListener(v -> {
            String name = edtName.getText().toString();
            String note = edtNote.getText().toString();
            String tabelNum = edtTableNum.getText().toString();
            OrderDataModel order = new OrderDataModel(name, note, tabelNum);
            ForinFirebase DBForin = new ForinFirebase();

            DBForin.add(order).addOnSuccessListener(suc->{
                Toast.makeText(this, "Pesanan Anda Berhasil Ditambahkan", Toast.LENGTH_SHORT).show();
            }).addOnFailureListener(er->{
                Toast.makeText(this, "Terjadi Kesalahan Dalam Memasukkan Pesananan Anda", Toast.LENGTH_LONG).show();
            });
        });
    }

    private void showOrderList() {
        rvOrder.setLayoutManager(new LinearLayoutManager(this));
        OrderFoodAdapter orderFoodAdapter = new OrderFoodAdapter(foodList);
        rvOrder.setAdapter(orderFoodAdapter);
    }
}