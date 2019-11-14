package com.example.mediapplication

import android.app.Activity
import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.example.mediapplication.ui.main.MachineLearning
import com.example.mediapplication.ui.main.SectionsPagerAdapter

class TabbedActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tabbed)
        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        val viewPager: ViewPager = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        tabs.setupWithViewPager(viewPager)

        val B : Button = findViewById(R.id.button)
        val TV : TextView = findViewById(R.id.textView)

        B.setOnClickListener {
            RunScript(TV)
        }
       // val fab: FloatingActionButton = findViewById(R.id.fab)

//        fab.setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show()
//        }
    }

    private fun RunScript(TV : TextView){
        val ML = MLKit()
        ML.runInference(TV)
//        val Res = ML.output
//        if(Res != null) {
//            if (Res[0][0] == 1.0f) {
//                return "You have diabetes"
//            } else {
//                return "You do not have Diabetes"
//            }
//        }
//        else{
//            return "Still returing null"
//        }
   }
}