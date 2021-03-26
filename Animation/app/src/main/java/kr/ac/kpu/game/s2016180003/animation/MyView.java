package kr.ac.kpu.game.s2016180003.animation;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.View;

public class MyView extends View {
    private static final String TAG = MyView.class.getSimpleName();
    private Paint paint = new Paint();
    private Rect rect = new Rect();

    public MyView(Context context){
        super(context);
        paint.setColor(0xff0044ff);
    }

    protected void onDraw(Canvas canvas){
        paint.setColor(0xff0044ff);
        int w = getWidth();
        int h = getHeight();
        rect.set(0,0,w,h);
        Log.d(TAG, "drawing" + rect);
        canvas.drawRect(rect, paint);
    }
}
