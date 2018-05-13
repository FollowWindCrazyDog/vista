package com.example.a11633.vista.common;

import android.app.Application;
import android.content.Context;

import org.litepal.LitePal;

/**
 * Created by 11633 on 2018/4/2.
 */

public class MyAppliction extends Application {
    private static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        LitePal.initialize(context);
    }

    public static Context getContext(){
        return context;
    }
}
