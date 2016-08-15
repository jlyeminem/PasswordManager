package com.example.jly.passwordmanager.mvp.view;


import com.example.jly.passwordmanager.mvp.model.bean.Password;

import java.util.List;

import me.imid.swipebacklayout.lib.SwipeBackLayout;


public interface EditView {

    void initSpinner(List<String> data);
    void initCreateModel();
    void initViewModel(Password pw, int positionType);
    String getTitleName();
    String getUserName();
    String getPassWord();
    String getMemoInfo();
    void setTime(String time);
    void showSnackToast(String msg);
    void setItemMenuVisible(boolean visible);
    void finishActivity();
    void setPassWordVisible(boolean visible);
    void hideKeyBoard();
    void setToolBarTitle(int resId);
    void showDialog(String msg, String positiveMsg);
    SwipeBackLayout getSwipeBack();
}
