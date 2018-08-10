package com.example.petya.lesson88asynctask;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

import static com.example.petya.lesson88asynctask.MainActivity.LOG_TAG;

public class MyTask extends AsyncTask<Void, Void, Integer>{

    TextView mTextView;

    public MyTask(TextView textView) {
        mTextView = textView;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        mTextView.setText("Begin");
        Log.d(LOG_TAG, "Begin");
    }

    @Override
    protected Integer doInBackground(Void... voids) {
        try {
            TimeUnit.SECONDS.sleep(5);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return 303531;
    }

    @Override
    protected void onPostExecute(Integer integer) {
        super.onPostExecute(integer);
        mTextView.setText("End. Result = " + integer);
        Log.d(LOG_TAG, "End. Result = " + integer);
    }
}
