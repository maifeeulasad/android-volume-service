package com.mua.avs;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.widget.RemoteViews;

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
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(getPackageName(),
                    "Volume Service",
                    NotificationManager.IMPORTANCE_HIGH);
            NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

            if (manager != null) {
                manager.createNotificationChannel(channel);
            }

            RemoteViews notificationLayout = new RemoteViews(getPackageName(), R.layout.notification_volume);
            RemoteViews notificationLayoutExpanded = new RemoteViews(getPackageName(), R.layout.notification_volume);

            Intent downIntent = new Intent(this, NotificationIntentService.class);
            downIntent.setAction("down");
            notificationLayoutExpanded
                    .setOnClickPendingIntent(
                            R.id.btn_volume_down,
                            PendingIntent.getService(
                                    this,
                                    0,
                                    downIntent,
                                    PendingIntent.FLAG_UPDATE_CURRENT
                            )
                    );

            Intent riseIntent = new Intent(this, NotificationIntentService.class);
            riseIntent.setAction("rise");
            notificationLayoutExpanded
                    .setOnClickPendingIntent(
                            R.id.btn_volume_rise,
                            PendingIntent.getService(
                                    this,
                                    0,
                                    riseIntent,
                                    PendingIntent.FLAG_UPDATE_CURRENT
                            )
                    );

            NotificationCompat.Builder builder
                    = new NotificationCompat.Builder(this, getPackageName())
                    .setSmallIcon(R.drawable.ic_launcher_background)
                    .setStyle(new NotificationCompat.DecoratedCustomViewStyle())
                    .setCustomContentView(notificationLayout)
                    .setCustomBigContentView(notificationLayoutExpanded)
                    .setContentTitle("Volume Service")
                    .setContentText("Volume Service is Running")
                    .setOngoing(true)
                    .setPriority(NotificationCompat.PRIORITY_HIGH);

            Notification notification = builder.build();

            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
            notificationManager.notify(0, notification);
        }
    }


}