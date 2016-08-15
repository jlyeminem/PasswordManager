package com.example.jly.passwordmanager.mvp.presenter.impl;


import android.content.Context;
import android.content.Intent;
import android.preference.Preference;
import android.text.TextUtils;

import com.example.jly.passwordmanager.mvp.model.Constants;
import com.example.jly.passwordmanager.mvp.model.evenbus.EventCenter;
import com.example.jly.passwordmanager.mvp.presenter.FragmentPresenter;
import com.example.jly.passwordmanager.mvp.view.SettingView;
import com.example.jly.passwordmanager.mvp.view.activity.CreateLockActivity;
import com.example.jly.passwordmanager.utils.SPUtils;

import org.greenrobot.eventbus.EventBus;

public class SettingImpl implements FragmentPresenter {
    private final Context mContext;
    private final SettingView mSettingView;
    private Boolean isOpen;
    private boolean isOpenShow;

    public SettingImpl(Context context, SettingView settingView) {
        mContext = context;
        mSettingView = settingView;
    }


    @Override
    public void onFirstUserVisible() {
        mSettingView.findView();
        isOpen = (Boolean) SPUtils.get(mContext, Constants.SETTING.OPEN_GESTURE,true);
        isOpenShow = (boolean) SPUtils.get(mContext,Constants.SETTING.OPEN_PASS_WORD_SHOW,true);
        mSettingView.initOpenShow(isOpenShow);
        mSettingView.initState(isOpen);
    }

    @Override
    public void onUserVisible() {

    }

    @Override
    public void onUserInvisible() {

    }

    public void onPreferenceTreeClick(Preference preference) {
        String key = preference.getKey();
        if (TextUtils.equals(key, "开启手势密码")) {
            isOpen = !isOpen;
            SPUtils.put(mContext, Constants.SETTING.OPEN_GESTURE, isOpen);
        } else if (TextUtils.equals(key, "首页密码可见")) {
            isOpenShow = !isOpenShow;
            SPUtils.put(mContext, Constants.SETTING.OPEN_PASS_WORD_SHOW, isOpenShow);
            EventBus.getDefault().post(new EventCenter(Constants.EVEN_BUS.CHANGE_PASS_WORD_SHOW));
        } else if (TextUtils.equals(key, "修改手势密码")) {
            Intent intent = new Intent(mContext, CreateLockActivity.class);
            intent.putExtra("CREATE_MODE", Constants.UPDATE_GESTURE);
            mSettingView.readyGo(CreateLockActivity.class, intent);
        }
    }
}
