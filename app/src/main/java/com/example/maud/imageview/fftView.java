package com.example.maud.imageview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class fftView extends CanvasView {

    private Bitmap fbitmap;
    private Canvas fcanvas;
    private Path fpath;
    private Paint fpaint;

    private float startX, startY, stopX, stopY;


    public fftView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        fpath = new Path();
        fpaint = new Paint();
        fpaint.setColor(Color.DKGRAY);
        fpaint.setStrokeWidth(4f);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        startX = 200;
        startY = 800;
        stopX = 200;
        stopY = 60;

        //this should draw a line
        canvas.drawLine(10, 20, 30, 40, fpaint);
        canvas.drawLine(startX, startY, stopX, stopY, fpaint);
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        fbitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        fcanvas = new Canvas(fbitmap);

    }
}
