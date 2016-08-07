package com.example.jly.passwordmanager.mvp.view.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.jly.passwordmanager.mvp.view.fragment.ContentFragment;


public class ContentIndexAdapter extends FragmentPagerAdapter {

    public ContentIndexAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int index) {
        Fragment fragment = new ContentFragment();
        Bundle argus = new Bundle();
        argus.putInt("index", index);
        fragment.setArguments(argus);
        return fragment;
    }

    @Override
    public int getCount() {
        return 4;
    }
}
