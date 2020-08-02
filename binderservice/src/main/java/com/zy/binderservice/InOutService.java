package com.zy.binderservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import android.util.Log;

import com.zy.ipc.AidlBean;
import com.zy.ipc.IBinderTestService;
import com.zy.ipc.utils.Constant;

public class InOutService extends Service {

    private static final String TAG = Constant.PRE_TAG + "InOutService";

    public InOutService() {
    }

    private IBinderTestService.Stub stub = new IBinderTestService.Stub() {
        private AidlBean in;
        private AidlBean out;
        private AidlBean inout;

        @Override
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            //控制服务端返回到客户端数据的情况，权限不满足，返回false，数据就不会发送到客户端
            return super.onTransact(code, data, reply, flags);
        }

        @Override
        public void InTest(AidlBean bean) throws RemoteException {
            Log.d(TAG, "InTest start:" + bean);
            in = bean;
            bean.setId(1);
            bean.setName("Service InTest");
            Log.d(TAG, "InTest end:" + bean);
        }

        @Override
        public void InTestShow() throws RemoteException {
            Log.d(TAG, "InTestShow start:" + in);
            in.setName("Service InTest Show");
            Log.d(TAG, "InTestShow end:" + in);
        }

        @Override
        public void OutTest(AidlBean bean) throws RemoteException {
            Log.d(TAG, "OutTest start:" + bean);
            out = bean;
            bean.setId(2);
            bean.setName("Service OutTest");
            Log.d(TAG, "OutTest end:" + bean);
        }

        @Override
        public void OutTestShow() throws RemoteException {
            Log.d(TAG, "OutTestShow start:" + out);
            out.setName("Service OutTest Show");
            Log.d(TAG, "OutTestShow end:" + out);
        }

        @Override
        public void InOutTest(AidlBean bean) throws RemoteException {
            Log.d(TAG, "InOutTest start:" + bean);
            inout = bean;
            bean.setId(3);
            bean.setName("Service InOutTest");
            Log.d(TAG, "InOutTest end:" + bean);
        }

        @Override
        public void InOutTestShow() throws RemoteException {
            Log.d(TAG, "OutTestShow start:" + inout);
            inout.setName("Service InOutTest show");
            Log.d(TAG, "OutTestShow end:" + inout);
        }
    };


    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return stub;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand:" + Thread.currentThread());
        return super.onStartCommand(intent, flags, startId);
    }


    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
        Log.d(TAG, "onRebind:" + Thread.currentThread());
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG, "onUnbind:" + Thread.currentThread());
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy:" + Thread.currentThread());
    }
}
