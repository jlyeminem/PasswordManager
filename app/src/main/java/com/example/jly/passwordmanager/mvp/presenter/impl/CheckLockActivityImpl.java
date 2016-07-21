package com.example.jly.passwordmanager.mvp.presenter.impl;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;

import com.example.jly.passwordmanager.R;
import com.example.jly.passwordmanager.databinding.ActivityCheckLockBinding;
import com.example.jly.passwordmanager.mvp.model.bean.LockWarnBean;
import com.example.jly.passwordmanager.mvp.presenter.ActivityPresenter;
import com.example.jly.passwordmanager.mvp.view.LockPatternView;
import com.example.jly.passwordmanager.mvp.view.LockView;
import com.example.jly.passwordmanager.mvp.view.activity.CheckLockActivity;
import com.example.jly.passwordmanager.mvp.view.activity.MainActivity;
import com.example.jly.passwordmanager.utils.LockPatternUtils;

import java.util.List;


public class CheckLockActivityImpl implements ActivityPresenter {
    private Context mContext;
    private ActivityCheckLockBinding mBinding;
    private LockView mLockView;
    private LockWarnBean mLockWarn;

    public CheckLockActivityImpl(Context context, ActivityCheckLockBinding binding, LockView lockView) {
        mContext = context;
        mBinding = binding;
        mLockView = lockView;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        mLockWarn = new LockWarnBean(mContext.getString(R.string.draw_password), Color.WHITE);
        mBinding.setLockWarn(mLockWarn);
        mLockView.initLockPatternView();
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

    public void check(List<LockPatternView.Cell> pattern) {
        if (pattern == null) return;
        LockPatternUtils lockPatternUtils = LockPatternUtils.getInstances(mContext);
        if (lockPatternUtils.checkPattern(pattern)) {
            Intent intent = new Intent(mContext, MainActivity.class);
            mContext.startActivity(intent);
            ((CheckLockActivity) mContext).finish();
        } else {
            mLockWarn = new LockWarnBean(mContext.getString(R.string.wrong_password),Color.RED);
            mBinding.setLockWarn(mLockWarn);
            mLockView.lockErrorShow();
        }
    }
}
