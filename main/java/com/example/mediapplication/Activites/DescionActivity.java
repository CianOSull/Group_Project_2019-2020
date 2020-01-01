package com.example.mediapplication.Activites;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mediapplication.LoginPackages.EmailPasswordActivity;
import com.example.mediapplication.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.example.mediapplication.User;
import com.example.mediapplication.ValueContainer;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DescionActivity extends AppCompatActivity {
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference myRef, UserRef;
    private FirebaseAuth Auth;
    private FirebaseUser user;
    private String UID;
    String Tag = "Descion Activity.";

  //  private TextView RateUsTV, SupportTV, TabbedViewTV;
    private RatingBar ratingBar;
    private EditText QueriesET;
    private Button TabbedButton,  QueryButton;
    private FloatingActionButton floatingActionButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_descion);

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

        ratingBar = findViewById(R.id.ratingBar);
        QueriesET = findViewById(R.id.QueriesET);
      //  TabbedButton = findViewById(R.id.TabbedButton);
        floatingActionButton = findViewById(R.id.floatingActionButton);
        registerForContextMenu(floatingActionButton);
        QueryButton = findViewById(R.id.QueryButton);

//        TabbedButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent I = new Intent(DescionActivity.this,TabbedActivity.class);
//                startActivity(I);
//            }
//        });

        QueryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserRef.child("Query").setValue(QueriesET.getText().toString());
                QueriesET.setText("");
                Toast t = Toast.makeText(getApplicationContext(),"Your Query has been sent and will be seen to soon.",Toast.LENGTH_SHORT);
                t.show();
            }
        });

    }
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Descion");
        menu.add(0, v.getId(), 0, "Make a payment");
        menu.add(0, v.getId(), 0, "Heart/Diabetes/Cancer");
        menu.add(0,v.getId(),0,"Change your information.");
        menu.add(0,v.getId(),0,"Contact Us");
        menu.add(0,v.getId(),0,"Log out");
    }

    public boolean onContextItemSelected(MenuItem item) {
        if (item.getTitle() == "Make a payment") {
            Intent i = new Intent(DescionActivity.this, PaymentActivity.class);
            startActivity(i);
        }
        else if (item.getTitle() == "Heart/Diabetes/Cancer") {
            Intent i = new Intent(DescionActivity.this, TabbedActivity.class);
            // i.putExtra("UserRef", (Parcelable) newUserRef);
            startActivity(i);
            //to do
        }
        else if(item.getTitle() ==  "Change your information." ){
            Intent i = new Intent(DescionActivity.this, RegistrationActivity.class);
            // i.putExtra("UserRef", (Parcelable) newUserRef);
            startActivity(i);
            //to do
        }
        else if(item.getTitle() == "Contact Us"){
            //to do
            Intent i = new Intent(DescionActivity.this, ContactUs.class);
            // i.putExtra("UserRef", (Parcelable) newUserRef);
            startActivity(i);
        }
        else if(item.getTitle() == "Log out"){
            Intent i = new Intent(DescionActivity.this, EmailPasswordActivity.class);
            startActivity(i);
        }
        else{
            return false;
        }
        return true;
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
