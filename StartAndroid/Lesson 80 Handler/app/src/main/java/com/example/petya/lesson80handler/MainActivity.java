package com.example.petya.lesson80handler;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    final String LOG_TAG = "myLogs";

    Handler mHandler;
    TextView mTextView;
    Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = findViewById(R.id.textView);
        mButton = findViewById(R.id.button);
        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                mTextView.setText("Закачано файлов" + msg.what);
                if (msg.what == 10) mButton.setEnabled(true);
            }
        };
    }

    public void onclick(View view) {
        switch (view.getId()) {
            case R.id.button:
                mButton.setEnabled(false);
                Thread thread =new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i <= 10; i++) {
                            downloadFile();
                            mHandler.sendEmptyMessage(i);
                            Log.d(LOG_TAG, "Закачано файлов: " + i);
                        }
                    }
                });
                thread.start();
                break;
            case R.id.button2:
                Log.d(LOG_TAG, "test");
                break;
            default:
                break;
        }
    }

    private void downloadFile() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
