package com.example.jly.passwordmanager.mvp.model;

/**
 * Created by jly on 16-7-20.
 */
public class Constants {
    public static String CREATE_LOCK_SUCCESS = "create_lock_success";

    public static int VIEWMODE = 0;
    public static int ADDMODE = 1;

    public static int CREATE_GESTURE = 1;
    public static int UPDATE_GESTURE = 2;

    public final static class EVEN_BUS {
        public static int CHANGE_THEME = 2;
        public static int CHANGE_PASS_WORD_SHOW = 3;
    }

    public final static class SETTING {
        public static String OPEN_GESTURE = "OPEN_GESTURE";
        public static String OPEN_PASS_WORD_SHOW = "OPEN_PASS_WORD_SHOW";
    }
}
