package com.example.jly.passwordmanager.mvp.model.bean;

/**
 * Created by jly on 16-7-21.
 */
public class IndexBean {

    private String toolBarTitle;

    public IndexBean(String title) {
        this.toolBarTitle = title;
    }

    public void setToolBarTitle(String title) {
        this.toolBarTitle = title;
    }

    public String getToolBarTitle() {
        return this.toolBarTitle;
    }
}
