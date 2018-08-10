package com.example.petya.lesson87asynctask;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView mTextView;
    private MyTask mMyTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = findViewById(R.id.textView);
    }

    public void onclick(View view) {
        mMyTask = new MyTask(mTextView);
        mMyTask.execute("file_path_1", "file_path_2", "file_path_3", "file_path_4");
    }
}
