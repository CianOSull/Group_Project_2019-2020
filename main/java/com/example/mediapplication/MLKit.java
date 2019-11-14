package com.example.mediapplication;

import android.widget.TextView;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.ml.common.FirebaseMLException;
import com.google.firebase.ml.common.modeldownload.FirebaseModelDownloadConditions;
import com.google.firebase.ml.common.modeldownload.FirebaseModelManager;
import com.google.firebase.ml.custom.FirebaseCustomLocalModel;
import com.google.firebase.ml.custom.FirebaseCustomRemoteModel;
import com.google.firebase.ml.custom.FirebaseModelDataType;
import com.google.firebase.ml.custom.FirebaseModelInputOutputOptions;
import com.google.firebase.ml.custom.FirebaseModelInputs;
import com.google.firebase.ml.custom.FirebaseModelInterpreter;
import com.google.firebase.ml.custom.FirebaseModelInterpreterOptions;
import com.google.firebase.ml.custom.FirebaseModelOutputs;

public class MLKit {
    Boolean res = false;
    private float out;
    float[][] output;

    private FirebaseCustomRemoteModel configureHostedModelSource() {
        // [START mlkit_cloud_model_source]
        FirebaseCustomRemoteModel remoteModel =
                new FirebaseCustomRemoteModel.Builder("diabeties_predict_model").build();
        return remoteModel;
    }

    private void startModelDownloadTask(FirebaseCustomRemoteModel remoteModel) {
        // [START mlkit_model_download_task]
        FirebaseModelDownloadConditions conditions = new FirebaseModelDownloadConditions.Builder()
                .requireWifi()
                .build();
        FirebaseModelManager.getInstance().download(remoteModel, conditions)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        // Success.
                    }
                });
        // [END mlkit_model_download_task]
    }

    private FirebaseCustomLocalModel configureLocalModelSource() {
        // [START mlkit_local_model_source]
        FirebaseCustomLocalModel localModel = new FirebaseCustomLocalModel.Builder()
                .setAssetFilePath("DiabetesModel.tflite")
                .build();
        // [END mlkit_local_model_source]
        return localModel;
    }

    private FirebaseModelInterpreter createInterpreter(FirebaseCustomLocalModel localModel) throws FirebaseMLException {
        // [START mlkit_create_interpreter]
        FirebaseModelInterpreter interpreter = null;
        try {
            FirebaseModelInterpreterOptions options =
                    new FirebaseModelInterpreterOptions.Builder(localModel).build();
            interpreter = FirebaseModelInterpreter.getInstance(options);
        } catch (FirebaseMLException e) {
            // ...
        }
        // [END mlkit_create_interpreter]

        return interpreter;
    }
    private void checkModelDownloadStatus(
            final FirebaseCustomRemoteModel remoteModel,
            final FirebaseCustomLocalModel localModel) {
        // [START mlkit_check_download_status]
        FirebaseModelInterpreter interpreter;
        FirebaseModelManager.getInstance().isModelDownloaded(remoteModel)
                .addOnSuccessListener(new OnSuccessListener<Boolean>() {
                    @Override
                    public void onSuccess(Boolean isDownloaded) {
                        FirebaseModelInterpreterOptions options;
                        if (isDownloaded) {
                            options = new FirebaseModelInterpreterOptions.Builder(remoteModel).build();
                        } else {
                            options = new FirebaseModelInterpreterOptions.Builder(localModel).build();
                        }
                        try {
                            FirebaseModelInterpreter interpreter = FirebaseModelInterpreter.getInstance(options);
                            // ...
                        } catch (FirebaseMLException e) {

                        }
                    }
                });
        // [END mlkit_check_download_status]
    }

    private void addDownloadListener(
            FirebaseCustomRemoteModel remoteModel,
            FirebaseModelDownloadConditions conditions) {
        // [START mlkit_remote_model_download_listener]
        FirebaseModelManager.getInstance().download(remoteModel, conditions)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void v) {
                        // Download complete. Depending on your app, you could enable
                        // the ML feature, or switch from the local model to the remote
                        // model, etc.
                    }
                });
        // [END mlkit_remote_model_download_listener]
    }

    private FirebaseModelInputOutputOptions createInputOutputOptions() throws FirebaseMLException {
        // [START mlkit_create_io_options]
        FirebaseModelInputOutputOptions inputOutputOptions =
                new FirebaseModelInputOutputOptions.Builder()
                        .setInputFormat(0, FirebaseModelDataType.FLOAT32, new int[]{1, 8})
                        .setOutputFormat(0, FirebaseModelDataType.FLOAT32, new int[]{1, 1})
                        .build();
        // [END mlkit_create_io_options]

        return inputOutputOptions;
    }

    public float getOutStart() {
        return out;
    }

//    public void setOut(float[] out) {
//        this.out = out;
//    }

    public void runInference(final TextView T) throws FirebaseMLException {
        FirebaseCustomLocalModel localModel = configureLocalModelSource();


        FirebaseModelInterpreter firebaseInterpreter = createInterpreter(localModel);

        float[][] input = {{(float) 8.0,(float) 183.0,(float) 64.0,(float) 0.0,(float) 0.0,(float) 23.3,(float) 0.672,(float) 32.0}};
        FirebaseModelInputOutputOptions inputOutputOptions = createInputOutputOptions();

        // [START mlkit_run_inference]
        FirebaseModelInputs inputs = new FirebaseModelInputs.Builder()
                .add(input)  // add() as many input arrays as your model requires
                .build();
        firebaseInterpreter.run(inputs, inputOutputOptions)
                .addOnSuccessListener(
                        new OnSuccessListener<FirebaseModelOutputs>() {
                            @Override
                            public void onSuccess(FirebaseModelOutputs result) {
                                // [START_EXCLUDE]
                                // [START mlkit_read_result]
                                output = result.getOutput(0);
                                if(output[0][0] == 1.0){
                                    T.setText("You have Diabates.");
                                }
                                else{
                                    T.setText("You do not have Diabets.");
                                }

                            }
                        })
                .addOnFailureListener(
                        new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                // Task failed with an exception
                                // ...
                            }
                        });
        // [END mlkit_run_inference]
    }

//    public Boolean Run(){
//        try {
//            runInference();
//        } catch (FirebaseMLException e) {
//            e.printStackTrace();
//        }
//        return res;
//
//    }
}
