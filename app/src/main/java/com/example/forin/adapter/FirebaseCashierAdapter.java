package com.example.forin.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.forin.R;
import com.example.forin.datamodel.DBOrderDataModel;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class FirebaseCashierAdapter extends FirebaseRecyclerAdapter<DBOrderDataModel, FirebaseCashierAdapter.ViewHolder> {

    public FirebaseCashierAdapter(@NonNull FirebaseRecyclerOptions<DBOrderDataModel> options) {
        super(options);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_kasir, parent, false);
        return new ViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder viewHolder, int i, @NonNull DBOrderDataModel order) {
        viewHolder.tvNoPesanan.setText("Pesanan No 2");
        viewHolder.tvNoMeja.setText(order.getNoMeja());
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
