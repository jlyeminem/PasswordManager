package com.example.jly.passwordmanager.mvp.presenter;

import android.content.Intent;
import android.os.Bundle;

/**
 * Created by jly on 16-7-15.
 */
public interface ActivityPresenter {
    void onCreate(Bundle savedInstanceState);

    void getIntent(Intent intent);

    void onResume();

    void onStart();

    void onPause();

    void onStop();

    void onDestroy();
}
