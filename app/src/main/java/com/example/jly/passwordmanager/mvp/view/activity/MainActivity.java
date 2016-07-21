package com.example.jly.passwordmanager.mvp.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import com.example.jly.passwordmanager.R;
import com.example.jly.passwordmanager.mvp.model.Constants;
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
        },500);
    }

    private void selectActivity() {
        boolean isSuccess = (boolean) SPUtils.get(this, Constants.CREATE_LOCK_SUCCESS,false);
        Intent intent;
        if (!isSuccess) {
            intent = new Intent(this,CreateLockActivity.class);
        } else {
            intent = new Intent(this, CheckLockActivity.class);
        }
        startActivity(intent);
        this.finish();
    }
 
    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }
}
