package com.example.jly.passwordmanager.mvp.view;


import android.support.v7.widget.Toolbar;


public interface MainView {

    void initDrawerToggle();

    void initXViewPager();

    void initToolBar(Toolbar toolbar);

    void readyGoForResult(Class clazz);

    void go2Setting();

}
