package com.kg.customviews;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class CustomView extends View {

    public static final String TAG = "CustomTextView";

    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private RectF rect = new RectF(0, 0, 200, 200);
    private float x = 0;
    private float y = 0;

    private float dx = 0;
    private float dy = 0;

    public CustomView(Context context) {
        super(context);
        init();
    }

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){

        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.GREEN);
        canvas.save();
        canvas.translate(dx, dy);
        canvas.drawRect(rect, paint);
        canvas.restore();
    }

    private boolean moving = false;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if(rect.contains(event.getX(), event.getY())){
                    moving = true;
                    x = event.getX();
                    y = event.getY();
                    Log.i(TAG, "onTouchEvent: DOWN MOVING");
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if(moving) {
                    dx = event.getX() - x;
                    dy = event.getY() - y;
                    Log.i(TAG, "onTouchEvent: MOVE MOVING");
                }
                break;
            case MotionEvent.ACTION_UP:
                moving = false;
                rect.left += dx;
                rect.top += dy;
                rect.right += dx;
                rect.bottom += dy;
                dx = 0;
                dy = 0;
                Log.i(TAG, "onTouchEvent: UP");
                break;
        }
        invalidate();
        return true;
    }
}
