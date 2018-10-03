package io.github.mrrobi.uiuportal2;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;

import java.util.HashMap;
import java.util.Random;

/**
 * Created by mrrob on 9/28/2018.
 */

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    private static final String TAG = "MyFirebaseIIDService";
    FirebaseRemoteConfig remoteConfig = FirebaseRemoteConfig.getInstance();
    @Override public void onMessageReceived(RemoteMessage remoteMessage) {

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        int notificationId = new Random().nextInt(60000);





        remoteConfig.setConfigSettings(new FirebaseRemoteConfigSettings.Builder()
                .setDeveloperModeEnabled(true)
                .build()
        );

        HashMap<String,Object> defaults = new HashMap<>();
        defaults.put("update_req",false);
        defaults.put("app_version","1.0.8");
        defaults.put("update_url","https://drive.google.com/open?id=1CxgY3Hfm4oSqG5hbDZBCCgPeg_MEPmfi");
        defaults.put("notice","http://www.uiu.ac.bd/notices/");

        final Task<Void> fetch = remoteConfig.fetch(0);

        fetch.addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                remoteConfig.activateFetched();
            }
        });


        String notice = remoteConfig.getString("notice");
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(notice));
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(getApplicationContext())
                .setSmallIcon(R.drawable.ic_android_black_24dp)  //a resource for your custom small icon
                .setContentTitle(remoteMessage.getNotification().getTag()) //the "title" value you sent in your notification
                .setContentText(remoteMessage.getNotification().getBody()) //ditto
                .setColor(getColor(R.color.colorAccent))
                .setPriority(100)
                .setContentIntent(pendingIntent)
                .setSubText("Tap to view the website.")//dismisses the notification on click
                .setSound(defaultSoundUri)
                .setAutoCancel(true);

        notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(notificationId /* ID of notification */, notificationBuilder.build());

    }
}
