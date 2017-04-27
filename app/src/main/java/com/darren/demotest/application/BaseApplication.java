package com.darren.demotest.application;

import android.app.Application;

import cn.sharesdk.framework.ShareSDK;

/**
 * Created by Admin on 2017/4/15.
 */

public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        ShareSDK.initSDK(this);
        super.onCreate();
    }
}
