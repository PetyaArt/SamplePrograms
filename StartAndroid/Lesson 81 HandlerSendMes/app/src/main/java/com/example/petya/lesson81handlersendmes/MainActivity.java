package com.example.petya.lesson81handlersendmes;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    final String LOG_TAG = "myLogs";

    final int STATUS_NONE = 0;
    final int STATUS_CONNECTING = 1;
    final int STATUS_CONNECTED = 2;

    Handler mHandler;

    TextView mTextView;
    ProgressBar mProgressBar;
    Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = findViewById(R.id.tvStatus);
        mProgressBar = findViewById(R.id.pbConnect);
        mButton = findViewById(R.id.btnConnect);

        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case STATUS_NONE:
                        mButton.setEnabled(true);
                        mTextView.setText("Not connected");
                        break;
                    case STATUS_CONNECTING:
                        mButton.setEnabled(false);
                        mProgressBar.setVisibility(View.VISIBLE);
                        mTextView.setText("Connecting");
                        break;
                    case STATUS_CONNECTED:
                        mProgressBar.setVisibility(View.GONE);
                        mTextView.setText("Connected");
                        break;
                }
            }
        };
        mHandler.sendEmptyMessage(STATUS_NONE);
    }

    public void onclick(View view) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    mHandler.sendEmptyMessage(STATUS_CONNECTING);
                    TimeUnit.SECONDS.sleep(2);

                    mHandler.sendEmptyMessage(STATUS_CONNECTED);

                    TimeUnit.SECONDS.sleep(3);

                    mHandler.sendEmptyMessage(STATUS_NONE);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }
}
