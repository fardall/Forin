package com.example.forin.firebasemethod;

import com.example.forin.datamodel.DBOrderDataModel;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ForinFirebase {

    private DatabaseReference DbRef;

    public ForinFirebase () {
        FirebaseDatabase db = FirebaseDatabase.getInstance("https://forin-170e6-default-rtdb.asia-southeast1.firebasedatabase.app");
        DbRef = db.getReference(DBOrderDataModel.class.getSimpleName());
    }

    public Task<Void> add (DBOrderDataModel orderDataModel) {
        return DbRef.push().setValue(orderDataModel);
    }

}
