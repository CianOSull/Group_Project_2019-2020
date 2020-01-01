package com.example.mediapplication.Activites;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.mediapplication.R;
import com.google.android.gms.auth.api.Auth;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PaymentActivity extends AppCompatActivity {

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference myRef, UserRef;
    private FirebaseAuth Auth;
    private FirebaseUser user;
    private String UID;
    String Tag = "Descion Activity.";

    private EditText Number,CVV,Date,Name;
    public TextView Output;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        Number = findViewById(R.id.CardNumber);
        CVV = findViewById(R.id.CVV);
        Date = findViewById(R.id.ExpiryDate);
        Name = findViewById(R.id.FullName);
        Output = findViewById(R.id.textView5);

        Auth = FirebaseAuth.getInstance();
        user = Auth.getCurrentUser();
        UID = user.getUid();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference("Users");
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                FindData(dataSnapshot);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        Button Payment = findViewById(R.id.PaymentButton);
        Payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserRef.child("Payment").setValue("Paid");
                Output.setText("You have successfully Paid your insurance Provider.");
            }
        });



    }


    private void FindData(DataSnapshot dataSnapshot) {
        for(DataSnapshot ds : dataSnapshot.getChildren()){
            if(ds.child("UID").getValue().equals(UID)){
                UserRef = ds.getRef();
                Log.d(Tag, "User Reference now equals data snapshot instance.");
                break;
            }
            else{
                Log.d(Tag, "User reference was not saved as ds reference");
            }
        }

    }
}
