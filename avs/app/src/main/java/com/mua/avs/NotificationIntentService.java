package com.mua.avs;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Looper;

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
                            AudioManager audioManager = (AudioManager) getApplicationContext().getSystemService(Context.AUDIO_SERVICE);
                            audioManager.adjustVolume(AudioManager.ADJUST_LOWER, AudioManager.FLAG_PLAY_SOUND);
                        }
                );
                break;
            case "rise":
                android.os.Handler riseHandler = new android.os.Handler(Looper.getMainLooper());
                riseHandler.post(() -> {
                            AudioManager audioManager = (AudioManager) getApplicationContext().getSystemService(Context.AUDIO_SERVICE);
                            audioManager.adjustVolume(AudioManager.ADJUST_RAISE, AudioManager.FLAG_PLAY_SOUND);
                        }
                );
                break;
        }
    }
}