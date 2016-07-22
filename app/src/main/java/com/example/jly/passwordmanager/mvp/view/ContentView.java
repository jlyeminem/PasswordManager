package com.example.jly.passwordmanager.mvp.view;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by jly on 16-7-22.
 */
public interface ContentView {
    void initRecycler(LinearLayoutManager linearLayoutManager, RecyclerView.Adapter adapter);
    void readGo(Class clazz, int type, int position, int positionType);
    void hideException();
    void showException();
}
