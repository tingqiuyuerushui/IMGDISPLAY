package com.mine.myapplication.application;

import android.app.Application;
import android.content.Context;

import com.mine.myapplication.utils.Utils;


public class App extends Application {
    private static App app;
    public static Context getAppContext() {
        return app;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        Utils.init(this);
        app=this;
    }
}
