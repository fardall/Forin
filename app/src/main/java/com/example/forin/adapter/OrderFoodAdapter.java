package com.example.forin.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.forin.R;
import com.example.forin.datamodel.Order;

import java.util.ArrayList;

public class OrderFoodAdapter extends RecyclerView.Adapter<OrderFoodAdapter.ListViewHolder> {
    private ArrayList<Order> orderArrayList;

    public OrderFoodAdapter(ArrayList<Order> list) {
        this.orderArrayList = list;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_order, parent, false);
        return new ListViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        Order order = orderArrayList.get(position);
        holder.tvFoodCount.setText(String.valueOf(order.getFoodCount()));
        holder.tvFoodTitle.setText(order.getFoodName());
        holder.tvFoodPrice.setText(order.getTotalPrice() + "00");
    }

    @Override
    public int getItemCount() {
        return orderArrayList.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {
        TextView tvFoodCount, tvFoodTitle, tvFoodPrice;
        EditText edtNote, edtName, edtTableNum;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);

            tvFoodPrice = itemView.findViewById(R.id.tv_priceFood);
            tvFoodTitle = itemView.findViewById(R.id.tv_titleFood);
            tvFoodCount = itemView.findViewById(R.id.tv_countFood);


        }
    }


}
