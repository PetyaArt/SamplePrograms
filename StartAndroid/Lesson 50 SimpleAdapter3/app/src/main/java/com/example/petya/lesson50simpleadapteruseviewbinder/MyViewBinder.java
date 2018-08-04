package com.example.petya.lesson50simpleadapteruseviewbinder;

import android.content.Context;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;

public class MyViewBinder implements SimpleAdapter.ViewBinder {

    int red;
    int orange;
    int green;

    public MyViewBinder(Context context) {
        red = context.getResources().getColor(R.color.Red);
        orange = context.getResources().getColor(R.color.Orange);
        green = context.getResources().getColor(R.color.Green);
    }

    @Override
    public boolean setViewValue(View view, Object o, String s) {
        int i = 0;

        switch (view.getId()) {
            case R.id.linearLayout:
                i = ((Integer) o).intValue();
                if (i < 40 ) {
                    view.setBackgroundColor(green);
                }
                else if (i < 70 ) {
                    view.setBackgroundColor(orange);
                }
                else {
                    view.setBackgroundColor(red);
                }
                return true;
            case R.id.progressBar:
                i = ((Integer) o).intValue();
                ((ProgressBar)view).setProgress(i);
                return true;
        }
        return false;
    }
}
