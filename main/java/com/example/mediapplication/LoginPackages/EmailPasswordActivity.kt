package com.example.mediapplication.LoginPackages

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.mediapplication.Activites.DescionActivity
import com.example.mediapplication.Activites.RegistrationActivity
import com.example.mediapplication.Activites.TabbedActivity
import com.example.mediapplication.R
import com.google.firebase.auth.ActionCodeSettings
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class EmailPasswordActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    lateinit var email : EditText
    lateinit var password : EditText
    private lateinit var Bu : Button
    lateinit var SIB : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContentView(R.layout.activity_email_password)
        email =  findViewById(R.id.editText)
        password = findViewById(R.id.editText2)
        Bu = findViewById(R.id.button5)
        SIB = findViewById(R.id.button3)




        auth = FirebaseAuth.getInstance()

        Bu.setOnClickListener {
            auth.createUserWithEmailAndPassword(email.text.toString(), password.text.toString())
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "createUserWithEmail:success")
                        val user = auth.currentUser
                        val Tintent: Intent = Intent(this, RegistrationActivity::class.java)
                        startActivity(Tintent)
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "createUserWithEmail:failure", task.exception)
                        Toast.makeText(baseContext, "Authentication failed.",
                            Toast.LENGTH_SHORT).show()
                    }

                    // ...
                }
        }

        SIB.setOnClickListener {
            auth.signInWithEmailAndPassword(email.text.toString(), password.text.toString())
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "signInWithEmail:success")
                        val user = auth.currentUser
                        val Tintent: Intent = Intent(this, DescionActivity::class.java)
                        Tintent.putExtra("FBUSER", user)
                        startActivity(Tintent)

                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "signInWithEmail:failure", task.exception)
                        Toast.makeText(baseContext, "Authentication failed.",
                            Toast.LENGTH_SHORT).show()
                        email.setError("Your email or password was incorrect")
                        password.setError("Your email or password was incorrect")
                    }

                    // ...
                }
        }


    }


    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
    }


    companion object {
        private const val TAG = "EmailPassword"
    }



}
