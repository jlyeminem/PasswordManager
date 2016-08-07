package com.example.jly.passwordmanager.mvp.view.activity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.jly.passwordmanager.R;
import com.example.jly.passwordmanager.mvp.model.bean.Password;
import com.example.jly.passwordmanager.mvp.presenter.impl.EditActivityImpl;
import com.example.jly.passwordmanager.mvp.view.EditView;
import com.example.jly.passwordmanager.utils.spinner.NiceSpinner;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.imid.swipebacklayout.lib.SwipeBackLayout;


public class EditActivity extends BaseSwipeBackActivity implements EditView {

    private static final int SUCCESS = 1;
    public static String CREATE_MODE = "CREATE_MODE";

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.spinner)
    NiceSpinner mSpinner;
    @BindView(R.id.title_edit_text)
    MaterialEditText mTitleEditText;
    @BindView(R.id.userName)
    MaterialEditText mUserName;
    @BindView(R.id.passWord)
    MaterialEditText mPassWord;
    @BindView(R.id.eye)
    CheckBox mEye;
    @BindView(R.id.memo)
    MaterialEditText mMemo;
    @BindView(R.id.timeTextView)
    TextView mTimeTextView;
    @BindView(R.id.deleteButton)
    Button mDeleteButton;
    @BindView(R.id.view)
    LinearLayout mView;

    private EditActivityImpl mEditImpl;
    private MenuItem menuItem;
    private AlertDialog mAlertDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        initToolbar(mToolbar);
        mEditImpl = new EditActivityImpl(this,this);
        mEditImpl.onCreate(savedInstanceState);
        mEditImpl.getIntent(getIntent());
        mEye.setOnCheckedChangeListener(mEditImpl);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_edit, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        menuItem = menu.getItem(0);
        setItemMenuVisible(false);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return mEditImpl.onOptionItemSelected(item) || super.onOptionsItemSelected(item);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_edit;
    }

    @Override
    public void initSpinner(List<String> data) {
        mSpinner.attachDataSource(data);
        mSpinner.setOnItemSelectedListener(mEditImpl);
    }


    @Override
    public void initCreateModel() {
        mTitleEditText.requestFocus();
        showKeyBoard();
        addEdtChangeListener();
    }

    private void showKeyBoard() {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
    }

    @Override
    public void initEditModel() {

    }

    @Override
    public void initViewModel(Password pw, int positionType) {
        mView.setFocusable(true);
        mView.setFocusableInTouchMode(true);
        hideKeyBoard();
        mTitleEditText.setText(pw.getTitle());
        mTitleEditText.setEnabled(false);
        mUserName.setText(pw.getUserName());
        mPassWord.setText(pw.getPassword());
        mMemo.setText(pw.getPwInfo());
        mPassWord.setTransformationMethod(HideReturnsTransformationMethod
                .getInstance());
        mUserName.setOnFocusChangeListener(mEditImpl);
        mPassWord.setOnFocusChangeListener(mEditImpl);
        mMemo.setOnFocusChangeListener(mEditImpl);
        mEye.setChecked(false);
        mSpinner.setSelectedIndex(positionType);
        addEdtChangeListener();
        mDeleteButton.setVisibility(View.VISIBLE);
        mDeleteButton.setOnClickListener(mEditImpl);

    }

    private void addEdtChangeListener() {
        mTitleEditText.addTextChangedListener(mEditImpl);
        mUserName.addTextChangedListener(mEditImpl);
        mPassWord.addTextChangedListener(mEditImpl);
        mMemo.addTextChangedListener(mEditImpl);
    }

    @Override public String getTitleName() {
        return mTitleEditText.getText().toString().trim();
    }

    @Override public String getUserName() {
        return mUserName.getText().toString().trim();
    }

    @Override public String getPassWord() {
        return mPassWord.getText().toString().trim();
    }

    @Override public String getMemoInfo() {
        return mMemo.getText().toString().trim();
    }

    @Override
    public void setTime(String time) {
        mTimeTextView.setVisibility(View.VISIBLE);
        String text = "创建于："+ time;
        mTimeTextView.setText(text);
    }

    @Override
    public void showSnackToast(String msg) {

    }


    @Override
    public void setItemMenuVisible(boolean visible) {
        if (null != menuItem)
            menuItem.setVisible(visible);
    }

    @Override
    public void finishActivity() {
        setResult(SUCCESS);
        finish();
    }

    @Override
    public void setPassWordVisible(boolean visible) {
        if (visible) {
            mPassWord.setTransformationMethod(PasswordTransformationMethod
                    .getInstance());
        } else {
            mPassWord.setTransformationMethod(HideReturnsTransformationMethod
                    .getInstance());
        }
        mPassWord.setSelection(getPassWord().length());

    }

    @Override
    public void hideKeyBoard() {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(mPassWord.getWindowToken(), 0);
    }

    @Override
    public void setToolBarTitle(int resId) {
        mToolbar.setTitle(getResources().getString(resId));
    }

    @Override
    public void showDialog(String msg, String positiveMsg) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("提示");
        builder.setMessage(msg);//
        builder.setPositiveButton(positiveMsg, mEditImpl);
        builder.setNegativeButton("取消", mEditImpl);
        mAlertDialog = builder.show();
    }

    @Override
    public void hideSaveDialog() {
        if (null != mAlertDialog) {
            mAlertDialog.dismiss();
        }
    }

    @Override
    public SwipeBackLayout getSwipeBack() {
        return getSwipeBackLayout();
    }

    @Override
    public void onBackPressed() {
        mEditImpl.comeBack();
    }

    public void initToolbar(Toolbar toolbar) {
        if (toolbar == null) return;
        toolbar.setBackgroundColor(getColorPrimary());
        toolbar.setTitleTextColor(Color.WHITE);
        setToolBarTitle(R.string.add_item);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    private int getColorPrimary() {
        TypedValue typedValue = new TypedValue();
        getTheme().resolveAttribute(R.attr.colorPrimary, typedValue, true);
        return typedValue.data;
    }


}
