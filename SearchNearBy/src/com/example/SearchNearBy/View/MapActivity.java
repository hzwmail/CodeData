package com.example.SearchNearBy.View;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import android.widget.*;
import com.baidu.mapapi.search.*;
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

    public static final String strKey = "4cba983b01d546d3b069877aa0b80ef7";
    private MapView mapView;
    private MapController mapController;
    private LayoutInflater layoutInflater;
    private View mapPopWindow;
    private PoiOverlay itemItemizedOverlay,itemItemizedOverlay1;
    private MyLocationOverlay myLocationOverlay;
    private  double X =34.25934463685013 * 1E6;
    private  double Y = 108.94721031188965* 1E6;
    private ImageButton informationBack;
    private ImageButton refresh;
    int nodeIndex = -2;
    private ImageButton change;
    private Button radio1,radio2,radio3;
    private MKSearch mSearch = null;
    int searchType = -1;
    private RouteOverlay routeOverlay = null;
    private MKRoute route = null;
    private TransitOverlay transitOverlay = null;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);    //To change body of overridden methods use File | Settings | File Templates.


        //初始化BaiduMapManager
//        bMapManager = new BMapManager(this);
//        boolean initResult = bMapManager.init(strKey, new MKGeneralListener() {
//            @Override
//            public void onGetNetworkState(int iError) {
//                if (iError == MKEvent.ERROR_NETWORK_CONNECT) {
//                    Toast.makeText(MapActivity.this, "您的网络出错啦！", Toast.LENGTH_LONG).show();
//                } else if (iError == MKEvent.ERROR_NETWORK_DATA) {
//                    Toast.makeText(MapActivity.this, "输入正确的检索条件！", Toast.LENGTH_LONG).show();
//                }
//            }
//
//            @Override
//            public void onGetPermissionState(int iError) {
//                if (iError == MKEvent.ERROR_PERMISSION_DENIED) {
//                    Toast.makeText(MapActivity.this, "请在 DemoApplication.java文件输入正确的授权Key！", Toast.LENGTH_LONG).show();
//                }
//            }
//        });
//
//        if (!initResult) {
//            Toast.makeText(MapApplication.getInstance().getApplicationContext(), "BMapManager  初始化错误!", Toast.LENGTH_LONG).show();
//        }

        setContentView(R.layout.baidumap);
        radio1 = (Button) findViewById(R.id.radio1);
        radio2 = (Button) findViewById(R.id.radio2);
        radio3 = (Button) findViewById(R.id.radio3);
        informationBack = (ImageButton) findViewById(R.id.informationBack);


        change = (ImageButton) findViewById(R.id.change);

        layoutInflater = LayoutInflater.from(this);

        mapView = (MapView) findViewById(R.id.bmapView);
         //放大缩小按钮
        mapView.setBuiltInZoomControls(true);
        //卫星图层
        mapView.setSatellite(true);
        //交通图层
        mapView.setTraffic(true);
        refresh = (ImageButton) findViewById(R.id.refresh);
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mapView.refresh();
            }
        });

        mapController = mapView.getController();
        mapController.enableClick(true);
        //控制缩放等级
        mapController.setZoom(12.5f);
        Intent intent = getIntent();
        String address = intent.getStringExtra("address");
        Double nearByY = Double.valueOf(intent.getStringExtra("x"));
        Double nearByX = Double.valueOf(intent.getStringExtra("y"));
        Log.d("nearByX",nearByX+","+nearByY+"");
        Drawable markerSta = getResources().getDrawable(R.drawable.ic_loc_from);
        Drawable markerEnd = getResources().getDrawable(R.drawable.ic_loc_to);
        //移动到经纬度点
        final GeoPoint geoPoint = new GeoPoint((int)(X),(int)(Y));


        //设置中心点
        mapController.setCenter(geoPoint);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mapController.animateTo(geoPoint);
            }
        }, 5000);




        itemItemizedOverlay = new PoiOverlay(markerSta, mapView);


//        for (int i = 0; i < 10; i++) {
//            GeoPoint point = new GeoPoint(geoPoint.getLatitudeE6() + i * 10000, geoPoint.getLongitudeE6() + i * 10000);
//            OverlayItem overlayItem2 = new OverlayItem(point, "我是标题 " + i, "我是内容 " + i);
//            itemItemizedOverlay.addItem(overlayItem2);
//        }
        OverlayItem overlayItem = new OverlayItem(geoPoint,HomePageActivity.loction.getAddress(),"");
        itemItemizedOverlay.addItem(overlayItem);
        mapView.getOverlays().add(itemItemizedOverlay);
        itemItemizedOverlay1 = new PoiOverlay(markerEnd, mapView);
        GeoPoint nearGeoPoint = new GeoPoint((int)(nearByX*1e6),(int)(nearByY*1e6));
        OverlayItem overlayItem1 = new OverlayItem(nearGeoPoint,address,"");
        itemItemizedOverlay1 .addItem(overlayItem1);
        mapView.getOverlays().add(itemItemizedOverlay1);

        //添加弹出窗口
        mapPopWindow = layoutInflater.inflate(R.layout.map_pop_window, null);
        mapPopWindow.setVisibility(View.GONE);
        mapView.addView(mapPopWindow);

        mSearch = new MKSearch();
        mSearch.init(MapApplication.getInstance().mBMapManager, new MKSearchListener(){

            public void onGetDrivingRouteResult(MKDrivingRouteResult res,
                                                int error) {
                //起点或终点有歧义，需要选择具体的城市列表或地址列表
                if (error == MKEvent.ERROR_ROUTE_ADDR){
                    //遍历所有地址
//					ArrayList<MKPoiInfo> stPois = res.getAddrResult().mStartPoiList;
//					ArrayList<MKPoiInfo> enPois = res.getAddrResult().mEndPoiList;
//					ArrayList<MKCityListInfo> stCities = res.getAddrResult().mStartCityList;
//					ArrayList<MKCityListInfo> enCities = res.getAddrResult().mEndCityList;
                    return;
                }
                // 错误号可参考MKEvent中的定义
                if (error != 0 || res == null) {
                    Toast.makeText(MapActivity.this, "抱歉，未找到结果", Toast.LENGTH_SHORT).show();
                    return;
                }

                searchType = 0;
                routeOverlay = new RouteOverlay(MapActivity.this, mapView);
                // 此处仅展示一个方案作为示例
                routeOverlay.setData(res.getPlan(0).getRoute(0));
                //清除其他图层
                mapView.getOverlays().clear();
                //添加路线图层
                mapView.getOverlays().add(routeOverlay);
                //执行刷新使生效
                mapView.refresh();
                // 使用zoomToSpan()绽放地图，使路线能完全显示在地图上
                mapView.getController().zoomToSpan(routeOverlay.getLatSpanE6(), routeOverlay.getLonSpanE6());
                //移动地图到起点
                mapView.getController().animateTo(res.getStart().pt);
                //将路线数据保存给全局变量
                route = res.getPlan(0).getRoute(0);
                //重置路线节点索引，节点浏览时使用
                nodeIndex = -1;
//                mBtnPre.setVisibility(View.VISIBLE);
//                mBtnNext.setVisibility(View.VISIBLE);
            }

            public void onGetTransitRouteResult(MKTransitRouteResult res,
                                                int error) {
                //起点或终点有歧义，需要选择具体的城市列表或地址列表
                if (error == MKEvent.ERROR_ROUTE_ADDR){
                    //遍历所有地址
//					ArrayList<MKPoiInfo> stPois = res.getAddrResult().mStartPoiList;
//					ArrayList<MKPoiInfo> enPois = res.getAddrResult().mEndPoiList;
//					ArrayList<MKCityListInfo> stCities = res.getAddrResult().mStartCityList;
//					ArrayList<MKCityListInfo> enCities = res.getAddrResult().mEndCityList;
                    return;
                }
                if (error != 0 || res == null) {
                    Toast.makeText(MapActivity.this, "抱歉，未找到结果", Toast.LENGTH_SHORT).show();
                    return;
                }

                searchType = 1;
                transitOverlay = new TransitOverlay (MapActivity.this, mapView);
                // 此处仅展示一个方案作为示例
                transitOverlay.setData(res.getPlan(0));
                //清除其他图层
                mapView.getOverlays().clear();
                //添加路线图层
                mapView.getOverlays().add(transitOverlay);
                //执行刷新使生效
                mapView.refresh();
                // 使用zoomToSpan()绽放地图，使路线能完全显示在地图上
                mapView.getController().zoomToSpan(transitOverlay.getLatSpanE6(), transitOverlay.getLonSpanE6());
                //移动地图到起点
                mapView.getController().animateTo(res.getStart().pt);
                //重置路线节点索引，节点浏览时使用
                nodeIndex = 0;
//                mBtnPre.setVisibility(View.VISIBLE);
//                mBtnNext.setVisibility(View.VISIBLE);
            }

            public void onGetWalkingRouteResult(MKWalkingRouteResult res,
                                                int error) {
                //起点或终点有歧义，需要选择具体的城市列表或地址列表
                if (error == MKEvent.ERROR_ROUTE_ADDR){
                    //遍历所有地址
//					ArrayList<MKPoiInfo> stPois = res.getAddrResult().mStartPoiList;
//					ArrayList<MKPoiInfo> enPois = res.getAddrResult().mEndPoiList;
//					ArrayList<MKCityListInfo> stCities = res.getAddrResult().mStartCityList;
//					ArrayList<MKCityListInfo> enCities = res.getAddrResult().mEndCityList;
                    return;
                }
                if (error != 0 || res == null) {
                    Toast.makeText(MapActivity.this, "抱歉，未找到结果", Toast.LENGTH_SHORT).show();
                    return;
                }

                searchType = 2;
                routeOverlay = new RouteOverlay(MapActivity.this, mapView);
                // 此处仅展示一个方案作为示例
                routeOverlay.setData(res.getPlan(0).getRoute(0));
                //清除其他图层
                mapView.getOverlays().clear();
                //添加路线图层
                mapView.getOverlays().add(routeOverlay);
                //执行刷新使生效
                mapView.refresh();
                // 使用zoomToSpan()绽放地图，使路线能完全显示在地图上
                mapView.getController().zoomToSpan(routeOverlay.getLatSpanE6(), routeOverlay.getLonSpanE6());
                //移动地图到起点
                mapView.getController().animateTo(res.getStart().pt);
                //将路线数据保存给全局变量
                route = res.getPlan(0).getRoute(0);
                //重置路线节点索引，节点浏览时使用
                nodeIndex = -1;
//                mBtnPre.setVisibility(View.VISIBLE);
//                mBtnNext.setVisibility(View.VISIBLE);

            }
            public void onGetAddrResult(MKAddrInfo res, int error) {
            }
            public void onGetPoiResult(MKPoiResult res, int arg1, int arg2) {
            }
            public void onGetBusDetailResult(MKBusLineResult result, int iError) {
            }

            @Override
            public void onGetSuggestionResult(MKSuggestionResult res, int arg1) {
            }

            @Override
            public void onGetPoiDetailSearchResult(int type, int iError) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onGetShareUrlResult(MKShareUrlResult result, int type,
                                            int error) {
                // TODO Auto-generated method stub

            }
        });
        informationBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemItemizedOverlay=null;
                itemItemizedOverlay1=null;
                finish();
            }
        });


        radio2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SearchButtonProcess(v);
            }
        });
        radio1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SearchButtonProcess(v);
            }
        });
        radio3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SearchButtonProcess(v);
            }
        });
    }

    void SearchButtonProcess(View v) {
        //重置浏览节点的路线数据
        route = null;
        routeOverlay = null;
        transitOverlay = null;
//        mBtnPre.setVisibility(View.INVISIBLE);
//        mBtnNext.setVisibility(View.INVISIBLE);
        // 处理搜索按钮响应
        Intent intent = getIntent();
        Double nearByY = Double.valueOf(intent.getStringExtra("x"));
        Double nearByX = Double.valueOf(intent.getStringExtra("y"));

        // 对起点终点的name进行赋值，也可以直接对坐标赋值，   赋值坐标则将根据坐标进行搜索
        MKPlanNode stNode = new MKPlanNode();
        stNode.pt=new GeoPoint((int)(X),(int)(Y));
        MKPlanNode enNode = new MKPlanNode();
        enNode.pt =new GeoPoint((int)(nearByX*1e6),(int)(nearByY*1e6));

        // 实际使用中请对起点终点城市进行正确的设定
        if (radio1.equals(v)) {
            mSearch.drivingSearch("西安", stNode, "西安", enNode);
        } else if (radio2.equals(v)) {
            mSearch.transitSearch("西安", stNode, enNode);
        } else if (radio3.equals(v)) {
            mSearch.walkingSearch("西安", stNode, "西安", enNode);
        }
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
