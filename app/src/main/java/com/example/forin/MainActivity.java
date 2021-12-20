package com.example.forin;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.forin.adapter.ListFoodAdapter;
import com.example.forin.datamodel.Food;
import com.example.forin.datamodel.FoodData;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rvFoods;
    private ArrayList<Food> foodList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.title_layout);
        setContentView(R.layout.activity_main);

        Button btnPesan = findViewById(R.id.btn_pesan);
        TextView tvTitle = findViewById(R.id.tvTitle);

        rvFoods = findViewById(R.id.rv_foods);
        rvFoods.setHasFixedSize(true);

        foodList.addAll(FoodData.getListData());
        showFoodList();

        btnPesan.setOnClickListener(v -> {
            Intent moveToPesanan = new Intent(MainActivity.this, PesananActivity.class);
            moveToPesanan.putParcelableArrayListExtra(PesananActivity.EXTRA_ITEM, foodList);
            startActivity(moveToPesanan);
        });
    }

    private void showFoodList() {
        rvFoods.setLayoutManager(new LinearLayoutManager(this));
        ListFoodAdapter listFoodAdapter = new ListFoodAdapter(foodList);
        rvFoods.setAdapter(listFoodAdapter);
    }
}