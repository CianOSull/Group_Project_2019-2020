package com.example.mediapplication.FragmentsForTabbedActivity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.mediapplication.MachineLearningKits.BreastCancerML
import com.example.mediapplication.R
import com.example.mediapplication.ReferenceHolder
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class ThirdFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v : View? = inflater.inflate(R.layout.fragment_third, container, false)
        val breast_Button : Button = v!!.findViewById(R.id.Breast_Button)
        breast_Button.setOnClickListener {
            BreastRun(v)
        }


        // Inflate the layout for this fragment
        return v

    }

    private fun BreastRun(v: View) {
        val MeanRadius : EditText = v.findViewById(R.id.Mean_Radius)
        val MeanTexture : EditText = v.findViewById(R.id.Mean_texture)
        val MeanPerimeter : EditText = v.findViewById(R.id.Mean_Perimeter)
        val MeanArea : EditText = v.findViewById(R.id.Mean_area)
        val MeanSmoothness : EditText = v.findViewById(R.id.Ms)

        val BCML : BreastCancerML = BreastCancerML()

        val numbers: FloatArray = floatArrayOf(MeanRadius.text.toString().toFloat(),
            MeanTexture.text.toString().toFloat(),
            MeanPerimeter.text.toString().toFloat(),
            MeanArea.text.toString().toFloat(),
            MeanSmoothness.text.toString().toFloat())
        val TV : TextView = v.findViewById(R.id.textView)

        val nums : Array<FloatArray> = arrayOf(numbers)
        var UserRef : DatabaseReference? = FirstFragment.rf.dbrf

        BCML.runInference(TV,nums, UserRef)


    }

    companion object {

        var Tag = "Third Fragment"
        val Auth: FirebaseAuth? = FirebaseAuth.getInstance()
        val user: FirebaseUser? = Auth?.currentUser
        val UID: String? = user?.uid
        val mFirebaseDatabase: FirebaseDatabase? = FirebaseDatabase.getInstance()
        val myRef: DatabaseReference? = mFirebaseDatabase?.getReference("Users")
        var rf : ReferenceHolder = ReferenceHolder(mFirebaseDatabase, UID, myRef)
    }


}