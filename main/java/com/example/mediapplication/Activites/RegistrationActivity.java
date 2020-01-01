package com.example.mediapplication.Activites;

import android.app.Activity;

import android.content.Intent;
import android.os.Bundle;

import android.os.Parcelable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.mediapplication.GP;
import com.example.mediapplication.InsuranceProvider;
import com.example.mediapplication.R;
import com.example.mediapplication.User;
import com.example.mediapplication.ValueContainer;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class RegistrationActivity extends Activity {
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference myRef, UserRef, InsuranceRef;
    private FirebaseAuth Auth;
    private FirebaseUser user;

    private EditText PhoneNum, Age, Fname, Lname, Address;
    private Spinner S, Insurance;
    private Button RGB;
    ArrayList<GP> AR = new ArrayList<GP>();
    ArrayList<InsuranceProvider> IPAR = new ArrayList<>();

    private String Email;
    String UID;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        //Firebase Init
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference("GP");
        UserRef = mFirebaseDatabase.getReference("Users");
        InsuranceRef = mFirebaseDatabase.getReference("Insurance-Providers");
        Auth = FirebaseAuth.getInstance();
        user = Auth.getCurrentUser();
        UID = user.getUid();
        Email = user.getEmail();

        //Android resource init
        S = findViewById(R.id.spinner);
        Insurance = findViewById(R.id.spinnerInsurance);
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                showData(dataSnapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        InsuranceRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                InsuranceData(dataSnapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        Fname = findViewById(R.id.Fname);
        Lname = findViewById(R.id.Lname);
        PhoneNum = findViewById(R.id.PhoneNumber);
        Age = findViewById(R.id.age);
        Address = findViewById(R.id.Address);

        RGB = findViewById(R.id.Register);


        RGB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CreateUser();
            }
        });
    }

    private void InsuranceData(DataSnapshot dataSnapshot) {
        ArrayList<String> SAR = new ArrayList<String>();


        for (DataSnapshot ds : dataSnapshot.getChildren()){
            InsuranceProvider IP = new InsuranceProvider();
            String C_name = ds.child("Company_Name").getValue(String.class);
            Object ID = ds.child("IP_ID").getValue();

            IP.setID(ID);
            IP.setName(C_name);

            IPAR.add(IP);

        }
        for(int i = 0; i < IPAR.size(); i++){
            SAR.add(IPAR.get(i).getName());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, SAR);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Insurance.setAdapter(adapter);
    }

    private void CreateUser() {
        final ValueContainer v = new ValueContainer();

        UserRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                v.setVal((int) dataSnapshot.getChildrenCount());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        String p = "Patient"+(v.getVal()+1);
        if(Fname.getText().toString().isEmpty()){
            Fname.setError("This cannot be empty");
            return;
        }
        else
            if (Lname.getText().toString().isEmpty()){
                Lname.setError("This cannot be empty");
                return;
            }
        else
            if(PhoneNum.getText().toString().isEmpty()){
                PhoneNum.setError("This cannot be empty.");
                return;
            }
        else
            if(Age.getText().toString().isEmpty()){
                Age.setError("This cannot be empty");
                return;
            }
        else
            if(Address.getText().toString().isEmpty()){
                Address.setError("This cannot be empty.");
        }
        else{
            User u = new User();
            u.setAge(Age.getText().toString());
            u.setAddress(Address.getText().toString());
            u.setEmail(Email);
            u.setFname(Fname.getText().toString());
            u.setLname(Lname.getText().toString());
            u.setGP_ID(AR.get(S.getSelectedItemPosition()).getGP_id());
            u.setUID(UID);
            u.setIP_ID(String.valueOf( IPAR.get(Insurance.getSelectedItemPosition()).getID()));

            DatabaseReference newUserRef = UserRef.push();

            newUserRef.child("Fname").setValue(u.getFname());
            newUserRef.child("Lname").setValue(u.getLname());
            newUserRef.child("Address").setValue(u.getAddress());
            newUserRef.child("Age").setValue(u.getAge());
            newUserRef.child("Email").setValue(u.getEmail());
            newUserRef.child("UID").setValue(u.getUID());
            newUserRef.child("GP_ID").setValue(u.getGP_ID());
            newUserRef.child("IP_ID").setValue(u.getIP_ID());
            newUserRef.child("DiabatesOutPut").setValue(0);
            newUserRef.child("BreastConcerResult").setValue(0);
            newUserRef.child("HeartValiue").setValue(0);
            Intent i = new Intent(RegistrationActivity.this, DescionActivity.class);
           // i.putExtra("UserRef", (Parcelable) newUserRef);
            startActivity(i);

        }

    }

    private void showData(DataSnapshot dataSnapshot) {

        ArrayList<String> SAR = new ArrayList<String>();

        int count = 1;
        for(DataSnapshot ds : dataSnapshot.getChildren()){
            GP NGP = new GP();
            String Address = ds.child("Address").getValue(String.class);
            String Name  = ds.child("Name").getValue(String.class);
            Object Num = ds.child("Phone").getValue();
            String Email = ds.child("Email").getValue(String.class);
            Object Age = ds.child("Age").getValue();
            Object GPID = ds.child("GP_id").getValue();


            NGP.setName(Name);
            NGP.setAddress(Address);
            NGP.setNum(Num.toString());
            NGP.setEmail(Email);
            NGP.setAge(Integer.parseInt(Age.toString()));
            NGP.setGP_id(GPID.toString());
            AR.add(NGP);


        }
        for(int i = 0; i < AR.size(); i++){
            SAR.add(AR.get(i).getName());
        }
//        textView.setText(SAR.get(0));



        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, SAR);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        S.setAdapter(adapter);

    }


}
