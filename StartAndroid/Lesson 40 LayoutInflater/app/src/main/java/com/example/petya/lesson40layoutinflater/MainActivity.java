package com.example.petya.lesson40layoutinflater;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    final String LOG_TAG = "myLogs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*LayoutInflater layoutInflater = getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.text, null, false);
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();

        LinearLayout linearLayout = findViewById(R.id.linearLayout);
        linearLayout.addView(view);

        Log.d(LOG_TAG, "Class of view: " + view.getClass().toString());
        Log.d(LOG_TAG, "LayoutParams of view is null: " + (layoutParams == null));
        Log.d(LOG_TAG, "Text of view: " + ((TextView) view).getText());*/

        LayoutInflater layoutInflater = getLayoutInflater();

        LinearLayout linearLayout = findViewById(R.id.linearLayout);
        View view = layoutInflater.inflate(R.layout.text, linearLayout, true);
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();

        Log.d(LOG_TAG, "Class of view1: " + view.getClass().toString());
        Log.d(LOG_TAG, "Class of layoutParams of view1: " + layoutParams.getClass().toString());
        //Log.d(LOG_TAG, "Text of view1: " + ((TextView) view).getText());

        RelativeLayout relativeLayout = findViewById(R.id.relativeLayout);
        View view2 = layoutInflater.inflate(R.layout.text, relativeLayout, true);
        ViewGroup.LayoutParams layoutParams2 = view2.getLayoutParams();

        Log.d(LOG_TAG, "Class of view2: " + view2.getClass().toString());
        Log.d(LOG_TAG, "Class of layoutParams of view2: " + layoutParams2.getClass().toString());
        //Log.d(LOG_TAG, "Text of view2: " + ((TextView) view2).getText());

    }
}
