package com.example.mediapplication.Activites

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.mediapplication.FragmentsForTabbedActivity.MyPagerAdapter
import com.example.mediapplication.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_tabbed.*

//import com.example.mediapplication.ui.main.SectionsPagerAdapter

class TabbedActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tabbed)


        val fragmentAdapter =
            MyPagerAdapter(
                supportFragmentManager
            )
        viewpager_main.adapter = fragmentAdapter

        val mAuth : FirebaseAuth = FirebaseAuth.getInstance()
        val user : FirebaseUser? = mAuth.currentUser
        val email = user?.email
        val UID = user?.uid

        tabs_main.setupWithViewPager(viewpager_main)

       // val ExitButton : Button = findViewById(R.id.Exit)
//        ExitButton.setOnClickListener {
//            val a_builder =
//                AlertDialog.Builder(this)
//            a_builder.setMessage("Do you want to Sign out?")
//            a_builder.setPositiveButton("SignOut") {
//                    dialogInterface, i -> startActivity(Intent(this, MainActivity::class.java))
//                FirebaseAuth.getInstance().signOut()
//            }
//            a_builder.setNegativeButton(
//                "Cancel"
//            ) { dialogInterface, i -> dialogInterface.cancel() }
//            a_builder.setNeutralButton(
//                "Close App"
//            ) { dialogInterface, i ->
//                val intent = Intent(applicationContext, MainActivity::class.java)
//                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
//                intent.putExtra("EXIT", true)
//                FirebaseAuth.getInstance().signOut()
//                startActivity(intent)
//            }
//
//            val a = a_builder.create()
//            a.setTitle("Sign Out")
//            a.show()
//        }


    }




}