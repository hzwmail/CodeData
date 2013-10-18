package com.example.SearchNearBy.View;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.TextView;
import com.example.SearchNearBy.R;

/**
 * Created with IntelliJ IDEA.
 * User: Admin
 * Date: 13-10-18
 * Time: 上午8:46
 * To change this template use File | Settings | File Templates.
 */
public class MessageActivity extends Activity {
    private ImageButton informationBack;
    private TextView information;
    private TextView chName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.positioninformation);
        chName = (TextView) findViewById(R.id.chName);
        information = (TextView) findViewById(R.id.information);
        informationBack = (ImageButton) findViewById(R.id.informationBack);
        informationBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
//        information.setText(chName.getText().toString());
    }
}
