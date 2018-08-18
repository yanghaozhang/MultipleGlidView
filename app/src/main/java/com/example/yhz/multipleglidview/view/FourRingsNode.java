package com.example.yhz.multipleglidview.view;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

import com.example.yhz.multipleglidview.Utils;
import com.example.yhz.multipleglidview.YHZApplication;

/**
 * Created by yanghaozhang on 2018/7/4.
 */
public class FourRingsNode implements NodeImp {
    private int mX = 0;

    private int mY = 0;

    private int[] mRings = {0};

    private int mLeftColor = Color.RED;

    private int mRightColor = Color.BLUE;

    private float mRingHeight = -100;

    public FourRingsNode() {
    }

    public FourRingsNode(int x,
                         int y,
                         int leftColor,
                         int rightColor,
                         float ringHeight,
                         int... rings) {
        this.mX = x;
        this.mY = y;
        this.mRings = rings;
        this.mLeftColor = leftColor;
        this.mRightColor = rightColor;
        this.mRingHeight = ringHeight;
    }

    @Override
    public void draw(Canvas canvas, Paint paint, float left, float top, float right, float bottom) {
        if (mRingHeight < 0) {
            mRingHeight = Utils.dip2px(YHZApplication.mInstance, 6);
        }
        paint.setStrokeWidth(mRingHeight);
        paint.setStyle(Paint.Style.STROKE);

        float centerX = (right + left) / 2;
        float centerY = (bottom + top) / 2;

        for (int ring : mRings) {
            float ringLeft = left;
            float ringTop = top;
            float ringRight = right;
            float ringBottom = bottom;
            switch (ring) {
                case 3:
                    paint.setColor(mRightColor);
                    ringLeft = centerX;
                    ringTop = centerY;
                    break;
                case 2:
                    paint.setColor(mLeftColor);
                    ringTop = centerY;
                    ringRight = centerX;
                    break;
                case 1:
                    paint.setColor(mRightColor);
                    ringLeft = centerX;
                    ringBottom = centerY;
                    break;
                case 0:
                default:
                    paint.setColor(mLeftColor);
                    ringRight = centerX;
                    ringBottom = centerY;
                    break;
            }
            canvas.drawArc(new RectF(ringLeft + mRingHeight / 2,
                                     ringTop + mRingHeight / 2,
                                     ringRight - mRingHeight / 2,
                                     ringBottom - mRingHeight / 2), 0, 360, false, paint);
        }
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

    public void setRings(int[] rings) {
        this.mRings = rings;
    }

    public void setLeftColor(int leftColor) {
        this.mLeftColor = leftColor;
    }

    public void setRightColor(int rightColor) {
        this.mRightColor = rightColor;
    }

    public void setRingHeight(float ringHeight) {
        this.mRingHeight = ringHeight;
    }
}
