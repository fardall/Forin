package com.example.forin.firebasemethod;

import androidx.annotation.NonNull;

import com.example.forin.datamodel.DBOrderDataModel;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ForinFirebase {

    private DatabaseReference DbRef;
    private ArrayList<DBOrderDataModel> DBOrder;

    public ForinFirebase () {
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DbRef = db.getReference(DBOrderDataModel.class.getSimpleName());
    }

    public Task<Void> add (DBOrderDataModel orderDataModel) {
        return DbRef.push().setValue(orderDataModel);
    }

    public ArrayList<DBOrderDataModel> getDBOrder() {
        DbRef.child("DBOrderDataModel").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snapshotData : snapshot.getChildren()) {
                    DBOrderDataModel order = snapshotData.getValue(DBOrderDataModel.class);
                    DBOrder.add(order);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return DBOrder;
    }

}
