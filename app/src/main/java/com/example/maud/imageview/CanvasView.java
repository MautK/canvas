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
    private float startX, startY, stopX, stopY;
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
        mpaint.setStrokeWidth(8f);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        startX = 20;
        startY = 100;
        stopX = 200;
        stopY = 50;

        //this should draw a line
        canvas.drawLine(10, 20, 30, 40, mpaint);
        canvas.drawLine(startX, startY, stopX, stopY, mpaint);
        //use path to draw more complex shapes
        //define the shape by adding lines to the path
        // https://developer.android.com/training/custom-views/custom-drawing

        //mPath.moveTo for set the beginning of the next contour to the point (x,y)
        //mPath.lineTo add a line from the last point to the specified point (x,y)

        //this draws the path from the Touch event
        canvas.drawPath(mpath, mpaint);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        mbitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        mcanvas = new Canvas(mbitmap);

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
