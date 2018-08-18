package com.example.yhz.multipleglidview.view;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * 画中心圆
 *
 * Created by yanghaozhang on 2018/8/18.
 */
public class CircleNode implements NodeImp {

    private int mX = 0;

    private int mY = 0;

    private int mColor = Color.GREEN;

    private float mCircleSub = 3;

    protected CircleNode() {
    }

    protected CircleNode(int x, int y, int color) {
        this.mX = x;
        this.mY = y;
        this.mColor = color;
    }

    public CircleNode(int x, int y, int color, float circleSub) {
        mX = x;
        mY = y;
        mColor = color;
        mCircleSub = circleSub;
}

    @Override
    public void draw(Canvas canvas, Paint paint, float left, float top, float right, float bottom) {
        paint.setColor(mColor);
        paint.setStyle(Paint.Style.FILL);

        float centerX = (right + left) / 2;
        float centerY = (bottom + top) / 2;
        canvas.drawCircle(centerX, centerY, Math.abs(right - left) / 2 - mCircleSub, paint);
    }

    @Override
    public int getX() {
        return mX;
    }

    @Override
    public int getY() {
        return mY;
    }

    public void setX(int x) {
        this.mX = x;
    }

    public void setY(int y) {
        this.mY = y;
    }

    public void setColor(int color) {
        this.mColor = color;
    }

    public void setCircleSub(float circleSub) {
        this.mCircleSub = circleSub;
    }
}
