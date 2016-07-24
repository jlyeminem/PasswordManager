package com.example.jly.passwordmanager.mvp.model.bean;

/**
 * Created by jly on 16-7-24.
 */
public class Password {
    private int pwType;
    private String title;
    private String userName;
    private String password;
    private long time;
    private String pwInfo;

    public Password() {
    }

    public Password(int pwType, String title, String userName, String password, long time, String pwInfo) {
        this.pwType = pwType;
        this.title = title;
        this.userName = userName;
        this.password = password;
        this.time = time;
        this.pwInfo = pwInfo;
    }

    public int getPwType() {
        return pwType;
    }

    public void setPwType(int pwType) {
        this.pwType = pwType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getPwInfo() {
        return pwInfo;
    }

    public void setPwInfo(String pwInfo) {
        this.pwInfo = pwInfo;
    }
}