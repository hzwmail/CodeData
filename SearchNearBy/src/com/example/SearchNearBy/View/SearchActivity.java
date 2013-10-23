package com.example.SearchNearBy.View;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import com.example.SearchNearBy.R;

/**
 * Created with IntelliJ IDEA.
 * User: Admin
 * Date: 13-10-16
 * Time: 下午4:04
 * To change this template use File | Settings | File Templates.
 */
public class SearchActivity extends Activity {
    private ImageButton searchBack;
    private ImageButton search2Button;
    private AlertDialog loginDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);    //To change body of overridden methods use File | Settings | File Templates.
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.search);



        search2Button = (ImageButton) findViewById(R.id.search2Button);


        search2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    public void buttonOnClick(View v) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        LayoutInflater inflater = LayoutInflater.from(this);
        View view = inflater.inflate(R.layout.loading2, null);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        view.setLayoutParams(params);
        builder.setView(view);

        loginDialog = builder.create();
        loginDialog.show();
    }
}
