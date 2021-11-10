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
import com.example.forin.datamodel.DBOrderDataModel;
import com.example.forin.firebasemethod.ForinFirebase;
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

        showAdapter();

    }

    private void showAdapter() {
        ForinFirebase DBRef = new ForinFirebase();
        listDetailPesanan = DBRef.getDBOrder();
        rvPesanan.setLayoutManager(new LinearLayoutManager(requireActivity().getApplicationContext()));
        rvPesanan.setAdapter(adapter);
    }

    public void getDataFromDB () {
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference dbRef = db.getReference().child("DBOrderDataModel");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snapshotData : snapshot.getChildren()) {
                    DBOrderDataModel order = snapshotData.getValue(DBOrderDataModel.class);
                    listDetailPesanan.add(order);
                }
                //adapter.setItem(listDetailPesanan);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}