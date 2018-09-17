package com.example.petya.treepythagors;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.View;

import static java.lang.Math.*;

public class DrawView extends View {

    Paint mPaint;
    Rect mRect;

    public DrawView(Context context) {
        super(context);
        mPaint = new Paint();
        mRect = new Rect();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawARGB(80, 102, 204, 255);

        mPaint.setColor(Color.BLUE);
        mPaint.setStrokeWidth(10);

        //Log.d("myLogs", String.valueOf(canvas.getHeight()));//1358 норм: 1200 y
        //Log.d("myLogs", String.valueOf(canvas.getWidth()));//2048 норм: 1024 x

        canvas.drawPoint(1024, 1200, mPaint);
        canvas.drawLine(1074, 1200, 974, 1200, mPaint);
        canvas.drawLine(974, 1200, 974, 1_100, mPaint);
        float Ax = 974f;
        float Ay = 1100f;
        canvas.drawLine(974, 1100, 1_074, 1_100, mPaint);
        float Bx = 1074f;
        float By = 1100f;
        canvas.drawLine(1074, 1100, 1074, 1200, mPaint);

        float Lx = 1074 - 974;
        float Ly = 1100 - 1100;
        float A = (float) sqrt(pow(Lx, 2) + pow(Ly, 2));

        float y = (float) cos(A);

        float Cx = 
        float Cy = (float) (-1 * Bx * sin(toRadians(45)) + By * cos(toRadians(45))) + 1000;
        Log.d("myLogs", String.valueOf(Cx));
        Log.d("myLogs", String.valueOf(Cy));
        canvas.drawPoint(Cx, Cy, mPaint);
    }
}
