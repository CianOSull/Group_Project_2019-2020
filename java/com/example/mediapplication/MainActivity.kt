package com.example.mediapplication

import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.example.mediapplication.ui.main.SectionsPagerAdapter
import android.*
import android.content.Intent
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {
    val Database = FirebaseDatabase.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val editTextPassword : EditText = findViewById(R.id.PasswordEditText)
        val EmailEditTextr : EditText = findViewById(R.id.EmailEditText)
        val EmailTextView : TextView = findViewById(R.id.EmailLoginTextView)
        val PasswordTextView : TextView = findViewById(R.id.PasswordTextView)
        val LoginButton : Button = findViewById(R.id.LoginButton)

        val RegisterButton : Button = findViewById(R.id.RegisterButton)
        RegisterButton.setOnClickListener {
            openRegisterActivity()
        }

        val Button2 : Button = findViewById(R.id.button2)
        Button2.setOnClickListener {
            openTabbedActivity()
        }



        //   val fab: FloatingActionButton = findViewById(R.id.fab)

//        fab.setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show()
//        }
    }

    private fun openRegisterActivity() {
        val Rintent : Intent = Intent(this,RegisterActivity::class.java)
        startActivity(Rintent)
    }

    private fun openTabbedActivity(){
        val Tintent : Intent = Intent(this,TabbedActivity::class.java)
        startActivity(Tintent)
    }
}