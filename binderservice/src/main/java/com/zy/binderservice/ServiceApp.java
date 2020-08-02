package com.zy.binderservice;

import android.app.Application;
import android.util.Log;

import com.zy.ipc.utils.Constant;

public class ServiceApp extends Application {
    private static final String TAG = Constant.PRE_TAG + "ServiceApp";

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate");
    }
}
