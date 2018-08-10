package com.example.petya.lesson86asynctask;

import android.os.AsyncTask;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

public class MyTask extends AsyncTask<Void, Void, Void> {

    private TextView mTextView;

    public MyTask(TextView textView) {
        mTextView = textView;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        mTextView.setText("Begin");
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        mTextView.setText("End");
    }
}
