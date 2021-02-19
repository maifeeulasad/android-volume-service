package com.mua.avs;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.CheckBox;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private CheckBox cbPlayOnChange;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initListener();
        init();
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