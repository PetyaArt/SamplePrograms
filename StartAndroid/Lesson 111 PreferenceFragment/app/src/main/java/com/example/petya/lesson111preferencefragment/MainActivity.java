package com.example.petya.lesson111preferencefragment;

import java.util.List;

import android.preference.PreferenceActivity;

public class MainActivity extends PreferenceActivity {

    public void onBuildHeaders(List<Header> target) {
        loadHeadersFromResource(R.xml.pref_head, target);
    }
}
