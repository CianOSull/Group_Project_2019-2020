package com.example.mediapplication.Activites

//import com.example.mediapplication.ui.main.SectionsPagerAdapter
//import com.google.android.gms.auth.api.signin.GoogleSignInAccount
//import androidx.core.app.ComponentActivity
//import androidx.core.app.ComponentActivity.ExtraData
//import androidx.core.content.ContextCompat.getSystemService
//import android.icu.lang.UCharacter.GraphemeClusterBreak.T
//import com.google.android.gms.auth.api.signin.SignInAccount

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.mediapplication.LoginPackages.EmailPasswordActivity
import com.example.mediapplication.R
import com.google.firebase.auth.FirebaseAuth


class MainActivity : AppCompatActivity() {





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        // ...
        // Initialize Firebase Auth
        val auth = FirebaseAuth.getInstance()
        val s = auth.currentUser
        if(s != null){
            val i  =  Intent(this,DescionActivity::class.java)
            startActivity(i)
        }


//        val RegisterButton : Button = findViewById(R.id.RegisterButton)
//        RegisterButton.setOnClickListener {
//            openRegisterActivity()
//        }

        val Button2 : Button = findViewById(R.id.button2)
        Button2.setOnClickListener {
            openTabbedActivity()
        }
        val Button4 : Button = findViewById(R.id.button4)
        Button4.setOnClickListener {
            opeenEmailLogin()
        }

        if (intent.getBooleanExtra("EXIT", false)) {
            finish()
        }






    }

    private fun openRegisterActivity() {
//        val Rintent : Intent = Intent(this,
//            RegisterActivity::class.java)
        //startActivity(Rintent)
    }



    private fun openTabbedActivity() {
        val Tintent: Intent = Intent(this, TabbedActivity::class.java)
        startActivity(Tintent)
    }

    private fun opeenEmailLogin(){
        val Tintent: Intent = Intent(this, EmailPasswordActivity::class.java)
        startActivity(Tintent)
    }


    private fun openRegistration(){
        val Tintent: Intent = Intent(this, RegistrationActivity::class.java)
        startActivity(Tintent)
    }








}