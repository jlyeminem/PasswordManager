package com.example.jly.passwordmanager.mvp.view.fragment;

import android.app.Activity;
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
import com.example.jly.passwordmanager.mvp.presenter.impl.ContentFragmentImpl;
import com.example.jly.passwordmanager.mvp.view.ContentView;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ContentFragment extends Fragment implements ContentView{

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.exception)
    LinearLayout mException;

    private Activity mActivity;
    private ContentFragmentImpl mContentFragment;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_content, container, false);
        ButterKnife.bind(this, view);
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

    }

    @Override
    public void hideException() {
        mException.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showException() {
        mException.setVisibility(View.VISIBLE);
    }
}
