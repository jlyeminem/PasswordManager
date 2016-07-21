package com.example.jly.passwordmanager.mvp.presenter.impl;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.view.MenuItem;

import com.example.jly.passwordmanager.R;
import com.example.jly.passwordmanager.databinding.ActivityMainBinding;
import com.example.jly.passwordmanager.mvp.presenter.ActivityPresenter;
import com.example.jly.passwordmanager.mvp.view.MainView;


public class MainActivityImpl implements ActivityPresenter,NavigationView.OnNavigationItemSelectedListener {
    private ActivityMainBinding mBinding;
    private Context mContext;
    private MainView mMainView;

    public MainActivityImpl(ActivityMainBinding binding, Context context, MainView mainView) {
        mBinding = binding;
        mContext = context;
        mMainView = mainView;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        mMainView.initToolBar(mBinding.commonToolbar);
        mMainView.initDrawerToggle();
        mMainView.initXViewPager();
        mBinding.navigationView.setCheckedItem(R.id.nav_default_type);
        mBinding.navigationView.setNavigationItemSelectedListener(this);
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

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        return false;
    }
}
