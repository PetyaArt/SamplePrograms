package com.example.petya.lesson83handler;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    final String LOG_TAG = "myLogs";

    Handler mHandler;

    Handler.Callback mCallback = new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            Log.d(LOG_TAG, "what = " + message.what);
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mHandler = new Handler(mCallback);
        sendMessages();
    }

    private void sendMessages() {
        Log.d(LOG_TAG, "send messages");
        mHandler.sendEmptyMessageDelayed(1, 1000);
        mHandler.sendEmptyMessageDelayed(2, 2000);
        mHandler.sendEmptyMessageDelayed(3, 3000);
        mHandler.sendEmptyMessageDelayed(2, 4000);
        mHandler.sendEmptyMessageDelayed(5, 5000);
        mHandler.sendEmptyMessageDelayed(2, 6000);
        mHandler.sendEmptyMessageDelayed(7, 7000);
        mHandler.removeMessages(2);
    }
}
