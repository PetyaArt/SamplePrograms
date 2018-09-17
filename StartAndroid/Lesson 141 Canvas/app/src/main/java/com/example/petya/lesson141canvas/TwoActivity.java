package com.example.petya.lesson141canvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class TwoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new DrawView(this));
    }

    private class DrawView extends SurfaceView implements SurfaceHolder.Callback {

        private DrawThread mDrawThread;

        public DrawView(Context context) {
            super(context);
            getHolder().addCallback(this);
        }

        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            mDrawThread = new DrawThread(getHolder());
            mDrawThread.setRunning(true);
            mDrawThread.start();
        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {
            boolean retry = true;
            mDrawThread.setRunning(false);
            while (retry) {
                try {
                    mDrawThread.join();
                    retry = false;
                } catch (InterruptedException e) {
                }
            }
        }

        class DrawThread extends Thread {

            private boolean running = false;
            private SurfaceHolder mSurfaceHolder;

            public DrawThread(SurfaceHolder holder) {
                this.mSurfaceHolder = holder;
            }

            public void setRunning(boolean running) {
                this.running = running;
            }

            @Override
            public void run() {
                Canvas canvas;
                while (running) {
                    canvas = null;
                    try {
                        canvas = mSurfaceHolder.lockCanvas(null);
                        if (canvas == null)
                            continue;
                        canvas.drawColor(Color.BLUE);
                    } finally {
                        if (canvas != null) {
                            mSurfaceHolder.unlockCanvasAndPost(canvas);
                        }
                    }
                }
            }
        }
    }
}
