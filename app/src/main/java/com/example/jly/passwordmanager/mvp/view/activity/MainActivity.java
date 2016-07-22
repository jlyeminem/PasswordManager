package com.example.jly.passwordmanager.mvp.view.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.View;



import com.example.jly.passwordmanager.R;
import com.example.jly.passwordmanager.databinding.ActivityMainBinding;
import com.example.jly.passwordmanager.mvp.presenter.impl.MainActivityImpl;
import com.example.jly.passwordmanager.mvp.view.MainView;


public class MainActivity extends BaseActivity implements MainView {
    private ActivityMainBinding mBinding;
    private ActionBarDrawerToggle mActionBarDrawerToggle;
    private MainActivityImpl mActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = (ActivityMainBinding) this.mDataBinding;
        mActivity = new MainActivityImpl(mBinding,this,this);
        mActivity.onCreate(savedInstanceState);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    public void initDrawerToggle() {
        mActionBarDrawerToggle = new ActionBarDrawerToggle(this, mBinding.drawerlayout, mBinding.commonToolbar, 0, 0){
            @Override public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                invalidateOptionsMenu();
            }
            @Override public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu();
            }
        };
        mActionBarDrawerToggle.syncState();//使得箭头和三道杠图案和抽屉拉合保持同步
        mActionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        mBinding.drawerlayout.setDrawerListener(mActionBarDrawerToggle);
    }

    @Override
    public void initXViewPager() {

    }

    @Override
    public void initToolBar(Toolbar toolbar) {
        if(toolbar == null) return;
        toolbar.setBackgroundColor(getColorPrimary());
        toolbar.setTitle(R.string.app_name);
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    private int getColorPrimary() {
        TypedValue typedValue = new TypedValue();
        getTheme().resolveAttribute(R.attr.colorPrimary, typedValue, true);
        return typedValue.data;
    }

    @Override
    public void readyGoForResult(Class clazz) {

    }

    @Override
    public void go2Setting() {

    }

    @Override
    public void showSnackBar(String msg) {

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return mActivity.onKeyDown(keyCode,event) || super.onKeyDown(keyCode,event);
    }
}
