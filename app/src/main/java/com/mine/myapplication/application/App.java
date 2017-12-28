package com.mine.myapplication.application;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.mine.myapplication.utils.Utils;
import com.tencent.bugly.Bugly;
import com.tencent.bugly.beta.Beta;
import com.tencent.bugly.crashreport.CrashReport;


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
//        CrashReport.initCrashReport(getApplicationContext(), "4b127837a4", false);
        Bugly.init(getApplicationContext(), "4b127837a4", false);
    }
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        // you must install multiDex whatever tinker is installed!
        MultiDex.install(base);


        // 安装tinker
        Beta.installTinker();
    }
}
