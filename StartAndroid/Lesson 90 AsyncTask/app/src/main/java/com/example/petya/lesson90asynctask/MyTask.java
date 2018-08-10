package com.example.petya.lesson90asynctask;

import android.os.AsyncTask;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

public class MyTask extends AsyncTask<Void, Void, Void> {

    TextView mTextView;

    public MyTask(TextView textView) {
        mTextView = textView;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            for (int i = 0; i < 5; i++) {
                if (isCancelled()) return null;
                TimeUnit.SECONDS.sleep(1);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        mTextView.setText("Begin");
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        mTextView.setText("End");
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
        mTextView.setText("Cancel");
    }
}
