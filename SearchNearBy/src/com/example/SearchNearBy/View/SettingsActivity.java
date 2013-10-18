package com.example.SearchNearBy.View;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import com.example.SearchNearBy.R;

/**
 * Created with IntelliJ IDEA.
 * User: Admin
 * Date: 13-10-16
 * Time: 下午12:21
 * To change this template use File | Settings | File Templates.
 */
public class SettingsActivity extends Activity {
    private ImageButton settingsBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);    //To change body of overridden methods use File | Settings | File Templates.
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.settings);
        settingsBack = (ImageButton) findViewById(R.id.settingsBack);

        settingsBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
