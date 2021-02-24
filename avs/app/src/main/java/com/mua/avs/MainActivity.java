package com.mua.avs;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private CheckBox cbPlayOnChange;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initPermission();
        initListener();
        init();
    }

    void initPermission(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if(Settings.canDrawOverlays(this)){
                return;
            }
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                    Uri.parse("package:" + getPackageName()));
            startActivity(intent);
        }else{
            Toast
                    .makeText(
                            this,
                            "Please go to Settings -> Apps -> Draw over other apps and add this",
                            Toast.LENGTH_LONG)
                    .show();
        }
    }

    void initView() {
        cbPlayOnChange = findViewById(R.id.cb_play_on_change);
    }

    void initListener() {
        cbPlayOnChange.setOnCheckedChangeListener((buttonView, isChecked) -> {
            //todo : save to sharedprefs
        });
    }

    void init() {
        Intent serviceIntent = new Intent(this, VolumeService.class);
        startService(serviceIntent);
    }

}