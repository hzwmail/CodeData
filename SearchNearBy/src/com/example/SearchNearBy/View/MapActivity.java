package com.example.SearchNearBy.View;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import android.widget.TextView;
import android.widget.Toast;
import com.example.SearchNearBy.R;

import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.MKGeneralListener;
import com.baidu.mapapi.map.*;
import com.baidu.platform.comapi.basestruct.GeoPoint;


/**
 * Created with IntelliJ IDEA.
 * User: cdm
 * Date: 13-10-11
 * Time: AM10:08
 * To change this template use File | Settings | File Templates.
 */
public class MapActivity extends Activity {
    private BMapManager bMapManager;
    public static final String strKey = "4cba983b01d546d3b069877aa0b80ef7";
    private MapView mapView;
    private MapController mapController;
    private LayoutInflater layoutInflater;
    private View mapPopWindow;
    private PoiOverlay<OverlayItem> itemItemizedOverlay;
    private MyLocationOverlay myLocationOverlay;
    private  double X =34.25934463685013 * 1E6;
    private  double Y = 108.94721031188965* 1E6;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);    //To change body of overridden methods use File | Settings | File Templates.


        //初始化BaiduMapManager
        bMapManager = new BMapManager(this);
        boolean initResult = bMapManager.init(strKey, new MKGeneralListener() {
            @Override
            public void onGetNetworkState(int iError) {
                if (iError == MKEvent.ERROR_NETWORK_CONNECT) {
                    Toast.makeText(MapActivity.this, "您的网络出错啦！", Toast.LENGTH_LONG).show();
                } else if (iError == MKEvent.ERROR_NETWORK_DATA) {
                    Toast.makeText(MapActivity.this, "输入正确的检索条件！", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onGetPermissionState(int iError) {
                if (iError == MKEvent.ERROR_PERMISSION_DENIED) {
                    Toast.makeText(MapActivity.this, "请在 DemoApplication.java文件输入正确的授权Key！", Toast.LENGTH_LONG).show();
                }
            }
        });

        if (!initResult) {
            Toast.makeText(MapApplication.getInstance().getApplicationContext(), "BMapManager  初始化错误!", Toast.LENGTH_LONG).show();
        }

        setContentView(R.layout.baidumap);

        layoutInflater = LayoutInflater.from(this);

        mapView = (MapView) findViewById(R.id.bmapView);
         //放大缩小按钮
        mapView.setBuiltInZoomControls(true);
        //卫星图层
        mapView.setSatellite(true);
        //交通图层
        mapView.setTraffic(true);


        mapController = mapView.getController();
        //控制缩放等级
        mapController.setZoom(14);

        //移动到经纬度点
        final GeoPoint geoPoint = new GeoPoint((int)X,(int)(Y));
        //设置中心点
        mapController.setCenter(geoPoint);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mapController.animateTo(geoPoint);
            }
        }, 5000);


        Drawable marker = getResources().getDrawable(R.drawable.icon_marka);


        itemItemizedOverlay = new PoiOverlay<OverlayItem>(marker, mapView);


        for (int i = 0; i < 10; i++) {
            GeoPoint point = new GeoPoint(geoPoint.getLatitudeE6() + i * 10000, geoPoint.getLongitudeE6() + i * 10000);
            OverlayItem overlayItem = new OverlayItem(point, "我是标题 " + i, "我是内容 " + i);
            itemItemizedOverlay.addItem(overlayItem);
        }

        mapView.getOverlays().add(itemItemizedOverlay);

        //添加弹出窗口
        mapPopWindow = layoutInflater.inflate(R.layout.map_pop_window, null);
        mapPopWindow.setVisibility(View.GONE);
        mapView.addView(mapPopWindow);


        myLocationOverlay = new MyLocationOverlay(getResources().getDrawable(R.drawable.icon_marka), mapView);
        mapView.getOverlays().add(myLocationOverlay);

    }

    class PoiOverlay<OverlayItem> extends ItemizedOverlay {

        public PoiOverlay(Drawable drawable, MapView mapView) {
            super(drawable, mapView);
        }

        @Override
        protected boolean onTap(int i) {
            Log.d("BaiduMapDemo", "onTap " + i);
            com.baidu.mapapi.map.OverlayItem item = itemItemizedOverlay.getItem(i);
            GeoPoint point = item.getPoint();
            String title = item.getTitle();
            String content = item.getSnippet();

            TextView titleTextView = (TextView) mapPopWindow.findViewById(R.id.titleTextView);
            TextView contentTextView = (TextView) mapPopWindow.findViewById(R.id.contentTextView);
            titleTextView.setText(title);
            contentTextView.setText(content);
            contentTextView.setVisibility(View.VISIBLE);

            MapView.LayoutParams layoutParam = new MapView.LayoutParams(
                    //控件宽,继承自ViewGroup.LayoutParams
                    MapView.LayoutParams.WRAP_CONTENT,
                    //控件高,继承自ViewGroup.LayoutParams
                    MapView.LayoutParams.WRAP_CONTENT,
                    //使控件固定在某个地理位置
                    point,
                    0,
                    -40,
                    //控件对齐方式
                    MapView.LayoutParams.BOTTOM_CENTER);

            mapPopWindow.setVisibility(View.VISIBLE);

            mapPopWindow.setLayoutParams(layoutParam);

            mapController.animateTo(point);

            return super.onTap(i);
        }

        @Override
        public boolean onTap(GeoPoint geoPoint, MapView mapView) {
            Log.d("BaiduMapDemo", "onTap geoPoint " + geoPoint);

            mapPopWindow.setVisibility(View.GONE);

            return super.onTap(geoPoint, mapView);    //To change body of overridden methods use File | Settings | File Templates.
        }
    }


}
