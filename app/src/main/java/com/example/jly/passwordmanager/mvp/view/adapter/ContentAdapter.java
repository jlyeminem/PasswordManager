package com.example.jly.passwordmanager.mvp.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jly.passwordmanager.R;
import com.example.jly.passwordmanager.mvp.model.Constants;
import com.example.jly.passwordmanager.mvp.model.bean.Password;
import com.example.jly.passwordmanager.mvp.view.activity.SettingActivity;
import com.example.jly.passwordmanager.utils.SPUtils;
import com.example.jly.passwordmanager.utils.TimeUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ContentAdapter extends RecyclerView.Adapter<ContentAdapter.ViewHolder> {

    private List<Password> mPasswords = new ArrayList<>();
    private Context mContext;
    private OnRecyclerItemClickListener mOnRecyclerItemClickListener;
    private boolean isOpen;

    public ContentAdapter(List<Password> passwords, Context context) {
        mPasswords = passwords;
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.password_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final Password password = mPasswords.get(position);
        if (password != null) {
            holder.mItemDate.setText(TimeUtils.getConciseTime(password.getTime(),mContext));
            holder.mItemTitle.setText(password.getTitle());
            holder.mItemName.setText(password.getUserName());
            if(isOpen) {
                holder.mItemPassword.setText(password.getPassword());
            } else {
                holder.mItemPassword.setText("********");
            }
            if(password.getPwInfo() != null && !password.getPwInfo().equals("")) {
                holder.mItemNoteContainer.setVisibility(View.VISIBLE);
                holder.mMemoInfo.setText(password.getPwInfo());
            }
            holder.mCardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnRecyclerItemClickListener.onRecycleItemClick(view,position);
                }
            });
        }
    }

    public void addAll(List<Password> passwords) {
        if(null != mPasswords) {
            mPasswords.clear();
            mPasswords.addAll(passwords);
        } else {
            mPasswords = new ArrayList<>();
            mPasswords.addAll(passwords);
        }
    }

    public void clearData() {
        if (null != mPasswords)
            mPasswords.clear();
    }

    public interface OnRecyclerItemClickListener {
        void onRecycleItemClick(View view,int position);
    }

    public void setOnRecyclerItemClickListener(OnRecyclerItemClickListener onRecyclerItemClickListener) {
        mOnRecyclerItemClickListener = onRecyclerItemClickListener;
    }

    @Override
    public int getItemCount() {
        isOpen = (boolean) SPUtils.get(mContext, Constants.SETTING.OPEN_PASS_WORD_SHOW, true);
        return mPasswords != null ? mPasswords.size() : 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_date)
        TextView mItemDate;
        @BindView(R.id.imageType)
        ImageView mImageType;
        @BindView(R.id.item_title)
        TextView mItemTitle;
        @BindView(R.id.main_item_top)
        ImageView mMainItemTop;
        @BindView(R.id.item_name)
        TextView mItemName;
        @BindView(R.id.item_password)
        TextView mItemPassword;
        @BindView(R.id.memoInfo)
        TextView mMemoInfo;
        @BindView(R.id.item_note_container)
        FrameLayout mItemNoteContainer;
        @BindView(R.id.cardView)
        CardView mCardView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
