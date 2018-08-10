package com.example.petya.lesson90asynctask;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

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
                startTask();
                break;
            case R.id.btnStatus:
                showStatus();
                break;
        }
    }

    private void showStatus() {
        if (mMyTask != null)
            if (mMyTask.isCancelled())
                Toast.makeText(this, "CANCELLED", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(this, mMyTask.getStatus().toString(), Toast.LENGTH_SHORT).show();
    }

    private void startTask() {
        mMyTask = new MyTask(mTextView);
        mMyTask.execute();
        mMyTask.cancel(false);
    }
}
