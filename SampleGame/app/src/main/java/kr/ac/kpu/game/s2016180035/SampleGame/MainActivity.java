package kr.ac.kpu.game.s2016180021.practice;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Choreographer;
import android.view.View;

import androidx.annotation.Nullable;

public class GameView extends View {
    private static final String TAG = GameView.class.getSimpleName();
    // final 이 붙은 변수는 생성자에서 값이 결정되어야 한다
    private Bitmap bitmap;
    private Ball ball;
    private Ball ball2;
    private float lastTime;
    public static float frameTime;
    public static GameView view;

    public GameView(Context context, @Nullable AttributeSet attribute) {
        super(context, attribute);
        GameView.view = this;
        ball = new Ball(100, 100, 100, 200);
        ball2 = new Ball(800, 100, -50, 150);
        generateResources();
        startUpdating();
    }

    private void startUpdating() {
        doGameFrame();
    }

    private void doGameFrame() {
        // update();
        ball.update();
        ball2.update();
        // draw();
        invalidate();

        Choreographer.getInstance().postFrameCallback(new Choreographer.FrameCallback() {
            @Override
            public void doFrame(long time) {
                frameTime = (float) (time-lastTime) / (1000 * 1000 * 1000);
                if (lastTime == 0) {
                    frameTime = 0;
                }
                doGameFrame();
                lastTime = time;
            }
        });

    }

    private void generateResources() {
        frameTime = 0;
        lastTime = 0;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // canvas.drawBitmap(bitmap, ball.x, ball.y,null);
        // canvas.drawBitmap(bitmap, ball2.x, ball2.y,null);
        ball.draw(canvas);
        ball2.draw(canvas);
        // Log.d(TAG,"Drawing at " + ball.x + ", " + ball.y + " / " + frameTime);
    }
}