package com.example.jly.passwordmanager;

import android.app.Application;
import android.content.Context;

import com.example.jly.passwordmanager.utils.AppContextUtil;

/**
 * Created by jly on 16-7-22.
 */
public class App extends Application{
    private static Context sContext;

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = this;
        AppContextUtil.init(this);
    }

    public static Context getContext() {
        return sContext;
    }
}
