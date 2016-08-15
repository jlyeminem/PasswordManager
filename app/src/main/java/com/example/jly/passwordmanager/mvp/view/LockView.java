package com.example.jly.passwordmanager.mvp.view;

/**
 * 有关与锁屏界面的接口
 * Created by jly on 16-6-11.
 */
public interface LockView {
    void initLockPatternView();

    void lockErrorShow();

    void clearPattern();
}
