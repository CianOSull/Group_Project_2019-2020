package com.example.medical_professional_notify_example;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import static com.example.medical_professional_notify_example.notifyClass.CHANNEL_1_ID;
import static com.example.medical_professional_notify_example.notifyClass.CHANNEL_2_ID;
import static com.example.medical_professional_notify_example.notifyClass.CHANNEL_3_ID;

public class MainActivity extends AppCompatActivity {
    private NotificationManagerCompat notificationManager;

    final String subject = "Test subject";
    final String message = "Test message text that should appear.";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        notificationManager = NotificationManagerCompat.from(this);

        Button callGPBtn = (Button) findViewById(R.id.phoneGPBtn);
        Button emailGPBtn = (Button) findViewById(R.id.emailGPBtn);
        Button emailInsurBtn = (Button) findViewById(R.id.emailInsurBtn);
        Button allNotifyBtn = (Button) findViewById(R.id.allNotifyBtn);

        callGPBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createGPPhoneChannel();
            }
        });

        emailGPBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createGPEmail();
            }
        });

        emailInsurBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createInsurEmail();
            }
        });

        allNotifyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                createGPPhoneChannel();
//                createGPEmail();
//                createInsurEmail();
                createGroupChannel();
            }
        });
    }

    public void createGPPhoneChannel(){
        // Create the intent and pending intent
        Intent callIntent = new Intent(Intent.ACTION_DIAL);
        callIntent.setData(Uri.parse("tel:0210000000"));
        callIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent phoneCallPendingIntent = PendingIntent.getActivity(MainActivity.this,
                0, callIntent, 0);

        // Put an image on the notification
        Bitmap largeIcon = BitmapFactory.decodeResource(getResources(), R.drawable.pug_face);

        // Create the notification
        Notification notification = new NotificationCompat.Builder(this, CHANNEL_1_ID)
                .setSmallIcon(R.drawable.ic_phone_notification)
                .setContentTitle("Call your GP!")
                .setContentText("Tap this notificaiton to call your GP!")
                .setLargeIcon(largeIcon)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setColor(Color.RED)
                .setContentIntent(phoneCallPendingIntent)
                .setAutoCancel(true)
                .build();

        // Make the notification appear
        notificationManager.notify(1, notification);
    }

    public void createGPEmail(){
        // Setting up the email intent
        Intent emailGPIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                "mailto","email@email.com", null));
        emailGPIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
        emailGPIntent.putExtra(Intent.EXTRA_TEXT, message);
        emailGPIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingEmailGPIntent = PendingIntent.getActivity(MainActivity.this,
                0, emailGPIntent, 0);

        // Put an image on the notification
        Bitmap largeIcon = BitmapFactory.decodeResource(getResources(), R.drawable.angel_icon);

        // Create the notification
        Notification notification = new NotificationCompat.Builder(this, CHANNEL_2_ID)
                .setSmallIcon(R.drawable.ic_email)
                .setContentTitle("Email your GP!")
                .setContentText("Tap this notificaiton to Email your GP!")
                .setLargeIcon(largeIcon)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setColor(Color.BLUE)
                .setContentIntent(pendingEmailGPIntent)
                .setAutoCancel(true)
                .build();

        // Make the notification appear
        notificationManager.notify(2, notification);
    }

    public void createInsurEmail(){
        // Create the intent and pending intent
        Intent emailGPIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                "mailto","email@email.com", null));
        emailGPIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
        emailGPIntent.putExtra(Intent.EXTRA_TEXT, message);
        emailGPIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingEmailInsurIntent = PendingIntent.getActivity(MainActivity.this,
                0, emailGPIntent, 0);

        // Put an image on the notification
        Bitmap largeIcon = BitmapFactory.decodeResource(getResources(), R.drawable.devil_business_man);

        // Create the notification
        Notification notification = new NotificationCompat.Builder(this, CHANNEL_3_ID)
                .setSmallIcon(R.drawable.ic_email)
                .setContentTitle("Email your Insurance!")
                .setContentText("Tap this notificaiton to Email your Insurance!")
                .setLargeIcon(largeIcon)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setColor(Color.GREEN)
                .setContentIntent(pendingEmailInsurIntent)
                .setAutoCancel(true)
                .build();

        // Make the notification appear
        notificationManager.notify(3, notification);
    }

    public void createGroupChannel(){

       String title1 = "Call your GP!";
       String message1 = "Tap this notificaiton to Call your GP!";
       String title2 = "Email your GP!";
       String message2 = "Tap this notificaiton to Email your GP!";
       String title3 = "Email your Insurance!";
       String message3 = "Tap this notificaiton to Email your Insurance!";


        // Create the calling petent intent
        Intent callIntent = new Intent(Intent.ACTION_DIAL);
        callIntent.setData(Uri.parse("tel:0210000000"));
        callIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent phoneCallPendingIntent = PendingIntent.getActivity(MainActivity.this,
                0, callIntent, 0);

        // Setting up the email intent
        Intent emailGPIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                "mailto","email@email.com", null));
        emailGPIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
        emailGPIntent.putExtra(Intent.EXTRA_TEXT, message);
        emailGPIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingEmailGPIntent = PendingIntent.getActivity(MainActivity.this,
                0, emailGPIntent, 0);

        // Put an image on the notification
        Bitmap largeIcon1 = BitmapFactory.decodeResource(getResources(), R.drawable.pug_face);

        // Create the notification
        Notification notification1 = new NotificationCompat.Builder(this, CHANNEL_1_ID)
                .setSmallIcon(R.drawable.ic_phone_notification)
                .setContentTitle(title1)
                .setContentText(message1)
                .setLargeIcon(largeIcon1)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setGroup("medicinal_information_group")
                .setColor(Color.RED)
                .setContentIntent(phoneCallPendingIntent)
                .setAutoCancel(true)
                .build();

        // Put an image on the notification
        Bitmap largeIcon2 = BitmapFactory.decodeResource(getResources(), R.drawable.angel_icon);

        // Create the notification
        Notification notification2 = new NotificationCompat.Builder(this, CHANNEL_1_ID)
                .setSmallIcon(R.drawable.ic_email)
                .setContentTitle(title2)
                .setContentText(message2)
                .setLargeIcon(largeIcon2)
                .setPriority(NotificationCompat.PRIORITY_LOW)
                .setGroup("medicinal_information_group")
                .setColor(Color.BLUE)
                .setContentIntent(pendingEmailGPIntent)
                .setAutoCancel(true)
                .build();

        // Put an image on the notification
        Bitmap largeIcon3 = BitmapFactory.decodeResource(getResources(), R.drawable.devil_business_man);

        // Create the notification
        Notification notification3 = new NotificationCompat.Builder(this, CHANNEL_1_ID)
                .setSmallIcon(R.drawable.ic_email)
                .setContentTitle(title3)
                .setContentText(message3)
                .setLargeIcon(largeIcon3)
                .setPriority(NotificationCompat.PRIORITY_LOW)
                .setGroup("medicinal_information_group")
                .setColor(Color.GREEN)
                .setContentIntent(pendingEmailGPIntent)
                .setAutoCancel(true)
                .build();

        // Create the summary notification
        Notification summaryNotification = new NotificationCompat.Builder(this, CHANNEL_1_ID)
                .setSmallIcon(R.drawable.ic_book)
                .setStyle(new NotificationCompat.InboxStyle()
                    .addLine(title1 + " " + message1)
                    .addLine(title2 + " " + message2)
                    .addLine(title3 + " " + message3)
                    .setBigContentTitle("3 new messages")
                .setSummaryText("Medical Contact Information"))
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setGroup("medicinal_information_group")
                .setColor(Color.CYAN)
                .setGroupAlertBehavior(NotificationCompat.GROUP_ALERT_ALL)
                .setGroupSummary(true)
                .setAutoCancel(true)
                .build();

        // Make the notification appear
        notificationManager.notify(1, notification3);
        notificationManager.notify(2, notification2);
        notificationManager.notify(3, notification1);
        notificationManager.notify(4, summaryNotification);

    }
}
