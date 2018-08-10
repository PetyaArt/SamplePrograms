package com.example.petya.lesson89asynctask;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

import static com.example.petya.lesson89asynctask.MainActivity.LOG_TAG;

public class MyTask extends AsyncTask<Void, Void, Void>{

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
    protected Void doInBackground(Void... voids) {
        try {
            for (int i = 0; i < 5; i++) {
                TimeUnit.SECONDS.sleep(1);
                //if (isCancelled()) return null;
                Log.d(LOG_TAG, "isCancelled: " + isCancelled());
            }
        } catch (InterruptedException e) {
            Log.d(LOG_TAG, "Interrupted");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        mTextView.setText("End");
        Log.d(LOG_TAG, "End");
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
        mTextView.setText("Cancel");
        Log.d(LOG_TAG, "Cancel");
    }
}
