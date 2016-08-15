package com.example.jly.passwordmanager.mvp.view.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;



import com.example.jly.passwordmanager.R;
import com.example.jly.passwordmanager.databinding.ActivityMainBinding;
import com.example.jly.passwordmanager.mvp.model.Constants;
import com.example.jly.passwordmanager.mvp.model.evenbus.EventCenter;
import com.example.jly.passwordmanager.mvp.presenter.impl.MainActivityImpl;
import com.example.jly.passwordmanager.mvp.view.MainView;
import com.example.jly.passwordmanager.mvp.view.adapter.ContentIndexAdapter;
import com.example.jly.passwordmanager.utils.ShowToast;

import org.greenrobot.eventbus.EventBus;


public class MainActivity extends BaseActivity implements MainView {
    private static final int MAIN_REQUEST_CODE = 1;
    private static final int SETTING_REQUEST_CODE = 2;
    private static final int EDIT_SAVE = 1;
    private ActivityMainBinding mBinding;

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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.about:
                ShowToast.Short("This is about");
                return true;
            case R.id.setting:
                go2Setting();
                return true;
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void initDrawerToggle() {
        ActionBarDrawerToggle mActionBarDrawerToggle = new ActionBarDrawerToggle(this, mBinding.drawerlayout, mBinding.commonToolbar, 0, 0){
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
        mBinding.drawerlayout.addDrawerListener(mActionBarDrawerToggle);
    }

    @Override
    public void initXViewPager() {
        mBinding.content.setOffscreenPageLimit(4);
        ContentIndexAdapter contentIndexAdapter = new ContentIndexAdapter(getSupportFragmentManager());
        mBinding.content.setAdapter(contentIndexAdapter);
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
        Intent intent = new Intent(this,clazz);
        intent.putExtra(EditActivity.CREATE_MODE,Constants.ADDMODE);
        startActivityForResult(intent,MAIN_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == MAIN_REQUEST_CODE) {
            if (resultCode == EDIT_SAVE) {
                int MAIN_EVENT_SUCCESS = 1;
                EventCenter eventCenter = new EventCenter(MAIN_EVENT_SUCCESS, true);
                EventBus.getDefault().post(eventCenter);
            }
        }
    }

    @Override
    public void go2Setting() {
        Intent intent = new Intent(this, SettingActivity.class);
        startActivityForResult(intent, SETTING_REQUEST_CODE);
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return mActivity.onKeyDown(keyCode,event) || super.onKeyDown(keyCode,event);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
