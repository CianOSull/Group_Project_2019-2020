package com.example.mediapplication;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ReferenceHolder {
    private DatabaseReference DBRF;

    public DatabaseReference getDBRF() {
        return DBRF;
    }

    public void setDBRF(DatabaseReference f) {
        DBRF = f;
    }

    public ReferenceHolder(FirebaseDatabase db, final String UID, DatabaseReference databaseReference) {
        DBRF = db.getInstance().getReference();

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()){
                    if(ds.child("UID").getValue().equals(UID)){
                          setDBRF(ds.getRef());
                        break;
                    }
                    else{
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}
