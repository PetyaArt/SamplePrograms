package com.example.petya.lesson50simpleadapteruseviewbinder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    final String ATTRIBUTE_NAME_TEXT = "text";
    final String ATTRIBUTE_NAME_PB = "pb";
    final String ATTRIBUTE_NAME_LL = "ll";

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int load[] = { 41, 48, 22, 35, 30, 67, 51, 88 };

        ArrayList<Map<String, Object>> data = new ArrayList<>(load.length);

        Map<String, Object> m;

        for (int i = 0; i < load.length; i++) {
            m = new HashMap<>();
            m.put(ATTRIBUTE_NAME_TEXT, "Day " + (i + 1) + ". Load: " + load[i] + "%");
            m.put(ATTRIBUTE_NAME_PB, load[i]);
            m.put(ATTRIBUTE_NAME_LL, load[i]);
            data.add(m);
        }

        String[] from = { ATTRIBUTE_NAME_TEXT, ATTRIBUTE_NAME_PB,
                ATTRIBUTE_NAME_LL };

        int[] to = { R.id.textView, R.id.progressBar, R.id.linearLayout };

        SimpleAdapter simpleAdapter = new SimpleAdapter(this, data, R.layout.item, from, to);

        simpleAdapter.setViewBinder(new MyViewBinder(this));

        listView = findViewById(R.id.lvSimple);
        listView.setAdapter(simpleAdapter);
    }
}
