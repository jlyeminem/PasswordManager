package com.example.jly.passwordmanager.mvp.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceScreen;
import android.support.design.widget.Snackbar;

import com.example.jly.passwordmanager.R;
import com.example.jly.passwordmanager.mvp.presenter.impl.SettingImpl;
import com.example.jly.passwordmanager.mvp.view.SettingView;
import com.jenzz.materialpreference.SwitchPreference;


public class SettingFragment extends PreferenceFragment implements SettingView {
    private static final String PREFERENCE_NAME = "PM.setting";

    private SettingImpl settingFImpl;
    private SwitchPreference openGesture;
    private SwitchPreference openShow;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.setting_preference_xml);
        getPreferenceManager().setSharedPreferencesName(PREFERENCE_NAME);
        settingFImpl = new SettingImpl(getActivity(),this);
        settingFImpl.onFirstUserVisible();
    }

    @Override
    public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen, Preference preference) {
        settingFImpl.onPreferenceTreeClick(preference);
        return super.onPreferenceTreeClick(preferenceScreen, preference);
    }

    @Override
    public void findView() {
        openGesture = (SwitchPreference) findPreference("开启手势密码");
        openShow = (SwitchPreference) findPreference("首页密码可见");
    }

    @Override
    public void initState(boolean isOpen) {
        openGesture.setChecked(isOpen);
    }

    @Override
    public void initOpenShow(boolean isOpen) {
        openShow.setChecked(isOpen);
    }

    @Override
    public void readyGo(Class clazz, Intent intent) {
        startActivityForResult(intent,0);
    }

    @Override
    public void showSnackBar(String msg) {
        Snackbar.make(getView(), msg, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 1) {
            showSnackBar("修改成功");
        } else if (resultCode == 0) {
            showSnackBar("放弃修改");
        }
    }
}
