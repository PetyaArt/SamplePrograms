package com.example.petya.lesson88asynctask;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

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
            case R.id.btnGet:
                showResult();
                break;
        }
    }

    private void showResult() {
        if (mMyTask == null) return;
        int result = -1;
        try {
            Log.d(LOG_TAG, "Try to get result");
            result = mMyTask.get(1, TimeUnit.SECONDS);
            Log.d(LOG_TAG, "get returns " + result);
            Toast.makeText(this, "get returns " + result, Toast.LENGTH_LONG).show();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            Log.d(LOG_TAG, "get timeout, result = " + result);
            e.printStackTrace();
        }
    }
}
