package com.example.yhz.multipleglidview.view;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

import com.example.yhz.multipleglidview.YHZApplication;

/**
 * Created by yanghaozhang on 2018/7/3.
 */
public class SingleRingNode implements NodeImp {

    private int mX = 0;

    private int mY = 0;

    private float mRingHeight = -100;

    private float mLineHeight = -100;

    private boolean mNeedSlash = false;

    private int mRingColor = Color.BLUE;

    private int mSlashColor = Color.GREEN;

    public SingleRingNode() {
    }

    public SingleRingNode(int mX,
                          int mY,
                          float mRingHeight,
                          int mRingColor,
                          boolean mNeedSlash) {
        this.mX = mX;
        this.mY = mY;
        this.mRingHeight = mRingHeight;
        this.mRingColor = mRingColor;
        this.mNeedSlash = mNeedSlash;
    }

    @Override
    public void draw(Canvas canvas, Paint paint, float left, float top, float right, float bottom) {
        float nodeW = Math.abs(right - left) / 2;
        if (mRingHeight < 0) {
            mRingHeight = YHZGridView.dip2px(YHZApplication.mInstance, 2);
        }
        if (mLineHeight < 0) {
            mLineHeight = YHZGridView.dip2px(YHZApplication.mInstance, 2);
        }
        paint.setStrokeWidth(mRingHeight);
        paint.setColor(mRingColor);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawArc(new RectF(left + mRingHeight / 2,
                                 top + mRingHeight / 2,
                                 right - mRingHeight / 2,
                                 bottom - mRingHeight / 2), 0, 360, false, paint);

        if (mNeedSlash) {
            //设置斜线两点为斜边与圆环的中点距离
            float lineWidth = (float) ((1 - 1 / Math.sqrt(2)) * nodeW * 3 / 4);
            paint.setColor(mSlashColor);
            paint.setStrokeWidth(mLineHeight);
            canvas.drawLine(left + lineWidth,
                            bottom - lineWidth,
                            right - lineWidth,
                            top + lineWidth,
                            paint);
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

    public void setRingHeight(float ringHeight) {
        this.mRingHeight = ringHeight;
    }

    public void setLineHeight(float lineHeight) {
        this.mLineHeight = lineHeight;
    }

    public void setNeedSlash(boolean needSlash) {
        this.mNeedSlash = needSlash;
    }

    public void setRingColor(int ringColor) {
        this.mRingColor = ringColor;
    }

    public void setSlashColor(int slashColor) {
        this.mSlashColor = slashColor;
    }
}
