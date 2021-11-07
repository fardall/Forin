package com.example.forin;

import com.example.forin.datamodel.OrderDataModel;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ForinFirebase {

    private DatabaseReference DbRef;

    public ForinFirebase () {
        FirebaseDatabase db = FirebaseDatabase.getInstance("https://forin-170e6-default-rtdb.asia-southeast1.firebasedatabase.app");
        DbRef = db.getReference(OrderDataModel.class.getSimpleName());
    }

    public Task<Void> add (OrderDataModel orderDataModel) {
        return DbRef.push().setValue(orderDataModel);
    }

}
