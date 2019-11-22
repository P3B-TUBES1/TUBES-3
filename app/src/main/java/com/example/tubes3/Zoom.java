package com.example.tubes3;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Zoom extends RecyclerView {
    private int viewWidth;
    private int viewHeight;
    private final float minZoom = 1.f;
    private final float maxZoom = 2.5f;
    private float scaleFactor = 1.f;
    private ScaleGestureDetector customGestureDetector;
    public Zoom(@NonNull Context context,int viewWidth,int viewHeight) {
        super(context);
        this.viewWidth = viewWidth;
        this.viewHeight = viewHeight;
        customGestureDetector = new ScaleGestureDetector(context,new MyScaleListener());

    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {

        Log.d("berhasil masuk","masuk");
        customGestureDetector.onTouchEvent(e);
        return true;
    }

    @Override
    protected void onMeasure(int widthSpec, int heightSpec) {
        super.onMeasure(widthSpec, heightSpec);
        int width = MeasureSpec.getSize(widthSpec);
        int height = MeasureSpec.getSize(heightSpec);
        int scaledWidth = Math.round(viewWidth*scaleFactor);
        int scaledHeight = Math.round(viewHeight*scaleFactor);

        setMeasuredDimension(
                Math.min(width,scaledWidth),
                Math.min(height,scaledHeight)
        );
    }

    @Override
    public void setScaleX(float scaleX) {
        super.setScaleX(scaleX);
    }

    @Override
    public void setScaleY(float scaleY) {
        super.setScaleY(scaleY);
    }

    private class MyScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener{

        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            Log.d("test zoom scale","test");
            scaleFactor *=detector.getScaleFactor();
            scaleFactor = Math.max(minZoom,Math.min(maxZoom,scaleFactor));
            invalidate();
            requestLayout();
            setScaleX(scaleFactor);
            setScaleY(scaleFactor);
            return super.onScale(detector);
        }
    }
}
