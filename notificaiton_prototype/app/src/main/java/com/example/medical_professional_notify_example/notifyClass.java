package com.example.medical_professional_notify_example;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

public class notifyClass extends Application {
    public static final String CHANNEL_1_ID = "phone_channel";
    public static final String CHANNEL_2_ID = "email_gp_channel";
    public static final String CHANNEL_3_ID = "email_insurnace_channel";

    public void onCreate(){
        super.onCreate();

        createNotificaitonChannels();
    }

    public void createNotificaitonChannels(){
        if(Build.VERSION_CODES.O <= Build.VERSION.SDK_INT){
            // This is on different levels just to show the arguments it takes
            NotificationChannel phone_channel = new NotificationChannel(
                    CHANNEL_1_ID,
                    "Call your GP",
                    NotificationManager.IMPORTANCE_HIGH
            );

            // phone_channel.method() can allow you set if it vibrates or lights appear
            phone_channel.setDescription("Tap this notification to call your GP!");

            // This is on different levels just to show the arguments it takes
            NotificationChannel email_gp_channel = new NotificationChannel(
                    CHANNEL_2_ID,
                    "Email your GP",
                    NotificationManager.IMPORTANCE_DEFAULT
            );

            // phone_channel.method() can allow you set if it vibrates or lights appear
            email_gp_channel.setDescription("Tap this notification to email your GP!");

            // This is on different levels just to show the arguments it takes
            NotificationChannel email_insurance_channel = new NotificationChannel(
                    CHANNEL_3_ID,
                    "Email your Insurance",
                    NotificationManager.IMPORTANCE_LOW
            );

            // phone_channel.method() can allow you set if it vibrates or lights appear
            email_insurance_channel.setDescription("Tap this notification to email your Insurance!");

            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(phone_channel);
            manager.createNotificationChannel(email_gp_channel);
            manager.createNotificationChannel(email_insurance_channel);

        }
    }
}
