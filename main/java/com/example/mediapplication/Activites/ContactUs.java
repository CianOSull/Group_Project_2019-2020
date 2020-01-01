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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ContactUs extends AppCompatActivity {
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference myRef, UserRef;
    private FirebaseAuth Auth;
    private FirebaseUser user;
    private String UID;
    String Tag = "Descion Activity.";
    private String GPEMAIL;
    String GP_ID;


    TextView GPTV;
    EditText Query;
    Button B;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);


        Auth = FirebaseAuth.getInstance();
        user = Auth.getCurrentUser();
        UID = user.getUid();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference("Users");
        DatabaseReference GPREF = mFirebaseDatabase.getReference("GP");
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                FindData(dataSnapshot);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        GPREF.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                FindGP(dataSnapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

//        GPTV = findViewById(R.id.textView6);
////        GPTV.setText(GPEMAIL);

        Query = findViewById(R.id.editText3);
        Button b6 = findViewById(R.id.button6);
        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserRef.child("EmailToGP").setValue(Query.getText().toString());
            }
        });



    }

    private void FindGP( DataSnapshot dataSnapshot) {
        for(DataSnapshot ds : dataSnapshot.getChildren()){
            if(ds.child("GP_id").getValue().equals(GP_ID)){
               GPEMAIL = ds.child("Email").getValue(String.class);
                Log.d(Tag, "User Reference now equals data snapshot instance.");
                break;
            }
            else{
                Log.d(Tag, "User reference was not saved as ds reference");
            }
        }

    }

    private void FindData(DataSnapshot dataSnapshot) {
        for(DataSnapshot ds : dataSnapshot.getChildren()){
            if(ds.child("UID").getValue().equals(UID)){
                UserRef = ds.getRef();
                GP_ID = (String) ds.child("GP_ID").getValue();
                Log.d(Tag, "User Reference now equals data snapshot instance.");
                break;
            }
            else{
                Log.d(Tag, "User reference was not saved as ds reference");
            }
        }

    }
}
