package com.example.mediapplication.FragmentsForTabbedActivity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.mediapplication.MachineLearningKits.MLKit
import com.example.mediapplication.R
import com.example.mediapplication.ReferenceHolder
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.fragment_second.*

class SecondFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v : View? = inflater.inflate(R.layout.fragment_second, container, false)
        val DB : Button = v!!.findViewById(R.id.DiabetesButton)
        DB.setOnClickListener {
            DiabetesOnClick(v)
        }
        return v
    }

    private fun DiabetesOnClick(view: View) {
        val TP : EditText = view!!.findViewById(R.id.TimesPregnant)
        val Glucose : EditText = view!!.findViewById(R.id.Glucose)
        val BloodPressure : EditText = view!!.findViewById(R.id.BloodPressure)
        val TricepThickness : EditText = view!!.findViewById(R.id.TricepThickness)
        val age : EditText = view!!.findViewById(R.id.age)
        val Insulin : EditText = view!!.findViewById(R.id.Insulin)
        val BMI : EditText = view!!.findViewById(R.id.BMI)
        val DiabetesPedigree : EditText = view!!.findViewById(R.id.Diabetes_Pedigree)
        val Out : TextView = view!!.findViewById(R.id.DiabtetesOutPutTextView)

        val DiabtesMachinLearning = MLKit()
            .javaClass

        if(TP == null || TimesPregnant.text.toString().toInt() < 0){
            TimesPregnant.setError("This has to be a type integer and greater then 0")
        }
        else
            if (Glucose == null || Glucose.text.toString().toFloat().equals(0.0)){
                Glucose.setError("Glucose must be of type float greater then 0.")
            }
        else
            if (BloodPressure == null || BloodPressure.text.toString().toInt() < 0){
                BloodPressure.setError("Your blood pressure must be gretaer than 0")
            }
        else
            if(TricepThickness == null || TricepThickness.text.toString().toInt() < 0){
                TricepThickness.setError("Must be an integer in size mm greater then 0.")
            }
        else
            if(age == null || age.text.toString().toInt() < 0){
                age.setError("You must be over the age of 0.")
            }
        else
            if(Insulin == null || Insulin.text.toString().toInt() < 0){
                Insulin.setError("Insulin must be over 0.")
            }
        else
            if(BMI == null || BMI.text.toString().toFloat().equals(0.0)){
                BMI.setError("Your BMI must be over 0.")
            }
        else
            if(DiabetesPedigree.text.toString().isEmpty()|| DiabetesPedigree.text.toString().toFloat().equals(0.0)){
                DiabetesPedigree.setError("Diabetes Pedigree must be greater than 0.")
            }
        else{
                val DBML =
                    MLKit()
                val numbers: FloatArray = floatArrayOf(TP.text.toString().toFloat(),
                    Glucose.text.toString().toFloat(),
                    BloodPressure.text.toString().toFloat(),
                    TricepThickness.text.toString().toFloat(),
                    Insulin.text.toString().toFloat(),
                    BMI.text.toString().toFloat(),
                    DiabetesPedigree.text.toString().toFloat(),
                    age.text.toString().toFloat())

                val nums : Array<FloatArray> = arrayOf(numbers)
                var UserRef : DatabaseReference? = FirstFragment.rf.dbrf


                DBML.runInference(Out, nums, UserRef)
            }
    }

    companion object {

        var Tag = "Second Fragment"
        val Auth: FirebaseAuth? = FirebaseAuth.getInstance()
        val user: FirebaseUser? = Auth?.currentUser
        val UID: String? = user?.uid
        val mFirebaseDatabase: FirebaseDatabase? = FirebaseDatabase.getInstance()
        val myRef: DatabaseReference? = mFirebaseDatabase?.getReference("Users")
        var rf : ReferenceHolder = ReferenceHolder(mFirebaseDatabase, UID, myRef)
    }



}