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
import com.example.SearchNearBy.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Admin
 * Date: 13-10-16
 * Time: 下午1:57
 * To change this template use File | Settings | File Templates.
 */
public class FoodActivity extends Activity {
    private ListView chineseserve;
    private ArrayList<HashMap<String, ?>> data = new ArrayList<HashMap<String, ?>>();
    private AlertDialog loginDialog;
    private ImageButton chineseBack;
    private TextView threeName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);    //To change body of overridden methods use File | Settings | File Templates.
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.chineserestaurant);
        chineseserve = (ListView) findViewById(R.id.chineseserve);
        chineseBack = (ImageButton) findViewById(R.id.chineseBack);
        threeName = (TextView) findViewById(R.id.threeName);

        chineserestaures();


        chineseBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void inindata(){
        Intent intent =getIntent();
        String tittle = intent.getStringExtra("TITTLE");


        if (tittle.equals("0")) {
            String[] str=new String[]{"中餐厅","综合酒楼","四川菜（川菜）","广东菜（粤菜）","山东菜（鲁菜）","江苏菜","浙江菜","上海菜","湖南菜（湘菜）","安徽菜（徽菜）"};
            threeName.setText("中餐厅");
            for(int i= 0; i < str.length; i++){
                HashMap<String, Object> item = new HashMap<String, Object>();
                item.put("chName",str[i]);
                data.add(item);
            }

        }else if(tittle.equals("1")){
            String[] str = new String[]{"外国餐厅","西餐厅（综合风味）","日本料理","韩国料理","法式菜品餐厅","意式菜品餐厅","泰国／越南菜品餐厅","地中海风格菜品","美式风味","印度风味","英国式菜品餐厅","牛扒店(扒房)","俄国菜","葡国菜","德国菜","巴西菜","墨西哥菜","其它亚洲菜"};
            threeName.setText("外国餐厅");
            for (int i = 0; i <str.length; i++) {
                HashMap<String, Object> item = new HashMap<String, Object>();
                item.put("chName", str[i]);
                data.add(item);
            }
        }else if(tittle.equals("2")){
            String[] str = new String[]{"快餐厅","肯德基","麦当劳","必胜客","永和豆浆","茶餐厅","大家乐","大快活","美心","吉野家","吉野家"};
            threeName.setText("快餐厅");
            for (int i = 0; i < str.length; i++) {
                HashMap<String, Object> item = new HashMap<String, Object>();
                item.put("chName", str[i]);
                data.add(item);
            }
        }else if(tittle.equals("3")){
            String[] str = new String[]{"体育餐饮场所"};
            threeName.setText("体育餐饮场所");
            for (int i = 0; i < str.length; i++) {
                HashMap<String, Object> item = new HashMap<String, Object>();
                item.put("chName", str[i]);
                data.add(item);
            }
        }else if(tittle.equals("4")){
            String[] str = new String[]{"咖啡厅","星巴克咖啡","上岛咖啡","上岛咖啡","Pacific Coffee Company","巴黎咖啡店"};
            threeName.setText("咖啡厅");
            for (int i = 0; i < str.length; i++) {
                HashMap<String, Object> item = new HashMap<String, Object>();
                item.put("chName", str[i]);
                data.add(item);
            }
        }else if(tittle.equals("5")){
            String[] str = new String[]{"茶艺馆"};
            threeName.setText("茶艺馆");
            for (int i = 0; i < str.length; i++) {
                HashMap<String, Object> item = new HashMap<String, Object>();
                item.put("chName", str[i]);
                data.add(item);
            }
        }else if(tittle.equals("6")){
            String[] str = new String[]{"冷饮店"};
            threeName.setText("冷饮店");
            for (int i = 0; i < str.length; i++) {
                HashMap<String, Object> item = new HashMap<String, Object>();
                item.put("chName", str[i]);
                data.add(item);
            }
        }else if(tittle.equals("7")){
            String[] str = new String[]{"糕饼店"};
            threeName.setText("糕饼店");
            for (int i = 0; i < str.length; i++) {
                HashMap<String, Object> item = new HashMap<String, Object>();
                item.put("chName", str[i]);
                data.add(item);
            }
        }else if(tittle.equals("8")){
            String[] str = new String[]{"甜品店"};
            threeName.setText("甜品店");
            for (int i = 0; i < str.length; i++) {
                HashMap<String, Object> item = new HashMap<String, Object>();
                item.put("chName", str[i]);
                data.add(item);
            }
        }
    }
    private void chineserestaures(){
        inindata();
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
                    convertView = layoutInflater.inflate(R.layout.zhongcan, parent, false);
                }
                Map<String, Object> itemData = (Map<String, Object>) data.get(position);
                TextView chName = (TextView) convertView.findViewById(R.id.chName);
                chName.setText(itemData.get("chName").toString());
                return convertView;
            }
        };

        chineseserve.setAdapter(baseAdapter);
        chineseserve.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                     Intent intent = new Intent();
                        intent.setClass(FoodActivity.this, MessageActivity.class);

                        startActivity(intent);



                }


        });
    }
}
