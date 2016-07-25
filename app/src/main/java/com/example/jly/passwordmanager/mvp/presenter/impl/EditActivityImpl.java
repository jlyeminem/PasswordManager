package com.example.jly.passwordmanager.mvp.presenter.impl;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CompoundButton;

import com.example.jly.passwordmanager.mvp.presenter.ActivityPresenter;
import com.example.jly.passwordmanager.mvp.view.EditView;

import me.imid.swipebacklayout.lib.SwipeBackLayout;

public class EditActivityImpl implements ActivityPresenter,
        TextWatcher, AdapterView.OnItemSelectedListener,
        CompoundButton.OnCheckedChangeListener, View.OnFocusChangeListener, DialogInterface.OnClickListener, SwipeBackLayout.SwipeListener, View.OnClickListener{
    private Context mContext;
    private EditView mEditView;

    public EditActivityImpl(Context context, EditView editView) {
        mContext = context;
        mEditView = editView;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

    }

    @Override
    public void getIntent(Intent intent) {

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

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onFocusChange(View view, boolean b) {

    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

    }

    @Override
    public void onClick(DialogInterface dialogInterface, int i) {

    }

    @Override
    public void onScrollStateChange(int state, float scrollPercent) {

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

    }

    @Override
    public void afterTextChanged(Editable editable) {

    }

    @Override
    public void onClick(View view) {

    }
}
