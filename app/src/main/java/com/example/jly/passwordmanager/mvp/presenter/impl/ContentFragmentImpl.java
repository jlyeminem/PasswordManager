package com.example.jly.passwordmanager.mvp.presenter.impl;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;

import com.example.jly.passwordmanager.mvp.model.Constants;
import com.example.jly.passwordmanager.mvp.model.bean.Password;
import com.example.jly.passwordmanager.mvp.model.evenbus.EventCenter;
import com.example.jly.passwordmanager.mvp.model.reaml.RealmManager;
import com.example.jly.passwordmanager.mvp.presenter.FragmentPresenter;
import com.example.jly.passwordmanager.mvp.view.ContentView;
import com.example.jly.passwordmanager.mvp.view.activity.EditActivity;
import com.example.jly.passwordmanager.mvp.view.adapter.ContentAdapter;

import java.util.List;


public class ContentFragmentImpl implements FragmentPresenter,ContentAdapter.OnRecyclerItemClickListener {
    private ContentView mContentView;
    private Context mContext;
    private List<Password> mPasswords;
    private int index;
    private ContentAdapter mAdapter;
    private boolean isCreate;

    public ContentFragmentImpl(ContentView contentView, Context context) {
        mContentView = contentView;
        mContext = context;
    }

    @Override
    public void onFirstUserVisible() {
        isCreate = true;
        mPasswords = getPasswords();
        mAdapter = new ContentAdapter(mPasswords,mContext);
        mAdapter.setOnRecyclerItemClickListener(this);
        mContentView.initRecycler(new LinearLayoutManager(mContext),mAdapter);
        if(null != mPasswords && mPasswords.size()>0) {
            mContentView.hideException();
        } else mContentView.showException();
    }

    private List<Password> getPasswords() {
        return RealmManager.getInstances(mContext).selector(mContext, index);
    }

    @Override
    public void onUserVisible() {

    }

    @Override
    public void onUserInvisible() {

    }

    public void getArgus(Bundle arguments) {
        index = arguments.getInt("index");
    }

    @Override
    public void onRecycleItemClick(View view, int position) {
        mContentView.readGo(EditActivity.class,Constants.VIEWMODE,position,this.index);
    }

    public void onEventComing(EventCenter eventCenter) {
        if (isCreate) {
            if (eventCenter.getEventCode() == 1) {
                boolean data = (boolean) eventCenter.getData();
                if (data) {
                    mPasswords = getPasswords();
                    if (null != mPasswords && mPasswords.size() > 0) {
                        mContentView.hideException();
                        mAdapter.addAll(mPasswords);
                    } else {
                        mContentView.showException();
                        mAdapter.clearData();
                    }
                    mAdapter.notifyDataSetChanged();
                }
            } else if (eventCenter.getEventCode() == Constants.EVEN_BUS.CHANGE_PASS_WORD_SHOW) {
                mAdapter.notifyDataSetChanged();
            }
        }
    }
}
