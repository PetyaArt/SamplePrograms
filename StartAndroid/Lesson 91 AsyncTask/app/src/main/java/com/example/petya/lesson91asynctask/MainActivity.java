package com.example.petya.lesson91asynctask;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    MyTask mMyTask;
    TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("qwe", "create MainActivity: " + this.hashCode());

        mTextView = findViewById(R.id.tv);

        mMyTask = (MyTask) getLastCustomNonConfigurationInstance();
        if (mMyTask == null) {
            mMyTask = new MyTask();
            mMyTask.execute();
        }

        mMyTask.link(this);

        Log.d("qwe", "create MyTask: " + mMyTask.hashCode());
    }

    static class MyTask extends AsyncTask<String, Integer, Void> {

        MainActivity activity;

        @Override
        protected Void doInBackground(String... strings) {
            try {
                for (int i = 0; i <= 10; i++) {
                    TimeUnit.SECONDS.sleep(1);
                    publishProgress(i);
                    Log.d("qwe", "i = " + i
                            + ", MyTask: " + this.hashCode()
                            + ", MainActivity: " + activity.hashCode());
                }
            } catch (InterruptedException e) {
                    e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            activity.mTextView.setText("i = " + values[0]);
        }

        public void link(MainActivity mainActivity) {
            activity = mainActivity;
        }

        public void unLink() {
            activity = null;
        }
    }


    @Override
    public Object onRetainCustomNonConfigurationInstance() {
        mMyTask.unLink();
        return mMyTask;
    }
}
