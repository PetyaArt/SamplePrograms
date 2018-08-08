package com.example.petya.lesson69parcelable;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class TwoActivity extends AppCompatActivity {

    final String LOG_TAG = "myLogs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);

        Log.d(LOG_TAG, "getParcelableExtra");
        MyObject myObject = getIntent().getParcelableExtra("lol");
        Log.d(LOG_TAG, "myObj: " + myObject.s + ", " + myObject.i);
    }
}
