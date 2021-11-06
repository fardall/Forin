package com.example.forin;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ForinFirebase {

    private DatabaseReference DbRef;

    public ForinFirebase () {
        FirebaseDatabase db =FirebaseDatabase.getInstance();
    }
    
}
