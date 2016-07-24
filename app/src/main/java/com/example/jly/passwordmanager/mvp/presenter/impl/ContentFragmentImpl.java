package com.example.jly.passwordmanager.mvp.presenter.impl;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.example.jly.passwordmanager.mvp.model.bean.Password;
import com.example.jly.passwordmanager.mvp.model.reaml.RealmHelper;
import com.example.jly.passwordmanager.mvp.presenter.FragmentPresenter;
import com.example.jly.passwordmanager.mvp.view.ContentView;
import com.example.jly.passwordmanager.mvp.view.adapter.ContentAdapter;

import java.util.List;


public class ContentFragmentImpl implements FragmentPresenter {
    private ContentView mContentView;
    private Context mContext;
    private List<Password> mPasswords;
    private int index;
    private ContentAdapter mAdapter;

    public ContentFragmentImpl(ContentView contentView, Context context) {
        mContentView = contentView;
        mContext = context;
    }

    @Override
    public void onFirstUserVisible() {
        mPasswords = getPasswords();
        mAdapter = new ContentAdapter(mPasswords,mContext);
        mContentView.initRecycler(new LinearLayoutManager(mContext),mAdapter);
        if(null != mPasswords && mPasswords.size()>0) {
            mContentView.hideException();
        } else mContentView.showException();
    }

    private List<Password> getPasswords() {
        return RealmHelper.getInstance(mContext).getPasswords(mContext,index);
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
}
