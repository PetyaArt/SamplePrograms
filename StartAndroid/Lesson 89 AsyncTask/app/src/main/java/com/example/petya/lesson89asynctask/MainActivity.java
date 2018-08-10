package com.example.petya.lesson89asynctask;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    static final String LOG_TAG = "myLogs";

    MyTask mMyTask;
    TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = findViewById(R.id.tvInfo);
    }

    public void onclick(View view) {
        switch (view.getId()) {
            case R.id.btnStart:
                mMyTask = new MyTask(mTextView);
                mMyTask.execute();
                break;
            case R.id.btnCancel:
                cancelTask();
                break;
        }
    }

    private void cancelTask() {
        if (mMyTask == null) return;
        //Log.d(LOG_TAG, "cancel result: " + mMyTask.cancel(false));
        Log.d(LOG_TAG, "cancel result: " + mMyTask.cancel(true));
    }
}
