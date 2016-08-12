package com.example.jly.passwordmanager.mvp.view.activity;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.jly.passwordmanager.R;
import com.example.jly.passwordmanager.mvp.view.fragment.SettingFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SettingActivity extends BaseSwipeBackActivity {

    @BindView(R.id.common_toolbar)
    Toolbar mCommonToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        setFragment();
        initToolbar();
    }

    private void initToolbar() {
        mCommonToolbar.setTitleTextColor(Color.WHITE);
        mCommonToolbar.setTitle("设置");
        setSupportActionBar(mCommonToolbar);
        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    private void setFragment() {
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.container,new SettingFragment())
                .commit();
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_setting;
    }
}
