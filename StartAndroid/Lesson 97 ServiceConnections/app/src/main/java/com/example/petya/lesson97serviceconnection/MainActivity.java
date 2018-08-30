package com.example.petya.lesson97serviceconnection;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    final String LOG_TAG = "myLogs";

    boolean bound = false;
    ServiceConnection mServiceConnection;
    Intent mIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mIntent = new Intent("com.example.petya.lesson97sevicebindserver.MyService");
        mIntent.setPackage("com.example.petya");

        mServiceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                Log.d(LOG_TAG, "MainActivity onServiceConnected");
                bound = true;
            }

            @Override
            public void onServiceDisconnected(ComponentName componentName) {
                Log.d(LOG_TAG, "MainActivity onServiceDisconnected");
                bound = false;
            }
        };
    }

    public void onClickStart(View view) {
        startService(mIntent);
    }

    public void onClickStop(View view) {
        stopService(mIntent);
    }

    public void onClickBind(View view) {
        bindService(mIntent, mServiceConnection, BIND_AUTO_CREATE);
    }

    public void onClickUnBind(View view) {
        if (!bound) return;
        unbindService(mServiceConnection);
        bound = false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        onClickUnBind(null);
    }
}
