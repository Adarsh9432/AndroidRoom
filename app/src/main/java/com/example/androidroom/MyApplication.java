package com.example.androidroom;

import android.app.Application;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Database.getSingleInstance(getApplicationContext());
    }
}
