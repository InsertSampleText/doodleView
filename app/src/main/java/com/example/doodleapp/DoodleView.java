package com.example.doodleapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class DoodleView extends View{


    private Paint currentPaint;
    private Path currentPath;
    private List<ColoredPath> paths;

    private class ColoredPath {
        Path path;
        Paint paint;

        ColoredPath(Path path, Paint paint) {
            this.path = path;
            this.paint = paint;
        }
    }


    public DoodleView(Context context, AttributeSet attrs){
        super(context, attrs);
        init();

    }

    private void init() {
        paths = new ArrayList<>();
        currentPaint = new Paint();
        currentPaint.setColor(0xFF000000); // Default black color
        currentPaint.setAntiAlias(true);
        currentPaint.setStrokeWidth(10);
        currentPaint.setStyle(Paint.Style.STROKE);
        currentPaint.setStrokeJoin(Paint.Join.ROUND);
        currentPath = new Path();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        for (ColoredPath coloredPath : paths) {
            canvas.drawPath(coloredPath.path, coloredPath.paint);
        }

        canvas.drawPath(currentPath, currentPaint);
    }

    //drawing
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                currentPath.moveTo(x, y);
                return true;
            case MotionEvent.ACTION_MOVE:
                currentPath.lineTo(x, y);
                break;
            case MotionEvent.ACTION_UP:
                paths.add(new ColoredPath(new Path(currentPath), new Paint(currentPaint)));
                currentPath.reset();
                break;
            default:
                return false;
        }

        invalidate();
        return true;
    }

    //sliders
    public void setColor(int color) {
        currentPaint.setColor(color);
    }

    public void setBrushSize(float size) {
        currentPaint.setStrokeWidth(size);
    }

    public void setOpacity(int opacity) {
        currentPaint.setAlpha(opacity);
    }

    //clear button
    public void clearCanvas() {
        paths.clear();
        currentPath.reset();
        invalidate();
    }

}
