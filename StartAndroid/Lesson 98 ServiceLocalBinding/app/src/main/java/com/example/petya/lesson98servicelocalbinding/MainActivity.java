package com.example.petya.lesson98servicelocalbinding;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    final String LOG_TAG = "myLogs";

    boolean bound = false;
    ServiceConnection mServiceConnection;
    Intent mIntent;
    MyService mMyService;
    TextView mTextView;
    long interval;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = findViewById(R.id.tvInterval);
        mIntent = new Intent(this, MyService.class);
        mServiceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                Log.d(LOG_TAG, "MainActivity onServiceConnected");
                mMyService = ((MyService.MyBinder) iBinder).getService();
                bound = true;
            }

            @Override
            public void onServiceDisconnected(ComponentName componentName) {
                Log.d(LOG_TAG, "MainActivity onServiceDisconnected");
                bound = false;
            }
        };
    }

    @Override
    protected void onStart() {
        super.onStart();
        bindService(mIntent, mServiceConnection, 0);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (!bound) return;
        unbindService(mServiceConnection);
        bound = false;
    }

    public void onClickStart(View view) {
        startService(mIntent);
    }

    public void onClickUp(View view) {
        if (!bound) return;
        interval = mMyService.upInterval(500);
        mTextView.setText("interval = " + interval);
    }

    public void onClickDown(View view) {
        if (!bound) return;
        interval = mMyService.downInterval(500);
        mTextView.setText("interval = " + interval);
    }
}
