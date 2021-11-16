package com.example.forin.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.forin.R;
import com.example.forin.datamodel.Order;

import java.util.ArrayList;

public class OrderFoodAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<Order> orderArrayList = new ArrayList<>();
    private ArrayList<Order> dataModels;
    private static final int LAYOUT_ONE = 0;
    private static final int LAYOUT_TWO = 1;
    private int viewType;

    @Override
    public int getItemViewType(int position) {
        if (viewType == LAYOUT_ONE) {
            return LAYOUT_ONE;
        } else {
            return LAYOUT_TWO;
        }
    }

    public OrderFoodAdapter(ArrayList<Order> list, int viewType) {
        this.orderArrayList = list;
        this.viewType = viewType;
    }

    public OrderFoodAdapter(ArrayList<Order> dataModels) {
        this.dataModels = dataModels;
        viewType = 1;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = null;
        RecyclerView.ViewHolder viewHolder = null;
        switch (viewType) {
            case LAYOUT_ONE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_order, parent, false);
                viewHolder = new ListViewHolder(view);
                break;
            case LAYOUT_TWO:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_order, parent, false);
                viewHolder = new ViewHolderCashier(view);
                break;
            default:
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder.getItemViewType() == LAYOUT_ONE) {
            ListViewHolder viewHolder = (ListViewHolder) holder;
            Order order = orderArrayList.get(position);
            viewHolder.tvFoodTitle.setText(order.getFoodName());
            viewHolder.tvFoodCount.setText(order.getFoodCount());
            viewHolder.tvFoodPrice.setText(order.getTotalPrice());
        } else {
            ViewHolderCashier viewHolder = (ViewHolderCashier) holder;
            Order order = dataModels.get(position);
            viewHolder.tvFoodTitle.setText(order.getFoodName());
            viewHolder.tvFoodPrice.setText(order.getTotalPrice());
            viewHolder.tvFoodCount.setText(order.getFoodCount());

        }
    }

    @Override
    public int getItemCount() {
        if (viewType == LAYOUT_ONE) {
            return orderArrayList.size();
        } else {
            return dataModels.size();
        }
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {
        TextView tvFoodCount, tvFoodTitle, tvFoodPrice;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            tvFoodPrice = itemView.findViewById(R.id.tv_priceFood);
            tvFoodTitle = itemView.findViewById(R.id.tv_titleFood);
            tvFoodCount = itemView.findViewById(R.id.tv_countFood);
        }
    }

    public class ViewHolderCashier extends RecyclerView.ViewHolder {
        TextView tvFoodCount, tvFoodTitle, tvFoodPrice;
        public ViewHolderCashier(@NonNull View itemView) {
            super(itemView);
            tvFoodPrice = itemView.findViewById(R.id.tv_priceFood);
            tvFoodTitle = itemView.findViewById(R.id.tv_titleFood);
            tvFoodCount = itemView.findViewById(R.id.tv_countFood);
        }
    }

}
