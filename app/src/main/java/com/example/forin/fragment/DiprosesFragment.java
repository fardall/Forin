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
import com.example.forin.adapter.CashierAdapter;
import com.example.forin.datamodel.DBOrderDataModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DiprosesFragment extends Fragment {
    ArrayList<DBOrderDataModel> listDetailPesanan = new ArrayList<>();
    RecyclerView rvPesanan;

    public DiprosesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_diproses, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvPesanan = view.findViewById(R.id.rv_pesanan);
        rvPesanan.setHasFixedSize(true);

        getDataFromDB();
        showAdapter();

    }

    private void showAdapter() {
        rvPesanan.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        CashierAdapter adapter = new CashierAdapter(listDetailPesanan);
        rvPesanan.setAdapter(adapter);
    }

    public void getDataFromDB () {
        FirebaseDatabase db = FirebaseDatabase.getInstance("https://forin-170e6-default-rtdb.asia-southeast1.firebasedatabase.app");
        DatabaseReference dbRef = db.getReference(DBOrderDataModel.class.getSimpleName());
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snapshotData : snapshot.getChildren()) {
                    listDetailPesanan.add(snapshotData.getValue(DBOrderDataModel.class));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}