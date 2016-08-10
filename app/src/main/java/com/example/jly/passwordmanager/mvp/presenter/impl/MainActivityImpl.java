package com.example.jly.passwordmanager.mvp.presenter.impl;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.view.KeyEvent;
import android.view.MenuItem;

import com.example.jly.passwordmanager.R;
import com.example.jly.passwordmanager.databinding.ActivityMainBinding;
import com.example.jly.passwordmanager.mvp.presenter.ActivityPresenter;
import com.example.jly.passwordmanager.mvp.view.MainView;
import com.example.jly.passwordmanager.mvp.view.activity.EditActivity;
import com.example.jly.passwordmanager.mvp.view.activity.MainActivity;
import com.example.jly.passwordmanager.utils.ShowToast;
import com.jakewharton.rxbinding.view.RxView;

import java.util.concurrent.TimeUnit;

import rx.functions.Action1;


public class MainActivityImpl implements ActivityPresenter,NavigationView.OnNavigationItemSelectedListener {
    private ActivityMainBinding mBinding;
    private Context mContext;
    private MainView mMainView;
    private long exitTime;

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
        FloatingActionButton fab = mBinding.fab;
        RxView.clicks(fab).throttleFirst(500, TimeUnit.MILLISECONDS).subscribe(
                new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        mMainView.readyGoForResult(EditActivity.class);
                    }
                }
        );
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
        int currentSelectedItem = 0;
        switch (menuItem.getItemId()) {
            case R.id.nav_default_type:
                mBinding.drawerlayout.closeDrawers();
                mBinding.content.setCurrentItem(currentSelectedItem);
                //ShowToast.Short("This is the nav_default_type");
                break;
            case R.id.nav_game_type:
                currentSelectedItem = 1;
                mBinding.drawerlayout.closeDrawers();
                mBinding.content.setCurrentItem(currentSelectedItem);
                //ShowToast.Short("This is the nav_game_type");
                break;
            case R.id.nav_mail_type:
                currentSelectedItem = 2;
                mBinding.drawerlayout.closeDrawers();
                mBinding.content.setCurrentItem(currentSelectedItem);
                //ShowToast.Short("This is the nav_mail_type");
                break;
            case R.id.nav_note_type:
                currentSelectedItem = 3;
                mBinding.drawerlayout.closeDrawers();
                mBinding.content.setCurrentItem(currentSelectedItem);
                //ShowToast.Short("This is the nav_note_type");
                break;
            case R.id.nav_setting:
                mBinding.drawerlayout.closeDrawers();
                ShowToast.Short("This is the setting");
                break;
            default:break;
        }
        return true;
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if (mBinding.drawerlayout.isDrawerOpen(GravityCompat.START)) {
                mBinding.drawerlayout.closeDrawer(GravityCompat.START);
                return true;
            }
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                ShowToast.Short("再按一次退出程序");
                exitTime = System.currentTimeMillis();
            } else {
                ((MainActivity)mContext).finish();
            }
            return true;
        }
        return false;
    }
}
