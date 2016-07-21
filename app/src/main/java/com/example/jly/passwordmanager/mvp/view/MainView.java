package com.example.jly.passwordmanager.mvp.view;


import android.support.v7.widget.Toolbar;

/**
 * Created by jly on 16-7-21.
 */
public interface MainView {

    void initDrawerToggle();

    void initXViewPager();

    void initToolBar(Toolbar toolbar);

    void readyGoForResult(Class clazz);

    void go2Setting();

    void showSnackBar(String msg);
}
