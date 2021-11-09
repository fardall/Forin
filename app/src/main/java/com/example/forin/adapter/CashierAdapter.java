package com.example.forin.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.forin.R;
import com.example.forin.datamodel.DBOrderDataModel;

import java.util.ArrayList;

public class CashierAdapter extends RecyclerView.Adapter<CashierAdapter.ViewHolder> {
    private ArrayList<DBOrderDataModel> listData;

    public CashierAdapter(ArrayList<DBOrderDataModel> listData) {
        this.listData = listData;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_kasir, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvNoPesanan, tvNoMeja;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNoPesanan = itemView.findViewById(R.id.no_pesanan);
            tvNoMeja = itemView.findViewById(R.id.no_meja);
        }
    }
}
