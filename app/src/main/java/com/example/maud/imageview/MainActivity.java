package com.example.maud.imageview;

import android.graphics.Canvas;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import static com.example.maud.imageview.R.id.canvas;

public class MainActivity extends AppCompatActivity {

    private CanvasView canvasView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        canvasView = findViewById(R.id.canvas);
    }

    public void clearCanvas(View view) {
        canvasView.clearCanvas();
    }

    }
