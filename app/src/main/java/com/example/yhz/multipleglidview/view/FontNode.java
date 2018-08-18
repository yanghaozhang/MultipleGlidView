package com.example.yhz.multipleglidview.view;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

/**
 * Created by yanghaozhang on 2018/7/3.
 */
public class FontNode extends CircleNode {
    private String mText = "";

    private int mFontBgColor = Color.BLUE;

    private float mFontSize = -100;

    public FontNode() {
    }

    public FontNode(int x, int y, String text, int color, int fontBgColor) {
        super(x, y, color);
        this.mText = text;
        mFontBgColor = fontBgColor;
    }

    @Override
    public void draw(Canvas canvas, Paint paint, float left, float top, float right, float bottom) {
        super.draw(canvas, paint, left, top, right, bottom);
        paint.setColor(mFontBgColor);
        paint.setStyle(Paint.Style.FILL);
        if (mFontSize < 0) {
            mFontSize = (right - left) * 2 / 3;
        }
        paint.setTextSize(mFontSize);
        paint.setTextAlign(Paint.Align.CENTER);
        Paint.FontMetrics fontMetrics = paint.getFontMetrics();

        float centerX = (right + left) / 2;
        float centerY = (bottom + top) / 2;
        float nameTop = fontMetrics.top;//为基线到字体上边框的距离,即上图中的top
        float nameBottom = fontMetrics.bottom;//为基线到字体下边框的距离,即上图中的bottom
        //https://blog.csdn.net/zly921112/article/details/50401976
        int baseLineY = (int) (centerY - nameTop / 2 - nameBottom / 2);//基线中间点的y轴计算公式
        canvas.drawText(mText,
                        centerX,
                        baseLineY,
                        paint);
    }

    public void setmText(String text) {
        this.mText = text;
    }

    public void setmFontBgColor(int fontBgColor) {
        this.mFontBgColor = fontBgColor;
    }

    public void setmFontSize(float mFontSize) {
        this.mFontSize = mFontSize;
    }
}
