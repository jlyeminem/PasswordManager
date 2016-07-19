package com.example.jly.passwordmanager.mvp.view;

/**
 * Created by jly on 16-7-19.
 */
public interface CreateLockView {
    void initLockPatternView();

    void lockErrorShow();

    void setResults(int isSuccess);

    void clearPattern();
}
