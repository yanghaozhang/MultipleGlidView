package com.example.yhz.multipleglidview.view;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.simple.multipleglid.Utils;
import com.example.yhz.multipleglidview.YHZApplication;
import com.simple.multipleglid.NodeImp;

/**
 * Created by yanghaozhang on 2018/7/4.
 */
public class FourLineNode implements NodeImp {

    private int mX = 0;

    private int mY = 0;

    private int[] mLines = {0};

    private int mLeftColor = Color.RED;

    private int mRightColor = Color.BLUE;

    private float mLineHeight = -100;

    public FourLineNode() {
    }

    public FourLineNode(int x,
                        int y,
                        int leftColor,
                        int rightColor,
                        float lineHeight,
                        int... lines) {
        this.mX = x;
        this.mY = y;
        this.mLines = lines;
        this.mLeftColor = leftColor;
        this.mRightColor = rightColor;
        this.mLineHeight = lineHeight;
    }

    @Override
    public void draw(Canvas canvas, Paint paint, float left, float top, float right, float bottom) {
        paint.setStyle(Paint.Style.STROKE);

        if (mLineHeight < 0) {
            mLineHeight = Utils.dip2px(YHZApplication.mInstance, 2);
        }
        paint.setStrokeWidth(mLineHeight);
        float lineSub = (float) (mLineHeight / Math.sqrt(2));

        float centerX = (right + left) / 2;
        float centerY = (bottom + top) / 2;

        for (int line : mLines) {
            float lineLeft = left;
            float lineTop = top;
            float lineRight = right;
            float lineBottom = bottom;
            switch (line) {
                case 3:
                    paint.setColor(mRightColor);
                    lineLeft = centerX;
                    lineTop = centerY;
                    break;
                case 2:
                    paint.setColor(mLeftColor);
                    lineTop = centerY;
                    lineRight = centerX;
                    break;
                case 1:
                    paint.setColor(mRightColor);
                    lineLeft = centerX;
                    lineBottom = centerY;
                    break;
                case 0:
                default:
                    paint.setColor(mLeftColor);
                    lineRight = centerX;
                    lineBottom = centerY;
                    break;
            }
            canvas.drawLine(lineLeft + lineSub,
                            lineBottom - lineSub,
                            lineRight - lineSub,
                            lineTop + lineSub,
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

    public void setLines(int[] lines) {
        this.mLines = lines;
    }

    public void setLeftColor(int leftColor) {
        this.mLeftColor = leftColor;
    }

    public void setRightColor(int rightColor) {
        this.mRightColor = rightColor;
    }

    public void setLineHeight(float lineHeight) {
        this.mLineHeight = lineHeight;
    }
}
