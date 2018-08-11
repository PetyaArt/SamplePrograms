package com.example.petya.lesson98servicelocalbinding;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;

public class MyService extends Service {

    final String LOG_TAG = "myLogs";

    MyBinder binder = new MyBinder();

    Timer mTimer;
    TimerTask mTimerTask;
    long interval = 1000;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(LOG_TAG, "MyService onCreate");
        mTimer = new Timer();
        schedule();
    }

    private void schedule() {
        if (mTimerTask != null) mTimerTask.cancel();
        if (interval > 0) {
            mTimerTask = new TimerTask() {
                @Override
                public void run() {
                    Log.d(LOG_TAG, "run");
                }
            };
            mTimer.schedule(mTimerTask, 1000, interval);
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(LOG_TAG, "MyService onBind");
        return binder;
    }

    long upInterval(long gap) {
        interval = interval + gap;
        schedule();
        return interval;
    }

    long downInterval(long gap) {
        interval = interval - gap;
        if (interval < 0) interval = 0;
        schedule();
        return interval;
    }

    class MyBinder extends Binder {
        MyService getService() {
            return MyService.this;
        }
    }
}
