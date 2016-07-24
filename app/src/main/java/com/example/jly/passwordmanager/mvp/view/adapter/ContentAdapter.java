package com.example.jly.passwordmanager.mvp.view.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jly.passwordmanager.R;
import com.example.jly.passwordmanager.mvp.model.bean.Password;
import com.example.jly.passwordmanager.utils.TimeUtils;

import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ContentAdapter extends RecyclerView.Adapter<ContentAdapter.ViewHolder> {

    private List<Password> mPasswords;
    private Context mContext;

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
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Password password = mPasswords.get(position);
        if (password != null) {
            holder.mItemDate.setText(TimeUtils.getConciseTime(password.getTime(),mContext));
            holder.mItemTitle.setText(password.getTitle());
            holder.mItemName.setText(password.getUserName());
            holder.mItemPassword.setText(password.getPassword());
            if(password.getPwInfo() != null && !password.getPwInfo().equals("")) {
                holder.mItemNoteContainer.setVisibility(View.VISIBLE);
                holder.mMemoInfo.setText(password.getPwInfo());
            }
        }
    }

    @Override
    public int getItemCount() {
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