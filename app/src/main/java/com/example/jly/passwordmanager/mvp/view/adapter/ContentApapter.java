package com.example.jly.passwordmanager.mvp.view.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.jly.passwordmanager.mvp.view.fragment.ContentFragment;

/**
 * Created by jly on 16-7-22.
 */
public class ContentApapter extends FragmentPagerAdapter {

    public ContentApapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = new ContentFragment();
        return fragment;
    }

    @Override
    public int getCount() {
        return 4;
    }
}
