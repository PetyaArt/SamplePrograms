package com.example.petya.lesson82handlerhard;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    final String LOG_TAG = "myLogs";

    final int STATUS_NONE = 0; // нет подключения
    final int STATUS_CONNECTING = 1; // подключаемся
    final int STATUS_CONNECTED = 2; // подключено
    final int STATUS_DOWNLOAD_START = 3; // загрузка началась
    final int STATUS_DOWNLOAD_FILE = 4; // файл загружен
    final int STATUS_DOWNLOAD_END = 5; // загрузка закончена
    final int STATUS_DOWNLOAD_NONE = 6; // нет файлов для загрузки

    Handler mHandler;

    TextView mTextView;
    ProgressBar mProgressBar;
    Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = findViewById(R.id.tvStatus);
        mProgressBar = findViewById(R.id.pbDownload);
        mButton = findViewById(R.id.btnConnect);

        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case STATUS_NONE:
                        mButton.setEnabled(true);
                        mTextView.setText("Not connected");
                        mProgressBar.setVisibility(View.GONE);
                        break;
                    case STATUS_CONNECTING:
                        mButton.setEnabled(false);
                        mTextView.setText("Connecting");
                        break;
                    case STATUS_CONNECTED:
                        mTextView.setText("Connected");
                        break;
                    case STATUS_DOWNLOAD_START:
                        mTextView.setText("Start download " + msg.arg1 + " files");
                        mProgressBar.setMax(msg.arg1);
                        mProgressBar.setProgress(0);
                        mProgressBar.setVisibility(View.VISIBLE);
                        break;
                    case STATUS_DOWNLOAD_FILE:
                        mTextView.setText("Downloading. Left " + msg.arg2 + " false");
                        mProgressBar.setProgress(msg.arg1);
                        saveFile((byte[]) msg.obj);
                        break;
                    case STATUS_DOWNLOAD_END:
                        mTextView.setText("Downloads complete");
                        break;
                    case STATUS_DOWNLOAD_NONE:
                        mTextView.setText("No files for download");
                        break;
                }

            }
        };
        mHandler.sendEmptyMessage(STATUS_NONE);
    }

    private void saveFile(byte[] obj) {
    }

    public void onclick(View view) {
        Thread thread = new Thread(new Runnable() {
            Message msg;
            byte[] file;
            Random mRandom = new Random();


            @Override
            public void run() {
                try {
                    mHandler.sendEmptyMessage(STATUS_CONNECTING);
                    TimeUnit.SECONDS.sleep(1);

                    mHandler.sendEmptyMessage(STATUS_CONNECTED);
                    TimeUnit.SECONDS.sleep(1);

                    int filesCount = mRandom.nextInt(5);
                    Log.d(LOG_TAG, filesCount + "");

                    if (filesCount == 0) {
                        mHandler.sendEmptyMessage(STATUS_DOWNLOAD_NONE);

                        TimeUnit.MILLISECONDS.sleep(1500);
                        mHandler.sendEmptyMessage(STATUS_NONE);
                        return;
                    }
                    msg = mHandler.obtainMessage(STATUS_DOWNLOAD_START, filesCount, 0);
                    mHandler.sendMessage(msg);

                    for (int i = 0; i <= filesCount; i++) {
                        file = downloadFile();
                        msg = mHandler.obtainMessage(STATUS_DOWNLOAD_FILE, i, filesCount - 1, file);
                        mHandler.sendMessage(msg);
                    }

                    mHandler.sendEmptyMessage(STATUS_DOWNLOAD_END);

                    TimeUnit.MILLISECONDS.sleep(1500);
                    mHandler.sendEmptyMessage(STATUS_NONE);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }

    private byte[] downloadFile() throws InterruptedException {
        TimeUnit.SECONDS.sleep(2);
        return new byte[1024];
    }
}
