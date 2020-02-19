package com.sanved.parkdemo;

import android.app.Application;

import com.google.firebase.FirebaseApp;

/**
 * Created by Sanved on 10-07-2018.
 */

public class InitApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseApp.initializeApp(this);
    }
}
