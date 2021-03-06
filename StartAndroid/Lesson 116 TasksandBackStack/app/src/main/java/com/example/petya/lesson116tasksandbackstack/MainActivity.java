package com.example.petya.lesson116tasksandbackstack;

import android.app.ActivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.List;

public abstract class MainActivity extends AppCompatActivity {

    final String LOG_TAG = "myLogs";
    List<ActivityManager.RunningTaskInfo> list;
    ActivityManager mActivityManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle(getResources().getString(R.string.app_name) + " : " + getLocalClassName());
        mActivityManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
    }

    abstract public void onClick(View view);

    public void onInfoClick(View view) {
        list = mActivityManager.getRunningTasks(10);
        for (ActivityManager.RunningTaskInfo task : list) {
                if (task.baseActivity.flattenToShortString().startsWith("com.example.petya.lesson116")) {
                Log.d(LOG_TAG, "------------------");
                Log.d(LOG_TAG, "Count: " + task.numActivities);
                Log.d(LOG_TAG, "Root: " + task.baseActivity.flattenToShortString());
                Log.d(LOG_TAG, "Top: " + task.topActivity.flattenToShortString());
            }
        }
    }
}
