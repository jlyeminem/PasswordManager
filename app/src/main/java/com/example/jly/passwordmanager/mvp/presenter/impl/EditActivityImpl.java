package com.example.jly.passwordmanager.mvp.presenter.impl;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CompoundButton;

import com.example.jly.passwordmanager.R;
import com.example.jly.passwordmanager.mvp.model.bean.Password;
import com.example.jly.passwordmanager.mvp.model.reaml.RealmManager;
import com.example.jly.passwordmanager.mvp.presenter.ActivityPresenter;
import com.example.jly.passwordmanager.mvp.view.EditView;
import com.example.jly.passwordmanager.utils.TimeUtils;

import java.util.ArrayList;
import java.util.Arrays;

import me.imid.swipebacklayout.lib.SwipeBackLayout;

public class EditActivityImpl implements ActivityPresenter,
        TextWatcher, AdapterView.OnItemSelectedListener,
        CompoundButton.OnCheckedChangeListener, View.OnFocusChangeListener, DialogInterface.OnClickListener, SwipeBackLayout.SwipeListener, View.OnClickListener{

    private Context mContext;
    private EditView mEditView;
    private int position;
    private int positionType;
    private int mPosition = 0;
    private Password mPassword;
    private boolean isEdit;
    private String mPositiveButtonMsg;
    private int createMode;

    public EditActivityImpl(Context context, EditView editView) {
        mContext = context;
        mEditView = editView;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        String[] strings = mContext.getResources().getStringArray(R.array.spinner_item);
        ArrayList<String> arrayList = new ArrayList<>();
        for (String str:Arrays.asList(strings)) {
            arrayList.add(str);
        }
        mEditView.initSpinner(arrayList);
        mEditView.getSwipeBack().addSwipeListener(this);
    }

    @Override
    public void getIntent(Intent intent) {
        createMode = intent.getIntExtra("CREATE_MODE", 1);
        switch (createMode) {
            case 0://查看
                position = intent.getIntExtra("position",0);
                //密码类型
                mPosition = positionType = intent.getIntExtra("positionType", 0);
                ArrayList<Password> passwords = getPasswords(positionType);
                mPassword = passwords.get(position);
                mEditView.initViewModel(mPassword, positionType);
                mEditView.setToolBarTitle(R.string.view_mode);
                mEditView.setTime(TimeUtils.getTime(mPassword.getTime()));
                isEdit = false;
                break;
        }
    }

    private ArrayList<Password> getPasswords(int positionType) {
        return RealmManager.getInstances(mContext).selector(mContext,positionType);
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

    public boolean onOptionItemSelected(MenuItem item){
        switch (item.getItemId()) {
            case R.id.done:
                saveData();
                return true;

            case android.R.id.home:
                return comeBack();
            default: return false;
        }
    }

    private boolean comeBack() {
        if (isEdit) {
            String userName = mEditView.getUserName();
            String passWord = mEditView.getPassWord();
            mEditView.hideKeyBoard();
            if (positionType != mPosition || !TextUtils.equals(userName, mPassword.getUserName()) || !TextUtils.equals(passWord, mPassword.getPassword())) {
                mPositiveButtonMsg = "保存";
                mEditView.showDialog("密码还未保存，是否先保存在退出", mPositiveButtonMsg);
            } else {
                mEditView.finishActivity();
            }
        } else {
            mEditView.hideKeyBoard();
            mEditView.finishActivity();
        }
        return true;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        isEdit = true;
        mPosition = i;
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onFocusChange(View view, boolean b) {
        if (b) {
            isEdit = true;
            mEditView.setToolBarTitle(R.string.edit_mode);
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        mEditView.setPassWordVisible(b);
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        if (which == DialogInterface.BUTTON_POSITIVE) {
            if (TextUtils.equals(mPositiveButtonMsg, "确定")) {
                RealmManager.delete(mContext, mPassword, position);
                mEditView.finishActivity();
            } else {
                saveData();
            }
        } else if (which == DialogInterface.BUTTON_NEGATIVE) {
            if (!TextUtils.equals(mPositiveButtonMsg, "确定")) {
                mEditView.hideKeyBoard();
                mEditView.finishActivity();
            }
        }
    }

    private void saveData() {
        String titleName = mEditView.getTitleName();
        String userName = mEditView.getUserName();
        String passWord = mEditView.getPassWord();
        String memoInfo = mEditView.getMemoInfo();
        Password password = new Password(mPosition, titleName, userName, passWord, TimeUtils.getCurrentTimeInLong(), memoInfo);
        switch (createMode) {
            case 0:
                if (!RealmManager.update(mContext, password)) {
                    mEditView.showSnackToast("修改失败");
                    mEditView.hideKeyBoard();
                    return;
                }
                break;
            case 1:
                if (RealmManager.save(mContext, password)) {
                    mEditView.showSnackToast("保存失败，已经存在-"+password.getTitle()+"-的标题");
                    mEditView.hideKeyBoard();
                    return;
                }
                break;
        }

        mEditView.hideKeyBoard();
        mEditView.finishActivity();
    }

    @Override
    public void onScrollStateChange(int state, float scrollPercent) {
        mEditView.hideKeyBoard();
    }

    @Override
    public void onEdgeTouch(int edgeFlag) {

    }

    @Override
    public void onScrollOverThreshold() {

    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        String titleName = mEditView.getTitleName();
        String userName = mEditView.getUserName();
        String passWord = mEditView.getPassWord();
        if (!TextUtils.isEmpty(passWord)) {
            if (!TextUtils.isEmpty(titleName) && !TextUtils.isEmpty(userName)) {
                mEditView.setItemMenuVisible(true);
            } else {
                mEditView.setItemMenuVisible(false);
            }
        } else {
            mEditView.setItemMenuVisible(false);
        }
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.deleteButton) {
            mPositiveButtonMsg = "确定";
            mEditView.showDialog("将永久删除该密码备忘记录~~", mPositiveButtonMsg);
        }
    }
}
