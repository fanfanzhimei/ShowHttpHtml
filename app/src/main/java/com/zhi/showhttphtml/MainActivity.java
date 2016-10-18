package com.zhi.showhttphtml;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.zhi.service.HttpService;

public class MainActivity extends Activity {

    private static final int MESSAGE_SEND_SUCCESS = 0x1;

    private String data;

    private EditText mEtUrl;
    private Button mBtnShow;
    private TextView mTvShow;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MESSAGE_SEND_SUCCESS:
                    if(null != data && !"".equals(data.trim())) {
                        mTvShow.setText(data);
                    }
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        initEvent();
    }

    private void initViews() {
        mEtUrl = (EditText) findViewById(R.id.et_url);
        mBtnShow = (Button) findViewById(R.id.btn_show);
        mTvShow = (TextView) findViewById(R.id.tv_show);
    }

    private void initEvent() {
        mBtnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show();
            }
        });
    }

    private void show() {
        new Thread() {
            @Override
            public void run() {
                try {
                    String url = mEtUrl.getText().toString();
                    data = HttpService.getHtml(url);
                    mHandler.sendEmptyMessage(MESSAGE_SEND_SUCCESS);
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(MainActivity.this, R.string.str_show_fail, Toast.LENGTH_SHORT).show();
                }
            }
        }.start();
    }
}