package com.mua.avs;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;


public class VolumeService extends Service {

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        createNotification();
        return Service.START_STICKY;
    }


    void createNotification() {
        NotificationCompat.Builder builder
                = new NotificationCompat.Builder(this, "muavolume")
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("Volume Service")
                .setContentText("Volume Service is Running")
                .setOngoing(true)
                .setPriority(NotificationCompat.PRIORITY_HIGH);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(0, builder.build());
    }


}