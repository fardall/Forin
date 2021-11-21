package com.example.forin.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.forin.R;
import com.example.forin.adapter.FirebaseCashierAdapter;
import com.example.forin.datamodel.DBOrderDataModel;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DiprosesFragment extends Fragment {
    private RecyclerView rvPesanan;
    private ArrayList<DBOrderDataModel> listDetailPesanan = new ArrayList<>();
    private FirebaseDatabase db;
    private DatabaseReference dbRef;


    public DiprosesFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_diproses, container, false);
        getDataFromDB();

        rvPesanan = view.findViewById(R.id.rv_pesanan);
        rvPesanan.setHasFixedSize(true);

        rvPesanan.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));

        FirebaseRecyclerOptions<DBOrderDataModel> options = new
                FirebaseRecyclerOptions.Builder<DBOrderDataModel>()
                .setQuery(dbRef, DBOrderDataModel.class)
                .build();
        FirebaseCashierAdapter adapter = new FirebaseCashierAdapter(options);
        rvPesanan.setAdapter(adapter);
        adapter.startListening();

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    public void getDataFromDB () {
        db = FirebaseDatabase.getInstance("https://forin-170e6-default-rtdb.asia-southeast1.firebasedatabase.app");
        dbRef = db.getReference(DBOrderDataModel.class.getSimpleName());
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    listDetailPesanan.add(ds.getValue(DBOrderDataModel.class));
                }
                //adapter.setItem(listDetailPesanan);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}