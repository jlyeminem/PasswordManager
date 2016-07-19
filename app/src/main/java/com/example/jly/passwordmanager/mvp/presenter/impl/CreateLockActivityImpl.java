package com.example.jly.passwordmanager.mvp.presenter.impl;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.jly.passwordmanager.R;
import com.example.jly.passwordmanager.databinding.ActivityCreateLockBinding;
import com.example.jly.passwordmanager.mvp.model.bean.LockWarnBean;
import com.example.jly.passwordmanager.mvp.presenter.ActivityPresenter;
import com.example.jly.passwordmanager.mvp.view.CreateLockView;
import com.example.jly.passwordmanager.mvp.view.LockPatternView;
import com.example.jly.passwordmanager.utils.LockPatternUtils;

import java.util.List;

/**
 * CreateLockActivity具体实现
 * Created by jly on 16-7-15.
 */
public class CreateLockActivityImpl implements ActivityPresenter {
    private LockWarnBean mLockWarnBean;
    private Context mContext;
    private ActivityCreateLockBinding mBinding;
    private CreateLockView mCreateLockView;
    private boolean isFirstDone = false;

    public CreateLockActivityImpl(Context context, CreateLockView createLockView, ActivityCreateLockBinding binding) {
        mContext = context;
        mCreateLockView = createLockView;
        mBinding = binding;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        mLockWarnBean = new LockWarnBean(mContext.getResources().getString(R.string.set_password));
        mBinding.setLockWarn(mLockWarnBean);
        mCreateLockView.initLockPatternView();
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

    public void startPress() {
        mLockWarnBean.setLockWarn(mContext.getResources().getString(R.string.startPress));
        mBinding.setLockWarn(mLockWarnBean);
    }

    public void fingerUpError() {
        mLockWarnBean.setLockWarn(mContext.getString(R.string.password_too_short));
        mBinding.setLockWarn(mLockWarnBean);
    }

    public void fingerSecondError() {
        mLockWarnBean.setLockWarn(mContext.getString(R.string.finger_second_error));
        mBinding.setLockWarn(mLockWarnBean);
    }

    public void fingerFisrtPressDone() {
        mLockWarnBean.setLockWarn(mContext.getString(R.string.finger_first_done));
        mBinding.setLockWarn(mLockWarnBean);
    }

    public void fingerAllDone() {
        mLockWarnBean.setLockWarn(mContext.getString(R.string.finger_all_done));
        mBinding.setLockWarn(mLockWarnBean);
    }

    public void check(List<LockPatternView.Cell> pattern) {
        if (pattern == null) return;
        if (pattern.size() < LockPatternUtils.MIN_LOCK_PATTERN_SIZE){
            if (!isFirstDone) {
                fingerUpError();
            } else {
                fingerSecondError();
            }
            mCreateLockView.lockErrorShow();
        } else {
            if (!isFirstDone) {
                fingerFisrtPressDone();
                LockPatternUtils lockPatternUtils = LockPatternUtils.getInstances(mContext);
                lockPatternUtils.saveLockPattern(pattern);
                mCreateLockView.clearPattern();
                isFirstDone = true;
            } else {
                LockPatternUtils lockPatternUtils = LockPatternUtils.getInstances(mContext);
                if (lockPatternUtils.checkPattern(pattern)) {
                    fingerAllDone();
                } else {
                    fingerSecondError();
                    mCreateLockView.lockErrorShow();
                }
            }
        }

    }
}
