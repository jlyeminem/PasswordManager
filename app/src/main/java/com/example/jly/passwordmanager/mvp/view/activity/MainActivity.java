package com.example.jly.passwordmanager.mvp.view.activity;

import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import com.example.jly.passwordmanager.R;
import com.example.jly.passwordmanager.utils.SPUtils;


public class MainActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                selectActivity();
            }
        },100);
    }

    private void selectActivity() {
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }
}
