package com.example.mediapplication

import android.location.Address
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Telephony
import android.widget.Button
import android.widget.EditText

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        val Fname : EditText = findViewById(R.id.FnameEditText)
        val Lname : EditText = findViewById(R.id.SecondNameEditText)
        val Password1 : EditText = findViewById(R.id.Pass1EditText)
        val Password2 : EditText = findViewById(R.id.Pass2EditText)
        val Email : EditText = findViewById(R.id.EmailEditText)
        val PhoneNumber : EditText = findViewById(R.id.PhoneNumberEditText)
        val Address : EditText = findViewById(R.id.AddressEditText)
        val RegisterButton : Button = findViewById(R.id.RegisterButton)
        val User1 : User

        val ET = arrayOf<EditText>()
        

        RegisterButton.setOnClickListener {
            if(Password1.text.toString() != Password2.text.toString()){
                Password1.setError("Passwords do not match.")
                Password2.setError("Passwords do not match.")
            }
            else {
                if(Fname.text.toString()== "" || Lname.text.toString() == ""){

                    }
                    //User1 = CreateUser()
                 //   RegisterUser()

            }
        }
    }

//    fun CreateUser(): User {
//
//
//    }
}

//private operator fun <T> Array<T>.invoke(): Any {
//
//}
