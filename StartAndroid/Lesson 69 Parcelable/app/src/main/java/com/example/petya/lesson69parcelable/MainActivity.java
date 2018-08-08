package com.example.petya.lesson69parcelable;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    final static String LOG_TAG = "myLogs";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onclick(View view) {
        MyObject myObject = new MyObject("text", 1);
        Intent intent = new Intent(this, TwoActivity.class);
        intent.putExtra("lol", myObject);
        Log.d(LOG_TAG, "startActivity");
        startActivity(intent);
    }
}
