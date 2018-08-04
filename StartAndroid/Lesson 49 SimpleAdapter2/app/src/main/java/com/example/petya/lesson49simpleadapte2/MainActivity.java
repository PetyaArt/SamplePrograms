package com.example.petya.lesson49simpleadapte2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    final String ATTRIBUTE_NAME_TEXT = "text";
    final String ATTRIBUTE_NAME_VALUE = "value";
    final String ATTRIBUTE_NAME_IMAGE = "image";

    static final int positive = android.R.drawable.arrow_up_float;
    static final int negative = android.R.drawable.arrow_down_float;

    ListView lvSimple;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int[] values = { 8, 4, -3, 2, -5, 0, 3, -6, 1, -1 };

        ArrayList<Map<String, Object>> data = new ArrayList<>(values.length);

        Map<String, Object> m;

        int img = 0;

        for (int i = 0; i < values.length; i++) {
            m = new HashMap<>();
            m.put(ATTRIBUTE_NAME_TEXT, "Day " + (i + 1));
            m.put(ATTRIBUTE_NAME_VALUE, values[i]);
            if (values[i] == 0) {
                img = 0;
            }
            else {
                if (values[i] > 0) {
                    img = positive;
                }
                else{
                    img = negative;
                }
            }
            m.put(ATTRIBUTE_NAME_IMAGE, img);
            data.add(m);
        }

        String[] from = { ATTRIBUTE_NAME_TEXT, ATTRIBUTE_NAME_VALUE,
                ATTRIBUTE_NAME_IMAGE };

        int[] to = { R.id.tvText, R.id.tvValue, R.id.ivImg };

        MySimpleAdapter mySimpleAdapter = new MySimpleAdapter(this, data, R.layout.item, from, to);

        lvSimple = findViewById(R.id.lvSimple);
        lvSimple.setAdapter(mySimpleAdapter);
    }
}
