package com.example.a11633.vista.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class DownloadService extends Service {
    private static final String TAG = "DownloadService";
    private DownloadIBinder downloadIBinder = new DownloadIBinder();

    public DownloadService() {
    }

    public class DownloadIBinder extends Binder{
        public void startDownload(){
            Log.d(TAG, "startDownload: ");
        }

        public int getProcess(){
            Log.d(TAG, "getProcess: ");
            return 0;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return downloadIBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: ");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand: ");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }
}
