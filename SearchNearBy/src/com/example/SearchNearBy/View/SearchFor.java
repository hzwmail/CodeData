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
import com.example.SearchNearBy.R;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Admin
 * Date: 13-10-22
 * Time: 下午1:27
 * To change this template use File | Settings | File Templates.
 */
public class SearchFor extends Activity {
    private static final String TAG = "SearchNearBy";
    private ListView searchfor;
    private  String poiHttp = "https://api.weibo.com/2/location/pois/search/by_geo.json";
    private ImageButton searchBack;

    private static final int SUCCESS = 0;
    private static final int ERROR_SERVER = 1;
    private static final int ERROR_DATA_FORMAT = 2;
    private ArrayList<HashMap<String, ?>> data;
    private  String X = String.valueOf(108.908035);
    private  String Y = String.valueOf(34.238404);
    private EditText searchedittext;
    private ImageButton search2Button;
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);    //To change body of overridden methods use File | Settings | File Templates.
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.search);
        searchfor= (ListView) findViewById(R.id.searchfor);
        searchedittext= (EditText) findViewById(R.id.searchedittext);
        searchBack = (ImageButton) findViewById(R.id.searchBack);

        search2Button = (ImageButton) findViewById(R.id.search2Button);
        searchBack.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                finish();
            }
        });
        search2Button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                try {
                    data = new ArrayList<HashMap<String, ?>>();
                    loading();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
            }
        });
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
                Log.d(TAG, "jo1" + itemData);
                TextView indetail = (TextView) convertView.findViewById(R.id.indetail);
                indetail.setText(itemData.get("name").toString());
                TextView message = (TextView) convertView.findViewById(R.id.message);
                message.setText(itemData.get("address").toString());
                TextView distance = (TextView)convertView.findViewById(R.id.distance);
                distance.setText(itemData.get("distance").toString());

                return convertView;
            }
        };

        searchfor.setAdapter(baseAdapter);
        baseAdapter.notifyDataSetChanged();

    }


    private void loading() throws UnsupportedEncodingException {
        name=java.net.URLEncoder.encode(searchedittext.getText().toString(),"utf-8");


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
                        JSONObject  jo=  ja.getJSONObject(i);

                        item.put("address", jo.getString("address"));
                        item.put("name", jo.getString("name"));
                        item.put("distance", jo.getString("distance")+"m");
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
