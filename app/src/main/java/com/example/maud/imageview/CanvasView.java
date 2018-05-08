package com.example.maud.imageview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class CanvasView extends View {

    public int width;
    public int height;
    private Bitmap mbitmap;
    private Canvas mcanvas;
    private Path mpath;
    private Paint mpaint;
    private float mX, mY;
    private static final float TOLERANCE = 5;
    Context context;


    public CanvasView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;

        mpath = new Path();
        mpaint = new Paint();
        mpaint.setAntiAlias(true);
        mpaint.setColor(Color.BLACK);
        mpaint.setStyle(Paint.Style.STROKE);
        mpaint.setStrokeJoin(Paint.Join.ROUND);
        mpaint.setStrokeWidth(4f);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        mbitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        mcanvas = new Canvas(mbitmap);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawPath(mpath, mpaint);
    }

    private void StartTouch(float x, float y) {
        mpath.moveTo(x, y);
        mX = x;
        mY = y;
    }

    private void  moveTouch(float x, float y) {
        float dx = Math.abs(x - mX);
        float dy = Math.abs(x - mY);
        if (dx >= TOLERANCE || dy >= TOLERANCE) {
            mpath.quadTo(mX, mY, (x + mX) /2, (y + mY) /2);
            mX = x;
            mY = y;
        }
    }

    public void clearCanvas () {
        mpath.reset();
        invalidate();
    }

    private void upTouch() {
        mpath.lineTo(mX, mY);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                StartTouch(x, y);
                invalidate();
                break;

            case MotionEvent.ACTION_MOVE:
                moveTouch(x, y);
                invalidate();
                break;

            case MotionEvent.ACTION_UP:
                upTouch();
                invalidate();
                break;
        }

        return true;
    }
}