package com.gzd.example.frameworkapplication;

import android.app.Application;
import android.content.Context;

/**
 * Created by gzd on 2019/2/25 0025
 */
public class BaseApplication extends Application {
    public static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
    }

    public static Context getmContext() {
        return mContext;
    }
}
