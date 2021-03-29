package kr.ac.kpu.game.s2016180035.SampleGame;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

public class GameView extends View {
    private static final String TAG = "Drawing Ball";
    private Bitmap bitmap;

    private float x;
    private float y;

    public GameView(Context context, @Nullable AttributeSet attrs){
        super(context, attrs);
        initResources();
        startUpdating();
    }

    private void startUpdating() {
        doGameFrame();
    }

    private void doGameFrame() {
//        update();
        x+=0.01;
        y+=0.02;
//        draw();
        invalidate();

        
    }

    private void initResources() {
        Resources res = getResources();
        bitmap = BitmapFactory.decodeResource(res, R.mipmap.soccer_ball_240);
        x = 100;
        y = 100;
    }

    protected void onDraw(Canvas canvas){
        canvas.drawBitmap(bitmap, x, y, null);
        Log.d(TAG, "Drawing at: " + x + "," + y);
    }
}