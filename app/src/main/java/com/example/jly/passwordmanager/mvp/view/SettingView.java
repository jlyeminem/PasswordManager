package com.example.jly.passwordmanager.mvp.view;

import android.content.Intent;
import android.os.Bundle;

/**
 * Created by yezhidong on 2016/1/27.
 */
public interface SettingView {

    void findView();
    void initState(boolean isOpen);
    void initOpenShow(boolean isOpen);
    void readyGo(Class clazz, Intent intent);
    void showSnackBar(String msg);
}
