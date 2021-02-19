package com.mua.avs;

import android.app.IntentService;
import android.content.Intent;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

public class NotificationIntentService extends IntentService {

    public NotificationIntentService() {
        super(NotificationIntentService.class.getName());
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        switch (intent.getAction()) {
            case "down":
                android.os.Handler downHandler = new android.os.Handler(Looper.getMainLooper());
                downHandler.post(() -> {
                            Log.d("d--mua", "down");
                            Toast.makeText(
                                    getBaseContext(),
                                    "down",
                                    Toast.LENGTH_LONG
                            ).show();
                        }
                );
                break;
            case "rise":
                android.os.Handler riseHandler = new android.os.Handler(Looper.getMainLooper());
                riseHandler.post(() -> {
                            Log.d("d--mua", "rise");
                            Toast.makeText(
                                    getBaseContext(),
                                    "rise",
                                    Toast.LENGTH_LONG
                            ).show();
                        }
                );
                break;
        }
    }
}