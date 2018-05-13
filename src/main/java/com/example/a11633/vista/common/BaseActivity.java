package com.example.a11633.vista.common;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

/**
 * Created by 11633 on 2018/4/2.
 * 自定义的所有活动的基类和ActivityCollection联合使用
 */

abstract public class BaseActivity extends AppCompatActivity{
    private static final String TAG = "BaseActivity";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCollector.addActivity(this);
        if (getActionBar() != null) {
            getActionBar().hide();
        }
        Log.d(TAG, "onCreate: "+getClass().getSimpleName());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }
}
