package com.example.SearchNearBy.View;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;
import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.MKGeneralListener;
import com.baidu.mapapi.map.MKEvent;

public class MapApplication extends Application {
    private static MapApplication mInstance = null;

    public boolean m_bKeyRight = true;
    BMapManager mBMapManager = null;
    public static final String strKey = "4cba983b01d546d3b069877aa0b80ef7";

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        initEngineManager(this);
    }

    public void initEngineManager(Context context) {
        if (mBMapManager == null) {
            mBMapManager = new BMapManager(context);
        }

        if (!mBMapManager.init(strKey, new MyGeneralListener())) {
            Toast.makeText(MapApplication.getInstance().getApplicationContext(), "BMapManager  初始化错误!", Toast.LENGTH_LONG).show();
        }
    }

    public static MapApplication getInstance() {
        return mInstance;
    }

    static class MyGeneralListener implements MKGeneralListener {
        @Override     //获取网络状态
        public void onGetNetworkState(int iError) {
            if (iError == MKEvent.ERROR_NETWORK_CONNECT) {
                Toast.makeText(MapApplication.getInstance().getApplicationContext(), "您的网络出错啦！", Toast.LENGTH_LONG).show();
            } else if (iError == MKEvent.ERROR_NETWORK_DATA) {
                Toast.makeText(MapApplication.getInstance().getApplicationContext(), "输入正确的检索条件！", Toast.LENGTH_LONG).show();
            }
        }

        @Override   //设置权限
        public void onGetPermissionState(int iError) {
            if (iError == MKEvent.ERROR_PERMISSION_DENIED) {
                Toast.makeText(MapApplication.getInstance().getApplicationContext(), "请在 DemoApplication.java文件输入正确的授权Key！", Toast.LENGTH_LONG).show();
                MapApplication.getInstance().m_bKeyRight = false;
            }
        }
    }
}