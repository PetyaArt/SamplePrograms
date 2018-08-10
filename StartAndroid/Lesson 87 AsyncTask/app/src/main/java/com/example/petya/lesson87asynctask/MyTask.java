package com.example.petya.lesson87asynctask;

import android.os.AsyncTask;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

public class MyTask extends AsyncTask<String, Integer, Void> {

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
    protected Void doInBackground(String... strings) {
        try {
            int cnt = 0;
            for (String string :
                    strings) {
                downloadFile(string);
                publishProgress(++cnt); 
            }

            TimeUnit.SECONDS.sleep(1);
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

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        mTextView.setText("Downloads " + values[0] + " files");
    }

    private  void downloadFile(String url) throws InterruptedException {
        TimeUnit.SECONDS.sleep(2);
    }
}
