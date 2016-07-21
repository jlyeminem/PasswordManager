package com.example.jly.passwordmanager.mvp.model.bean;


public class LockWarnBean {
    private String mLockWarn;
    private int mColor;

    public LockWarnBean(String lockWarn) {
        mLockWarn = lockWarn;
    }

    public LockWarnBean(String lockWarn, int color) {
        mLockWarn = lockWarn;
        mColor = color;
    }

    public String getLockWarn() {
        return mLockWarn;
    }

    public void setLockWarn(String lockWarn) {
        mLockWarn = lockWarn;
    }

    public int getColor() {
        return mColor;
    }

    public void setColor(int color) {
        mColor = color;
    }
}
