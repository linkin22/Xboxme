package com.ramogi.xboxme;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

/**
 * Created by ramogiochola on 4/28/16.
 */
public class Common extends Application {


    @Override
    public void onCreate() {
        super.onCreate();

    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
