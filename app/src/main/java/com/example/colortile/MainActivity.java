package com.example.colortile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentTransaction;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity {
    static int FLAGS = View.SYSTEM_UI_FLAG_FULLSCREEN |
            View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY |
            View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
            View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
            View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION |
            View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;

    private GameSurfaceView gameSurfaceView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setFlags();
        setMargin();
        gameSurfaceView = new GameSurfaceView(this);
        ((ConstraintLayout) findViewById(R.id.container)).addView(gameSurfaceView);
        // SurfaceView surfaceView = findViewById(R.id.surfaceView);
        // gameManager = new GameManager(surfaceView);
    }

    @Override
    protected void onPause() {
        super.onPause();
        gameSurfaceView.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setFlags();
        gameSurfaceView.onResume();
    }

    private void setFlags() {
        getWindow().getDecorView().setSystemUiVisibility(FLAGS);
    }

    private void setMargin() {
        int height = getNavigationBarHeight();
        ConstraintLayout constraintLayout = findViewById(R.id.container);
        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) constraintLayout.getLayoutParams();
        params.setMargins(0, height, 0, height);
        constraintLayout.setLayoutParams(params);
    }

    private int getNavigationBarHeight() {
        Display display = getWindowManager().getDefaultDisplay();
        Point viewP = new Point();
        Point realP = new Point();
        display.getSize(viewP);
        display.getRealSize(realP);
        return realP.y - viewP.y;
    }
}
