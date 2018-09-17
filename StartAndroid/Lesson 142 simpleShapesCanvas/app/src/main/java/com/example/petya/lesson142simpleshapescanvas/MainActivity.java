package com.example.petya.lesson142simpleshapescanvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new DrawView(this));
    }

    private class DrawView extends View {

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

            // рисуем точку (50,50)
            canvas.drawPoint(50, 50, mPaint);

            // рисуем линию от (100,100) до (500,50)
            canvas.drawLine(100, 100, 500, 50, mPaint);

            // рисуем круг с центром в (100,200), радиус = 50
            canvas.drawCircle(100, 200, 50, mPaint);

            // рисуем прямоугольник
            // левая верхняя точка (200,150), нижняя правая (400,200)
            canvas.drawRect(200, 150, 400, 200, mPaint);

            // настройка объекта Rect
            // левая верхняя точка (250,300), нижняя правая (350,500)
            mRect.set(250, 300, 350, 500);
            canvas.drawRect(mRect, mPaint);
        }
    }

}
