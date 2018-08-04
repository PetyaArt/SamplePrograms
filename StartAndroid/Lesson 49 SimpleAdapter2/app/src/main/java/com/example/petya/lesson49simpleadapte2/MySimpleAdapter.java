package com.example.petya.lesson49simpleadapte2;

import android.content.Context;
import android.graphics.Color;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

import static com.example.petya.lesson49simpleadapte2.MainActivity.negative;
import static com.example.petya.lesson49simpleadapte2.MainActivity.positive;

public class MySimpleAdapter extends SimpleAdapter {

    public MySimpleAdapter(Context context, List<? extends Map<String, ?>> data, int resource, String[] from, int[] to) {
        super(context, data, resource, from, to);
    }

    @Override
    public void setViewText(TextView v, String text) {
        super.setViewText(v, text);

        if (v.getId() == R.id.tvValue) {
            int i = Integer.parseInt(text);
            if (i > 0) {
                v.setTextColor(Color.GREEN);
            }
            if (i < 0) {
                v.setTextColor(Color.RED);
            }
        }
    }

    @Override
    public void setViewImage(ImageView v, int value) {
        super.setViewImage(v, value);

        if (value == negative) {
            v.setBackgroundColor(Color.RED);
        }
        if (value == positive) {
            v.setBackgroundColor(Color.GREEN);
        }
    }
}
