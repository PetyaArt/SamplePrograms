package com.example.petya.lesson142simpleshapescanvas;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.View;

public class DrawView2 extends View {

    Paint mPaint;
    RectF mRectF;
    float[] points;
    float[] points1;

    public DrawView2(Context context) {
        super(context);
        mPaint = new Paint();
        mRectF = new RectF(700, 100, 800, 150);
        points = new float[]{100,50,150,100,150,200,50,200,50,100};
        points1 = new float[]{300,200,600,200,300,300,600,300,400,100,400,400,500,100,500,400};
    }


}
