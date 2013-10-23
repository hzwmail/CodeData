package com.example.SearchNearBy.View;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.*;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.BMapManager;
import com.baidu.platform.comapi.basestruct.GeoPoint;
import com.example.SearchNearBy.Model.Loction;
import com.example.SearchNearBy.R;

import java.util.*;

public class HomePageActivity extends Activity {
    private ListView homepage;
    private ArrayList<HashMap<String, ?>> data = new ArrayList<HashMap<String, ?>>();
    private AlertDialog loginDialog;
    private ImageButton searchButton;
    private ImageButton settingButton;
    private ImageButton positionButton;
    private TextView textposition;
    private LocationClient locationClient = null;
    public static final String strKey = "FDf086c89f2517c9a9ef5c4eb53a7397";
    public static   Loction loction = new Loction();
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main);
        homepage = (ListView) findViewById(R.id.homepage);
        searchButton= (ImageButton) findViewById(R.id.searchButton);
        settingButton = (ImageButton) findViewById(R.id.settingButton);
        positionButton = (ImageButton) findViewById(R.id.positionButton);
        textposition = (TextView) findViewById(R.id.textposition);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(HomePageActivity.this, SearchFor.class);
                startActivity(intent);
            }
        });
        settingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(HomePageActivity.this, SettingsActivity.class);
                startActivity(intent);
            }
        });
        positionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HomePageActivity.this, "正在定位……", Toast.LENGTH_SHORT).show();
                getMyLocation();
                if (locationClient == null) {

                    return;
                }





            }
        });
        homePage();
    }
    private void initdata(){

        String[] str=new String[]{"餐饮服务","购物服务","生活服务","体育休闲服务","住宿服务","医疗保险服务","教科文化服务","交通设施服务","为人民服务","金融保险服务","公共设施"};
        for(int i= 0; i < str.length; i++){
            HashMap<String, Object> item = new HashMap<String, Object>();
            item.put("nameTextView",str[i]);
            data.add(item);
        }

    }
    private void homePage(){
        initdata();

        final BaseAdapter baseAdapter = new BaseAdapter() {
            @Override
            public int getCount() {
                return data.size();  //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public Object getItem(int position) {
                return data.get(position);  //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {

                if (convertView == null) {
                    LayoutInflater layoutInflater = getLayoutInflater();
                    convertView = layoutInflater.inflate(R.layout.pagehome, parent, false);
                }



                Map<String, Object> itemData = (Map<String, Object>) data.get(position);final TextView nameTextView = (TextView) convertView.findViewById(R.id.nameTextView);
                nameTextView.setText(itemData.get("nameTextView").toString());
                View  homeButton =convertView.findViewById(R.id.homeButton);

                switch (position)
                {

                    case 0:homeButton.setOnClickListener(new View.OnClickListener() {

                        public void onClick(View v) {
                            Intent intent = new Intent();
                            intent.setClass(HomePageActivity.this, ServeActivity.class);

                            intent.putExtra("TITTLE","0");
                            startActivity(intent);
                        }
                    });
                        break;
                    case 1:homeButton.setOnClickListener(new View.OnClickListener() {

                        public void onClick(View v) {
                            Intent intent = new Intent();
                            intent.setClass(HomePageActivity.this, ServeActivity.class);
                            intent.putExtra("TITTLE","1");
                            startActivity(intent);
                        }
                    });
                        break;
                    case 2:homeButton.setOnClickListener(new View.OnClickListener() {

                        public void onClick(View v) {
                            Intent intent = new Intent();
                            intent.setClass(HomePageActivity.this, ServeActivity.class);
                            intent.putExtra("TITTLE","2");
                            startActivity(intent);
                        }
                    });
                        break;
                    case 3:homeButton.setOnClickListener(new View.OnClickListener() {

                        public void onClick(View v) {
                            Intent intent = new Intent();
                            intent.setClass(HomePageActivity.this, ServeActivity.class);
                            intent.putExtra("TITTLE","3");
                            startActivity(intent);
                        }
                    });
                        break;
                    case 4:homeButton.setOnClickListener(new View.OnClickListener() {

                        public void onClick(View v) {
                            Intent intent = new Intent();
                            intent.setClass(HomePageActivity.this, ServeActivity.class);
                            intent.putExtra("TITTLE","4");
                            startActivity(intent);
                        }
                    });
                        break;
                    case 5:homeButton.setOnClickListener(new View.OnClickListener() {

                        public void onClick(View v) {
                            Intent intent = new Intent();
                            intent.setClass(HomePageActivity.this, ServeActivity.class);
                            intent.putExtra("TITTLE","5");
                            startActivity(intent);
                        }
                    });
                        break;
                    case 6:homeButton.setOnClickListener(new View.OnClickListener() {

                        public void onClick(View v) {
                            Intent intent = new Intent();
                            intent.setClass(HomePageActivity.this, ServeActivity.class);
                            intent.putExtra("TITTLE","6");
                            startActivity(intent);
                        }
                    });
                        break;
                    case 7:homeButton.setOnClickListener(new View.OnClickListener() {

                        public void onClick(View v) {
                            Intent intent = new Intent();
                            intent.setClass(HomePageActivity.this, ServeActivity.class);
                            intent.putExtra("TITTLE","7");
                            startActivity(intent);
                        }
                    });
                        break;
                    case 8:homeButton.setOnClickListener(new View.OnClickListener() {

                        public void onClick(View v) {
                            Intent intent = new Intent();
                            intent.setClass(HomePageActivity.this, ServeActivity.class);
                            intent.putExtra("TITTLE","8");
                            startActivity(intent);
                        }
                    });
                        break;
                    case 9:homeButton.setOnClickListener(new View.OnClickListener() {

                        public void onClick(View v) {
                            Intent intent = new Intent();
                            intent.setClass(HomePageActivity.this, ServeActivity.class);
                            intent.putExtra("TITTLE","9");
                            startActivity(intent);
                        }
                    });
                        break;
                    case 10:homeButton.setOnClickListener(new View.OnClickListener() {

                        public void onClick(View v) {
                            Intent intent = new Intent();
                            intent.setClass(HomePageActivity.this, ServeActivity.class);
                            intent.putExtra("TITTLE","10");
                            startActivity(intent);
                        }
                    });
                        break;
                    case 11:homeButton.setOnClickListener(new View.OnClickListener() {

                        public void onClick(View v) {
                            Intent intent = new Intent();
                            intent.setClass(HomePageActivity.this, ServeActivity.class);
                            intent.putExtra("TITTLE","11");
                            startActivity(intent);
                        }
                    });
                        break;
                }



//

                return convertView;
            }
        };
        homepage.setAdapter(baseAdapter);
    }
    private void getMyLocation() {
        Log.d("BaiduMapDemo", "getMyLocation");

        LocationClientOption option = new LocationClientOption();
        option.setCoorType("bd09ll");
        option.setAddrType("all");
        option.setOpenGps(true);
        option.setPriority(LocationClientOption.NetWorkFirst);
        option.setScanSpan(10000);
        LocationClient locationClient = new LocationClient(this);
        locationClient.setLocOption(option);

        locationClient.registerLocationListener(new BDLocationListener() {
            @Override
            public void onReceiveLocation(BDLocation bdLocation) {
//                Log.d("BaiduMapDemo", "onReceiveLocation address " + bdLocation.getAddrStr());
//                Log.d("BaiduMapDemo", "onReceiveLocation Latitude " + bdLocation.getLatitude());
//                Log.d("BaiduMapDemo", "onReceiveLocation Longitude " + bdLocation.getLongitude());

                double lng = bdLocation.getLongitude();
                double lat = bdLocation.getLatitude();

                loction.setX(lng+"");
                loction.setY(lat+"");

                StringBuffer  sb = new StringBuffer(256);

                sb.append(bdLocation.getAddrStr());
                GeoPoint point = new GeoPoint((int) (lng * 1E6), (int) (lat * 1E6));
                textposition.setText(sb.toString());
                loction.setAddress(sb.toString());


            }

            @Override
            public void onReceivePoi(BDLocation bdLocation) {
                Log.d("BaiduMapDemo", "onReceivePoi ");
            }
        });

        locationClient.start();
        locationClient.requestLocation();
    }
//    public void positionbuttonOnClick(View v) {
//
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//
//        LayoutInflater inflater = LayoutInflater.from(this);
//        View view = inflater.inflate(R.layout.loading, null);
//        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
//                LinearLayout.LayoutParams.MATCH_PARENT,
//                LinearLayout.LayoutParams.MATCH_PARENT);
//        view.setLayoutParams(params);
//        builder.setView(view);
//
//        loginDialog = builder.create();
//        loginDialog.show();
//    }
}
