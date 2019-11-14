package com.example.mediapplication.ui.main

import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.ml.common.modeldownload.FirebaseModelDownloadConditions
import com.google.firebase.ml.common.modeldownload.FirebaseModelManager
import com.google.firebase.ml.custom.*

class MachineLearning :  AppCompatActivity() {

    private fun configureHostedModelSource() {
        val remoteModel = FirebaseCustomRemoteModel.Builder("diabetes_predict_model").build()
    }

    private fun configureLocalModelSource() {
        val localModel = FirebaseCustomLocalModel.Builder()
            .setAssetFilePath("DiabetesModel.tflite")
            .build()
    }

    private fun startModelDownloadTask(remoteModel: FirebaseCustomRemoteModel) {
        // [START mlkit_model_download_task]
        val conditions = FirebaseModelDownloadConditions.Builder()
            .requireWifi()
            .build()
        FirebaseModelManager.getInstance().download(remoteModel, conditions)
            .addOnCompleteListener {
                // Success.
            }
        // [END mlkit_model_download_task]
    }

    private fun createInterpreter(localModel: FirebaseCustomLocalModel): FirebaseModelInterpreter? {
        // [START mlkit_create_interpreter]
        val options = FirebaseModelInterpreterOptions.Builder(localModel).build()
        val interpreter = FirebaseModelInterpreter.getInstance(options)
        // [END mlkit_create_interpreter]

        return interpreter
    }

    private fun checkModelDownloadStatus(
        remoteModel: FirebaseCustomRemoteModel,
        localModel: FirebaseCustomLocalModel
    ) {
        // [START mlkit_check_download_status]
        FirebaseModelManager.getInstance().isModelDownloaded(remoteModel)
            .addOnSuccessListener { isDownloaded ->
                val options =
                    if (isDownloaded) {
                        FirebaseModelInterpreterOptions.Builder(remoteModel).build()
                    } else {
                        FirebaseModelInterpreterOptions.Builder(localModel).build()
                    }
                val interpreter = FirebaseModelInterpreter.getInstance(options)
            }
        // [END mlkit_check_download_status]
    }

    private fun addDownloadListener(
        remoteModel: FirebaseCustomRemoteModel,
        conditions: FirebaseModelDownloadConditions
    ) {
        // [START mlkit_remote_model_download_listener]
        FirebaseModelManager.getInstance().download(remoteModel, conditions)
            .addOnCompleteListener {
                // Download complete. Depending on your app, you could enable the ML
                // feature, or switch from the local model to the remote model, etc.
            }
        // [END mlkit_remote_model_download_listener]
    }

    private fun createInputOutputOptions(): FirebaseModelInputOutputOptions {
        // [START mlkit_create_io_options]
        val inputOutputOptions = FirebaseModelInputOutputOptions.Builder()
            .setInputFormat(0, FirebaseModelDataType.FLOAT32, intArrayOf(1, 8))
            .setOutputFormat(0, FirebaseModelDataType.FLOAT32, intArrayOf(1, 1))
            .build()
        // [END mlkit_create_io_options]
        return inputOutputOptions
    }

    fun runInference(): String {
        var DT = false
        val localModel = FirebaseCustomLocalModel.Builder()
            .setAssetFilePath("DiabetesModel.tflite")
            .build()
        val firebaseInterpreter = createInterpreter(localModel)!!
        val Arr: FloatArray = floatArrayOf(8.0F, 183.0F, 64.0F, 0.0F, 0.0F, 23.3F, 0.672F, 32.0F)
        val mainArr: Array<FloatArray> = arrayOf(Arr)
        val inputOutputOptions = createInputOutputOptions()

        // [START mlkit_run_inference]
        val inputs = FirebaseModelInputs.Builder().add(mainArr).build() // add() as many input arrays as your model requires

        firebaseInterpreter.run(inputs, inputOutputOptions).addOnSuccessListener {


                // [START_EXCLUDE]
                // [START mlkit_read_result]
                val output = it.getOutput<Array<FloatArray>>(0)
//                for (element in output)
//                    for (i in element)
//                        println(i)
                // var Out : String
//                print(output[0][0])
//                var o: Int = output[0][0].toInt()
//                if (o == 1) {
//                    DT = true
//                }
                val outcome = output[0]
                if(outcome[0] == 1.0f){
                    DT = true
                }
            }
            .addOnFailureListener { e ->
                e.printStackTrace()

            }
        // [END mlkit_run_inference]
        if (DT == true) {
            return "You have diabetes"
        } else {
            return "You do not have diabetes"
        }
    }



}