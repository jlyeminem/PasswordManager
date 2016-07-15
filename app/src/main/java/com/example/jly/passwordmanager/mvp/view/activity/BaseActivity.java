package com.example.jly.passwordmanager.mvp.view.activity;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.jly.passwordmanager.R;

public abstract class BaseActivity extends AppCompatActivity {

    protected ViewDataBinding mDataBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDataBinding = DataBindingUtil.setContentView(this,getContentView());
    }

    protected abstract int getContentView();
}
