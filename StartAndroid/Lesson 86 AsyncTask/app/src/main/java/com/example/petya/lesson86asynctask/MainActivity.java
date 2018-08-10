package com.example.petya.lesson86asynctask;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

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
        mMyTask = new MyTask(mTextView);
        mMyTask.execute();
    }
}
