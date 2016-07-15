package com.example.jly.passwordmanager.mvp.view.activity;

import android.os.Bundle;

import com.example.jly.passwordmanager.R;
import com.example.jly.passwordmanager.databinding.ActivityCreateLockBinding;

/**
 * Created by jly on 16-7-15.
 */
public class CreateLockActivity extends BaseActivity {
    private ActivityCreateLockBinding mBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_lock);
        mBinding = (ActivityCreateLockBinding) this.mDataBinding;
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_create_lock;
    }
}
