package com.example.jly.passwordmanager.mvp.view.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.jly.passwordmanager.R;
import com.example.jly.passwordmanager.mvp.model.Constants;
import com.example.jly.passwordmanager.mvp.model.evenbus.EventCenter;
import com.example.jly.passwordmanager.mvp.presenter.impl.ContentFragmentImpl;
import com.example.jly.passwordmanager.mvp.view.ContentView;
import com.example.jly.passwordmanager.mvp.view.activity.EditActivity;


import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ContentFragment extends Fragment implements ContentView{

    private static final int INDEX_FRAGMENT_REQUEST_CODE = 2;
    private static final int EDIT_SAVE = 1;
    private static final int SUCCESS = 1;

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.exception)
    LinearLayout mException;

    private Activity mActivity;
    private ContentFragmentImpl mContentFragment;
    private int INDEX_EVENT_SUCCESS = 1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_content, container, false);
        ButterKnife.bind(this, view);
        EventBus.getDefault().register(this);
        mContentFragment = new ContentFragmentImpl(this,getContext());
        mContentFragment.getArgus(getArguments());
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mActivity = getActivity();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mContentFragment.onFirstUserVisible();

    }

    @Override
    public void initRecycler(LinearLayoutManager linearLayoutManager, RecyclerView.Adapter adapter) {
        mRecyclerView.setVisibility(View.VISIBLE);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void readGo(Class clazz, int type, int position, int positionType) {
        Intent intent = new Intent(mActivity, EditActivity.class);
        intent.putExtra("CREATE_MODE", Constants.VIEWMODE);
        intent.putExtra("position", position);
        intent.putExtra("positionType", positionType);
        startActivityForResult(intent, INDEX_FRAGMENT_REQUEST_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == INDEX_FRAGMENT_REQUEST_CODE) {
            if (resultCode == EDIT_SAVE ) {
                EventCenter eventCenter = new EventCenter(INDEX_EVENT_SUCCESS, true);
                EventBus.getDefault().post(eventCenter);
            }
        }
    }

    @Subscribe
    public void onMessageEvent(EventCenter event) {
        if (event != null) {
            onEventComing(event);
        }
    }

    public void onEventComing(EventCenter eventCenter) {
        mContentFragment.onEventComing(eventCenter);
    }

    @Override
    public void hideException() {
        mException.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showException() {
        mException.setVisibility(View.VISIBLE);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
