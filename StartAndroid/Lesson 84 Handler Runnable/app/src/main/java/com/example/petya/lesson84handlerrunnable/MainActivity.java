package com.example.petya.lesson84handlerrunnable;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    ProgressBar pbCount;
    TextView tvInfo;
    CheckBox chbInfo;
    int cnt;

    final String LOG_TAG = "myLogs";
    final int max = 100;

    Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mHandler = new Handler();

        pbCount = findViewById(R.id.pbCount);
        pbCount.setMax(max);
        pbCount.setProgress(0);

        tvInfo = findViewById(R.id.tvInfo);

        chbInfo = findViewById(R.id.chbInfo);
        chbInfo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    tvInfo.setVisibility(View.VISIBLE);
                    mHandler.post(showInfo);
                }
                else {
                    tvInfo.setVisibility(View.GONE);
                    mHandler.removeCallbacks(showInfo);
                }
            }
        });

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (cnt = 1; cnt < max; cnt++) {
                        TimeUnit.MILLISECONDS.sleep(100);
                        mHandler.post(updateProgress);
                    }
                } catch (InterruptedException e) {
                        e.printStackTrace();
                }

            }
        });
        thread.start();

    }

    Runnable updateProgress = new Runnable() {
        @Override
        public void run() {
            pbCount.setProgress(cnt);
        }
    };

    Runnable showInfo = new Runnable() {
        @Override
        public void run() {
            Log.d(LOG_TAG, "showInfo");
            tvInfo.setText("Count = " + cnt);
            mHandler.postDelayed(showInfo, 1000);
        }
    };

}
