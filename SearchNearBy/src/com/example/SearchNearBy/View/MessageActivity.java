package com.example.SearchNearBy.View;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.*;
import com.example.SearchNearBy.Model.Loction;
import com.example.SearchNearBy.R;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Admin
 * Date: 13-10-18
 * Time: 上午8:46
 * To change this template use File | Settings | File Templates.
 */
public class MessageActivity extends Activity {
    private  String poiHttp = "https://api.weibo.com/2/location/pois/search/by_geo.json";

    private static final String TAG = "SearchNearBy";
    private ImageButton informationBack;
    private TextView information;
    private String[] objects = new String[]{"范围:1000M内", "范围:2000M内", "范围:3000M内"};
    private Spinner range;
    private static final int SUCCESS = 0;
    private static final int ERROR_SERVER = 1;
    private static final int ERROR_DATA_FORMAT = 2;
    private ArrayList<HashMap<String, ?>> data = new ArrayList<HashMap<String, ?>>();
    private  String X = String.valueOf(108.908035);
    private  String Y = String.valueOf(34.238404);

//    private  String X = HomePageActivity.loction.getX();
//    private  String Y =HomePageActivity.loction.getY();
    private ListView positionmessage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.positioninformation);
        positionmessage = (ListView) findViewById(R.id.positionmessage);
        information = (TextView) findViewById(R.id.information);
        informationBack = (ImageButton) findViewById(R.id.informationBack);




        informationBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Intent intent=getIntent();
        String tittle3 =intent.getStringExtra("chName");
        information.setText(tittle3);
        range = (Spinner) findViewById(R.id.range);


        range.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
//                Object item = parent.getAdapter().getItem(position);
                String item = objects[position];

                setTitle( item.toString());

                Log.d("Spinner", "onItemSelected");
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                Log.d("Spinner", "onNothingSelected");

            }
        });
        loading();
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item, objects);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        range.setAdapter(adapter);

    }


    private void poilist(){


        BaseAdapter baseAdapter = new BaseAdapter() {

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
                    convertView = layoutInflater.inflate(R.layout.messageposition, parent, false);
                }
                Map<String, Object> itemData = (Map<String, Object>) data.get(position);
                Log.d(TAG,"jo1"+ itemData);
                TextView indetail = (TextView) convertView.findViewById(R.id.indetail);
                indetail.setText(itemData.get("name").toString());
                TextView message = (TextView) convertView.findViewById(R.id.message);
                message.setText(itemData.get("address").toString());
                TextView distance = (TextView)convertView.findViewById(R.id.distance);
                distance.setText(itemData.get("distance").toString());

                return convertView;
            }
        };

        positionmessage.setAdapter(baseAdapter);
        positionmessage.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Map<String, Object> itemData1 = (Map<String, Object>) data.get(position);
                Intent intent = new Intent();

                intent.setClass(MessageActivity.this, MapActivity.class);
                intent.putExtra("address",itemData1.get("address").toString());
                intent.putExtra("x",itemData1.get("x").toString());
                intent.putExtra("y",itemData1.get("y").toString());
                startActivity(intent);
            }
        });

    }


    private void loading() {
        Intent intent =getIntent();
        String str= intent.getStringExtra("chName");
        final String name = str.trim() ;
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("加载中...");

        AsyncTask<Integer, Integer, Integer> task = new AsyncTask<Integer, Integer, Integer>() {

            @Override
            protected Integer doInBackground(Integer... params) {


                try {

                    String url = poiHttp + "?coordinate=" + X+","+Y + "&q=" + name +"&count=20"+ "&access_token=2.00_HPptBIIxwdDe6f284b2560IPcOG";

                    Log.d(TAG, "Request server data " + url);

                    String result = requestServerData(url);
                    Log.d(TAG, "Request server data |" + result +"|");
                    JSONObject jsonObject = new JSONObject(result);
                    Log.d("BaiduMapDemo", "DATA " + X+","+Y);
                    Log.d("BaiduMapDemo", "DATA " + name);

                    JSONArray ja = jsonObject.getJSONArray("poilist");
                    Log.d(TAG,"jo"+ ja);
                    for (int i=0;i<ja.length();i++){
                        HashMap<String, Object> item = new HashMap<String, Object>();
                        JSONObject  jo=  ja.optJSONObject(i);

                        item.put("address", jo.optString("address"));
                        item.put("name", jo.optString("name"));
                        item.put("distance", jo.optString("distance")+"m");
                        item.put("x",jo.opt("x"));
                        item.put("y",jo.opt("y"));
                        data.add(item);

                    }


                }

                catch(
                        IOException e
                        )

                {
                    Log.e(TAG, "Request server data error", e);

                    return ERROR_SERVER;
                }

                catch(
                        JSONException e
                        )

                {
                    Log.e(TAG, "Format data error", e);

                    return ERROR_DATA_FORMAT;
                }

                return SUCCESS;
            }

            protected void onPreExecute() {


                progressDialog.show();
            }

            protected void onPostExecute(Integer result) {

                progressDialog.dismiss();
                if (result == SUCCESS) {
                    //跳转到展示界面
                   poilist();

                } else if (result == ERROR_SERVER) {
                    showServerErrorMessage();
                } else if (result == ERROR_DATA_FORMAT) {
                    showDataErrorMessage();
                }

            }
        };
        task.execute(0);

    }
    private void showServerErrorMessage() {
        Toast.makeText(this, "请求数据失败", Toast.LENGTH_LONG).show();
    }

    private void showDataErrorMessage() {
        Toast.makeText(this, "数据格式错误", Toast.LENGTH_LONG).show();
    }

    private String requestServerData(String url) throws IOException {
        //请求服务器
        HttpGet request = new HttpGet(url);
        DefaultHttpClient httpClient = new DefaultHttpClient();

        HttpResponse response = httpClient.execute(request);
        String resultStr = EntityUtils.toString(response.getEntity(), "UTF-8");

        return resultStr;
    }


}
