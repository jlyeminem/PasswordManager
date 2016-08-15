package com.example.jly.passwordmanager.mvp.view.activity;

import android.os.Bundle;

import com.example.jly.passwordmanager.R;
import com.example.jly.passwordmanager.databinding.ActivityCheckLockBinding;
import com.example.jly.passwordmanager.mvp.presenter.impl.CheckLockActivityImpl;
import com.example.jly.passwordmanager.mvp.view.LockPatternView;
import com.example.jly.passwordmanager.mvp.view.LockView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class CheckLockActivity extends BaseActivity implements LockView,LockPatternView.OnPatternListener{

    @BindView(R.id.lockView)
    LockPatternView mLockView;
    private CheckLockActivityImpl mActivity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        ActivityCheckLockBinding mBinding = (ActivityCheckLockBinding) this.mDataBinding;
        mActivity = new CheckLockActivityImpl(this,mBinding,this);
        mActivity.onCreate(savedInstanceState);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_check_lock;
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
    public void clearPattern() {
        //mLockView.clearPattern();
    }

    @Override
    public void onPatternStart() {

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
}
