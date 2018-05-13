package com.example.a11633.vista.test;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.a11633.vista.service.DownloadService;
import com.example.a11633.vista.R;
import com.example.a11633.vista.common.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ServiceTest extends BaseActivity implements View.OnClickListener{
    @BindView(R.id.onStart)
    Button onStart;
    @BindView(R.id.onStop)
    Button onStop;
    @BindView(R.id.onBind)
    Button onBind;
    @BindView(R.id.onDisBind)
    Button onDisBind;

    private static final String TAG = "ServiceTest";
    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.d(TAG, "onServiceConnected: ");
            DownloadService.DownloadIBinder binder = (DownloadService.DownloadIBinder) iBinder;
            binder.startDownload();
            binder.getProcess();

        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Log.d(TAG, "onServiceDisconnected: ");
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_test);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        ButterKnife.bind(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this, DownloadService.class);
        switch (view.getId()){
            case R.id.onStart:
                startService(intent);
                break;

            case R.id.onStop:
                stopService(intent);
                break;

            case R.id.onBind:
                bindService(intent,connection,BIND_AUTO_CREATE);
                break;

            case R.id.onDisBind:
                unbindService(connection);
                break;
            default:break;
        }
    }
}
