package com.example.yhz.multipleglidview;

import android.app.Application;

public class YHZApplication extends Application {

    public static Application mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }
}
