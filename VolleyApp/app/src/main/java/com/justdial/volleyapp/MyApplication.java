package com.justdial.volleyapp;

import android.app.Application;
import android.content.Context;

/**
 * Created by justdial on 13/6/16.
 */
public class MyApplication extends Application {
    public static MyApplication appInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        appInstance = this;
    }

    public static MyApplication getAppInstance(){
        return appInstance;
    }

    public static Context getContext(){
        return appInstance.getApplicationContext();
    }
}
