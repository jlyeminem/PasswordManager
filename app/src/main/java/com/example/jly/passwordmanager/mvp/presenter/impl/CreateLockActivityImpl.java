package com.example.jly.passwordmanager.mvp.presenter.impl;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.jly.passwordmanager.R;
import com.example.jly.passwordmanager.databinding.ActivityCreateLockBinding;
import com.example.jly.passwordmanager.mvp.model.bean.LockWarnBean;
import com.example.jly.passwordmanager.mvp.presenter.ActivityPresenter;

/**
 * Created by jly on 16-7-15.
 */
public class CreateLockActivityImpl implements ActivityPresenter {
    private LockWarnBean mLockWarnBean;
    private Context mContext;
    private ActivityCreateLockBinding mBinding;

    public CreateLockActivityImpl(Context context, ActivityCreateLockBinding binding) {
        mContext = context;
        mBinding = binding;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        mLockWarnBean = new LockWarnBean(mContext.getResources().getString(R.string.set_password));
        mBinding.setLockWarn(mLockWarnBean);
    }

    @Override
    public void getIntent(Intent intent) {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onDestroy() {

    }
}
