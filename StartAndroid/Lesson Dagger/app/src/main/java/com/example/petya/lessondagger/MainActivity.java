package com.example.petya.lessondagger;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    @Inject
    DatabaseHelper mDatabaseHelper;

    @Inject
    NetworkUtils mNetworkUtils;

    /*@Inject
    Preferences mPreferences;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        App.getComponent().injectsMainActivity(this);
    }
}
