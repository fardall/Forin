package com.example.forin.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.forin.R;
import com.example.forin.datamodel.DBOrderDataModel;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class FirebaseCashierAdapter extends FirebaseRecyclerAdapter<DBOrderDataModel, FirebaseCashierAdapter.ViewHolder> {
    private OnItemClickCallback onItemClickCallback;
    private static final int LAYOUT_ONE = 0;
    private static final int LAYOUT_TWO = 1;
    private int viewType;

    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }

    public FirebaseCashierAdapter(@NonNull FirebaseRecyclerOptions<DBOrderDataModel> options) {
        super(options);
    }

    public FirebaseCashierAdapter(@NonNull FirebaseRecyclerOptions<DBOrderDataModel> options, int viewType) {
        super(options);
        this.viewType = viewType;
    }

    @Override
    public int getItemViewType(int position) {
        if (viewType == LAYOUT_ONE) {
            return LAYOUT_ONE;
        } else {
            return LAYOUT_TWO;
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_kasir, parent, false);
        return new ViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder viewHolder, int i, @NonNull DBOrderDataModel order) {
        if (viewHolder.getItemViewType() == LAYOUT_ONE) {
            if (order.isOnProcess()){
                viewHolder.tvNoPesanan.setText("Pesanan No 2");
                viewHolder.tvNoMeja.setText(order.getNoMeja());
                order.setKey(String.valueOf(i));
                viewHolder.itemView.setOnClickListener(v -> {
                    onItemClickCallback.onItemClicked(order);
                });
            } else {
                viewHolder.itemView.setLayoutParams(new LinearLayout.LayoutParams(0,0));
            }
        } else {
            if (!(order.isOnProcess())){
                viewHolder.tvNoPesanan.setText("Pesanan No 2");
                viewHolder.tvNoMeja.setText(order.getNoMeja());
                order.setKey(String.valueOf(i));
                viewHolder.itemView.setOnClickListener(v -> {
                    onItemClickCallback.onItemClicked(order);
                });
            } else {
                viewHolder.itemView.setLayoutParams(new LinearLayout.LayoutParams(0,0));
            }
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvNoPesanan, tvNoMeja;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvNoPesanan = itemView.findViewById(R.id.no_pesanan);
            tvNoMeja = itemView.findViewById(R.id.no_meja);
        }
    }

    public interface OnItemClickCallback {
        void onItemClicked(DBOrderDataModel dataModel);
    }

}
