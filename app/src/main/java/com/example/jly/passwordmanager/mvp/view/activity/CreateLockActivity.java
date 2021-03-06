package com.example.jly.passwordmanager.mvp.view.activity;

import android.os.Bundle;

import com.example.jly.passwordmanager.R;
import com.example.jly.passwordmanager.databinding.ActivityCreateLockBinding;
import com.example.jly.passwordmanager.mvp.presenter.impl.CreateLockActivityImpl;
import com.example.jly.passwordmanager.mvp.view.LockView;
import com.example.jly.passwordmanager.mvp.view.LockPatternView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class CreateLockActivity extends BaseActivity implements LockView, LockPatternView.OnPatternListener{
    @BindView(R.id.lockView)
    LockPatternView mLockView;
    private CreateLockActivityImpl mActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        ActivityCreateLockBinding mBinding = (ActivityCreateLockBinding) this.mDataBinding;
        mActivity = new CreateLockActivityImpl(this, this,mBinding);
        mActivity.onCreate(savedInstanceState);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_create_lock;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onPatternStart() {
        mActivity.startPress();
    }

    @Override
    public void onPatternCleared() {

    }

    @Override
    public void onPatternCellAdded(List<LockPatternView.Cell> pattern) {

    }

    @Override
    public void onPatternDetected(List<LockPatternView.Cell> pattern) {
        mActivity.check(pattern);
    }

    @Override
    public void initLockPatternView() {
        mLockView.setOnPatternListener(this);
    }

    @Override
    public void lockErrorShow() {
        mLockView.setDisplayMode(LockPatternView.DisplayMode.Wrong);
    }

    @Override
    public void setResults(int isSuccess) {

    }

    @Override
    public void clearPattern() {
        mLockView.clearPattern();
    }
}
