package com.example.petya.lesson103multitouch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener {

    StringBuilder mStringBuilder = new StringBuilder();
    TextView mTextView;
    int upPI = 0;
    int downPI = 0;
    boolean inTouch = false;
    String result = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = new TextView(this);
        mTextView.setTextSize(30);
        mTextView.setOnTouchListener(this);
        setContentView(mTextView);

    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {

        int actionMask = motionEvent.getActionMasked();
        int pointerIndex = motionEvent.getActionIndex();
        int pointerCounter = motionEvent.getPointerCount();

        switch (actionMask) {
            case MotionEvent.ACTION_DOWN:
                inTouch = true;
            case MotionEvent.ACTION_POINTER_DOWN:
                downPI = pointerIndex;
                break;

            case MotionEvent.ACTION_UP:
                inTouch = false;
                mStringBuilder.setLength(0);
            case MotionEvent.ACTION_POINTER_UP:
                upPI = pointerIndex;
                break;

            case MotionEvent.ACTION_MOVE:
                mStringBuilder.setLength(0);

                for (int i = 0; i < 10; i++) {
                    mStringBuilder.append("Index = " + i);
                    if (i < pointerCounter) {
                        mStringBuilder.append(", ID = " + motionEvent.getPointerId(i));
                        mStringBuilder.append(", X = " + motionEvent.getX(i));
                        mStringBuilder.append(", Y = " + motionEvent.getY(i));
                    } else {
                        mStringBuilder.append(", ID = ");
                        mStringBuilder.append(", X = ");
                        mStringBuilder.append(", Y = ");
                    }
                    mStringBuilder.append("\r\n");
                }
                break;
        }
        result = "down: " + downPI + "\n" + "up: " + upPI + "\n";

        if (inTouch) {
            result += "pointerCount = " + pointerCounter + "\n" + mStringBuilder.toString();
        }
        mTextView.setText(result);
        return true;
    }
}
