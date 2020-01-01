package com.example.mediapplication.FragmentsForTabbedActivity

import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.mediapplication.Activites.FloatHolder
import com.example.mediapplication.MachineLearningKits.HeartML
import com.example.mediapplication.R
import com.example.mediapplication.ReferenceHolder
import com.example.mediapplication.ValueContainer
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*

class FirstFragment : Fragment() {



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater!!.inflate(R.layout.fragment_first, container, false)

        val Db : Button = v!!.findViewById(R.id.HeartButton)
        Db.setOnClickListener {
            run(v)
        }
        // Inflate the layout for this fragment
        return v
    }

    fun run(view: View){

        val age : EditText = view.findViewById(R.id.Age)
        val SexHeart : EditText = view.findViewById(R.id.SexHeart)
        val ChestPainTypeHeart : EditText = view.findViewById(R.id.ChestPainTypeHeart)
        val RestingBPHeart: EditText = view.findViewById(R.id.RestingBPHeart)
        val HeartCholestoral : EditText = view.findViewById(R.id.HeartCholestoral)
        val HeartFastingBloodSugar: EditText = view.findViewById(R.id.HeartFastingBloodSugar)
        val RestingEcocrdigraphResults : EditText = view.findViewById(R.id.RestingEcocrdigraphResults)
        val HeartRate : EditText = view.findViewById(R.id.HeartRate)
        val Exercise_induced_ani : EditText = view.findViewById(R.id.Exercise_induced_ani)
        val editText13 : EditText = view.findViewById(R.id.editText13)
        val the_slope_of_the_peak_exercise_ST_segment : EditText = view.findViewById(R.id.the_slope_of_the_peak_exercise_ST_segment)
        val Thal : EditText = view.findViewById(R.id.Thal)
        val CA :EditText = view.findViewById(R.id.CA)
        val TV : TextView = view.findViewById(R.id.HTV)

        val HML = HeartML()


        val numbers: FloatArray = floatArrayOf(age.text.toString().toFloat(),
            SexHeart.text.toString().toFloat(),
            ChestPainTypeHeart.text.toString().toFloat(),
            RestingBPHeart.text.toString().toFloat(),
            HeartCholestoral.text.toString().toFloat(),
            HeartFastingBloodSugar.text.toString().toFloat(),
            RestingEcocrdigraphResults.text.toString().toFloat(),
            HeartRate.text.toString().toFloat(),
            Exercise_induced_ani.text.toString().toFloat(),
            editText13.text.toString().toFloat(),
            the_slope_of_the_peak_exercise_ST_segment.text.toString().toFloat(),
            CA.text.toString().toFloat(),
            Thal.text.toString().toFloat()
            )
        val fh = FloatHolder()
        val nums : Array<FloatArray> = arrayOf(numbers)
        var UserRef : DatabaseReference? = rf.dbrf
        HML.runInference(TV, nums, fh, UserRef)



    }




    companion object {

        var Tag = "First Fragment"
        val Auth: FirebaseAuth? = FirebaseAuth.getInstance()
        val user: FirebaseUser? = Auth?.currentUser
        val UID: String? = user?.uid
        val mFirebaseDatabase: FirebaseDatabase? = FirebaseDatabase.getInstance()
        val myRef: DatabaseReference? = mFirebaseDatabase?.getReference("Users")
        var rf : ReferenceHolder = ReferenceHolder(mFirebaseDatabase, UID, myRef)
    }
}