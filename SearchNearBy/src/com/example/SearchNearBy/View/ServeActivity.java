package com.example.SearchNearBy.View;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import com.example.SearchNearBy.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Admin
 * Date: 13-10-16
 * Time: 上午11:22
 * To change this template use File | Settings | File Templates.
 */
public class ServeActivity extends Activity {
    private ListView cateringserve;
    private ArrayList<HashMap<String, ?>> data = new ArrayList<HashMap<String, ?>>();
    private AlertDialog loginDialog;
    private ImageButton cateringBack;
    private TextView onetitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);    //To change body of overridden methods use File | Settings | File Templates.
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.catering);
        cateringserve = (ListView) findViewById(R.id.cateringserve);
        cateringBack = (ImageButton) findViewById(R.id.cateringBack);
        onetitle = (TextView) findViewById(R.id.onetitle);

        careating();
        cateringBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initdata(){
        Intent intent =getIntent();
        String tittle = intent.getStringExtra("TITTLE");

        if (tittle.equals("0")) {
            String[] str = new String[]{"中餐厅", "外国餐厅", "快餐厅", "休闲餐饮场所", "咖啡厅", "茶艺馆", "冷饮店", "糕饼店", "甜品店"};
            onetitle.setText("餐饮服务");
            for (int i = 0; i < str.length; i++) {
                HashMap<String, Object> item = new HashMap<String, Object>();

                item.put("catertingName", str[i]);
                data.add(item);
            }

        }else if(tittle.equals("1")){
            String[] str = new String[]{"商场","便利店","家电电子卖场","超级市场","花鸟鱼虫市场","家居建材市场","综合市场","文化用品店","体育用品店"};
            onetitle.setText("购物服务");
            for (int i = 0; i < str.length; i++) {
                HashMap<String, Object> item = new HashMap<String, Object>();
                item.put("catertingName", str[i]);
                data.add(item);
            }
        }else if(tittle.equals("2")){
            String[] str = new String[]{"旅行社","信息咨询中心","售票处","邮局","物流速递","电讯营业厅","事务所","人才市场","自来水营业厅"};
            onetitle.setText("生活服务");
            for (int i = 0; i < str.length; i++) {
                HashMap<String, Object> item = new HashMap<String, Object>();
                item.put("catertingName", str[i]);
                data.add(item);
            }
        }else if(tittle.equals("3")){
            String[] str = new String[]{"体育休闲服务场所","运动场馆","高尔夫相关","娱乐场所","度假疗养场所","休闲场所","影剧院"};
            onetitle.setText("体育休闲服务");
            for (int i = 0; i < str.length; i++) {
                HashMap<String, Object> item = new HashMap<String, Object>();
                item.put("catertingName", str[i]);
                data.add(item);
            }
        }else if(tittle.equals("4")){
            String[] str = new String[]{"住宿服务相关","宾馆酒店","旅馆招待所"};
            onetitle.setText("住宿服务");
            for (int i = 0; i < str.length; i++) {
                HashMap<String, Object> item = new HashMap<String, Object>();
                item.put("catertingName", str[i]);
                data.add(item);
            }
        }else if(tittle.equals("5")){
            String[] str = new String[]{"医疗保健服务场所","综合医院","专科医院","诊所","急救中心","疾病预防机构","医药保健相关","动物医疗场所"};
            onetitle.setText("医疗保健服务");
            for (int i = 0; i < str.length; i++) {
                HashMap<String, Object> item = new HashMap<String, Object>();
                item.put("catertingName", str[i]);
                data.add(item);
            }
        }else if(tittle.equals("6")){
            String[] str = new String[]{"科教文化场所","博物馆","展览馆","会展中心","美术馆","图书馆","科技馆","天文馆","档案馆","文艺团体","传媒机构","学校","科研机构","培训机构","驾校"};
            onetitle.setText("教科文化服务");
            for (int i = 0; i < str.length; i++) {
                HashMap<String, Object> item = new HashMap<String, Object>();
                item.put("catertingName", str[i]);
                data.add(item);
            }
        }else if(tittle.equals("7")){
            String[] str = new String[]{"交通服务相关","飞机场","火车站","港口码头","长途汽车站","地铁站","轻轨站","公交车站","班车站","停车场","过境口岸"};
            onetitle.setText("交通服务");
            for (int i = 0; i < str.length; i++) {
                HashMap<String, Object> item = new HashMap<String, Object>();
                item.put("catertingName", str[i]);
                data.add(item);
            }
        }else if(tittle.equals("8")){
            String[] str = new String[]{"为人民服务相关"};

            onetitle.setText("为人民服务");
            for (int i = 0; i < str.length; i++) {
                HashMap<String, Object> item = new HashMap<String, Object>();
                item.put("catertingName", str[i]);
                data.add(item);
            }
        }else if(tittle.equals("9")){
            String[] str = new String[]{"金融保险服务机构","银行","银行相关","自动提款机","保险公司","证券公司","财务公司"};
            onetitle.setText("金融保险服务");
            for (int i = 0; i < str.length; i++) {
                HashMap<String, Object> item = new HashMap<String, Object>();
                item.put("catertingName", str[i]);
                data.add(item);
            }
        }else if(tittle.equals("10")){
            String[] str = new String[]{"公共设施","报刊亭","公用电话","公共厕所","紧急避难场所"};
            onetitle.setText("公共设施");
            for (int i = 0; i < str.length; i++) {
                HashMap<String, Object> item = new HashMap<String, Object>();
                item.put("catertingName", str[i]);
                data.add(item);
            }

        }
    }
    private void careating(){
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
                    convertView = layoutInflater.inflate(R.layout.eatserve, parent, false);
                }
                Map<String, Object> itemData = (Map<String, Object>) data.get(position);
                TextView catertingName = (TextView) convertView.findViewById(R.id.catertingName);
                catertingName.setText(itemData.get("catertingName").toString());
                View CatertingButton = convertView.findViewById(R.id.CatertingButton);

                switch (position)
                {

                    case 0: CatertingButton.setOnClickListener(new View.OnClickListener() {

                        public void onClick(View v) {
                            Intent intent = new Intent();
                            intent.setClass(ServeActivity.this, FoodActivity.class);
                            intent.putExtra("TITTLE","0");

                            startActivity(intent);
                        }
                    });
                        break;
                    case 1: CatertingButton.setOnClickListener(new View.OnClickListener() {

                        public void onClick(View v) {
                            Intent intent = new Intent();
                            intent.setClass(ServeActivity.this, FoodActivity.class);
                            intent.putExtra("TITTLE","1");

                            startActivity(intent);
                        }
                    });
                        break;
                    case 2:CatertingButton.setOnClickListener(new View.OnClickListener() {

                        public void onClick(View v) {
                            Intent intent = new Intent();
                            intent.setClass(ServeActivity.this, FoodActivity.class);
                            intent.putExtra("TITTLE","2");

                            startActivity(intent);
                        }
                    });
                        break;
                    case 3:CatertingButton.setOnClickListener(new View.OnClickListener() {

                        public void onClick(View v) {
                            Intent intent = new Intent();
                            intent.setClass(ServeActivity.this, FoodActivity.class);
                            intent.putExtra("TITTLE","3");

                            startActivity(intent);
                        }
                    });
                        break;
                    case 4:CatertingButton.setOnClickListener(new View.OnClickListener() {

                        public void onClick(View v) {
                            Intent intent = new Intent();
                            intent.setClass(ServeActivity.this, FoodActivity.class);
                            intent.putExtra("TITTLE","4");

                            startActivity(intent);
                        }
                    });
                        break;
                    case 5:CatertingButton.setOnClickListener(new View.OnClickListener() {

                        public void onClick(View v) {
                            Intent intent = new Intent();
                            intent.setClass(ServeActivity.this, FoodActivity.class);
                            intent.putExtra("TITTLE","5");
                            startActivity(intent);
                        }
                    });
                        break;
                    case 6:CatertingButton.setOnClickListener(new View.OnClickListener() {

                        public void onClick(View v) {
                            Intent intent = new Intent();
                            intent.setClass(ServeActivity.this, FoodActivity.class);
                            intent.putExtra("TITTLE","6");
                            startActivity(intent);
                        }
                    });
                        break;
                    case 7:CatertingButton.setOnClickListener(new View.OnClickListener() {

                        public void onClick(View v) {
                            Intent intent = new Intent();
                            intent.setClass(ServeActivity.this, FoodActivity.class);
                            intent.putExtra("TITTLE","7");
                            startActivity(intent);
                        }
                    });
                        break;
                    case 8:CatertingButton.setOnClickListener(new View.OnClickListener() {

                        public void onClick(View v) {
                            Intent intent = new Intent();
                            intent.setClass(ServeActivity.this, FoodActivity.class);
                            intent.putExtra("TITTLE","8");
                            startActivity(intent);
                        }
                    });
                        break;
                    case 9:CatertingButton.setOnClickListener(new View.OnClickListener() {

                        public void onClick(View v) {
                            Intent intent = new Intent();
                            intent.setClass(ServeActivity.this, FoodActivity.class);
                            intent.putExtra("TITTLE","9");
                            startActivity(intent);
                        }
                    });
                        break;
                    case 10:CatertingButton.setOnClickListener(new View.OnClickListener() {

                        public void onClick(View v) {
                            Intent intent = new Intent();
                            intent.setClass(ServeActivity.this, FoodActivity.class);
                            intent.putExtra("TITTLE","10");
                            startActivity(intent);
                        }
                    });
                        break;
                    case 11:CatertingButton.setOnClickListener(new View.OnClickListener() {

                        public void onClick(View v) {
                            Intent intent = new Intent();
                            intent.setClass(ServeActivity.this, FoodActivity.class);
                            intent.putExtra("TITTLE","11");
                            startActivity(intent);
                        }
                    });
                        break;
                }
                return convertView;
            }
        };

        cateringserve.setAdapter(baseAdapter);
    }
}
