package com.example.sunmin_project;

import android.app.Application;

public class MyApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        new httpConnection().getResponse();
    }
}
