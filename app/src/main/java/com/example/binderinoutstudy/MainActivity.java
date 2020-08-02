package com.example.binderinoutstudy;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.zy.ipc.AidlBean;
import com.zy.ipc.IBinderTestService;
import com.zy.ipc.utils.Constant;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = Constant.PRE_TAG + "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    IBinderTestService iBinderTestService = null;
    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            iBinderTestService = IBinderTestService.Stub.asInterface(service);
            Log.d(TAG, "onServiceConnected:" + iBinderTestService);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            // TODO: 2020/7/31 服务端断开 UI线程中回调
        }
    };

    public void bind(View view) {
        Log.d(TAG, "bind");

        Intent intent = new Intent(Constant.APK_TEST_ACTION);
        //设置服务端的包名
        //Caused by: java.lang.IllegalArgumentException: Service Intent must be explicit: Intent { act=com.test.zy.APK_INSTALL_ACTION }
        intent.setPackage("com.zy.binderservice");
        bindService(intent, serviceConnection, BIND_AUTO_CREATE);
    }

    public void unbind(View view) {
        Log.d(TAG, "unbind");
        unbindService(serviceConnection);
    }


    AidlBean outBean = new AidlBean(1000, "client outTest");

    public void outTest(View view) {
        try {
            Log.d(TAG, "outTest start:" + outBean);
            iBinderTestService.OutTest(outBean);
            Log.d(TAG, "outTest end:" + outBean);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void outTestShow(View view) {
        try {
            //修改传入的参数
            outBean.setName("client outTest Show");
            Log.d(TAG, "outTestShow start:" + outBean);
            iBinderTestService.OutTestShow();
            Log.d(TAG, "outTestShow end:" + outBean);

        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }


    AidlBean inoutBean = new AidlBean(1000, "client inoutTest");
    public void inoutTest(View view) {
        try {
            Log.d(TAG, "inoutTest start:" + inoutBean);
            iBinderTestService.InOutTest(inoutBean);
            Log.d(TAG, "inoutTest end:" + inoutBean);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void inoutTestShow(View view) {
        try {
            //修改传入的参数
            inoutBean.setName("client inoutTest Show");
            Log.d(TAG, "inoutTestShow start:" + inoutBean);
            iBinderTestService.InOutTestShow();
            Log.d(TAG, "inoutTestShow end:" + inoutBean);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    AidlBean inBean = new AidlBean(1000, "client inTest");
    public void inTest(View view) {
        try {
            Log.d(TAG, "inTest start:" + inBean);
            iBinderTestService.InTest(inBean);
            Log.d(TAG, "inTest end:" + inBean);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void inTestShow(View view) {
        try {
            //修改传入的参数
            inBean.setName("client inTest Show");
            Log.d(TAG, "inTestShow start:" + inBean);
            iBinderTestService.InTestShow();
            Log.d(TAG, "inTestShow end:" + inBean);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}