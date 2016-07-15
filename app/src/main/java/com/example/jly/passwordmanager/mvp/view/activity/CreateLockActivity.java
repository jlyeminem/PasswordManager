package com.example.jly.passwordmanager.mvp.view.activity;

import android.os.Bundle;

import com.example.jly.passwordmanager.R;
import com.example.jly.passwordmanager.databinding.ActivityCreateLockBinding;
import com.example.jly.passwordmanager.mvp.presenter.impl.CreateLockActivityImpl;

/**
 * Created by jly on 16-7-15.
 */
public class CreateLockActivity extends BaseActivity {
    private ActivityCreateLockBinding mBinding;
    private CreateLockActivityImpl mActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = (ActivityCreateLockBinding) this.mDataBinding;
        mActivity = new CreateLockActivityImpl(this,mBinding);
        mActivity.onCreate(savedInstanceState);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_create_lock;
    }
}
