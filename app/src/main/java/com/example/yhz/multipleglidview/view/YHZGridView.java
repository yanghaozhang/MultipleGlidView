package com.example.yhz.multipleglidview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yanghaozhang on 2018/7/3.
 */
public class YHZGridView extends View {
    private static final String TAG = "YHZGridView";

    private Paint mPaint;

    private float mWidth;

    private float mHeight;

    // 行数
    private int mRowCount = 10;

    // 列数
    private int mColumnCount = 10;

    private float[] mNodeDimen;

    private boolean mNodeSquare;

    private float mLineH;

    private int mLineColor = Color.BLACK;

    private Context mContext;

    private List<NodeImp> mNodeList;

    private boolean mNoRight;

    private boolean mNoBottom;

    private boolean mNoLeft;

    private boolean mNoTop;

    public YHZGridView(Context context) {
        this(context, null);
    }

    public YHZGridView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public YHZGridView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mContext = context;
        mNodeList = new ArrayList<>();
        mNodeDimen = new float[2];
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DEV_KERN_TEXT_FLAG);
        mLineH = 1;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        Log.e(TAG, "onSizeChanged: " + w + "---" + h);
        super.onSizeChanged(w, h, oldw, oldh);
        // 正方形格子,计算设计宽高以较长为主
        if (mNodeSquare && Math.abs(mNodeDimen[0] - mNodeDimen[1]) > .01) {
            if (mNodeDimen[0] < mNodeDimen[1]) {
                mNodeDimen[0] = mNodeDimen[1];
            } else {
                mNodeDimen[1] = mNodeDimen[0];
            }
        }

        int widthLineCount = mNoLeft && mNoRight ? -1 : (!mNoLeft && !mNoRight ? 1 : 0);
        int heightLineCount = mNoTop && mNoBottom ? -1 : (!mNoTop && !mNoBottom ? 1 : 0);
        float designWidth = (mNodeDimen[0] + mLineH) * mColumnCount + widthLineCount * mLineH;
        float designHeight = (mNodeDimen[1] + mLineH) * mRowCount + heightLineCount * mLineH;

        if (designWidth > w) {
//            Log.e(TAG, "onSizeChanged: 1");
            mWidth = w;
            mNodeDimen[0] = (w - widthLineCount * mLineH) / mColumnCount - mLineH;
        } else {
//            Log.e(TAG, "onSizeChanged: 2");
            mWidth = designWidth;
        }
        if (designHeight > h) {
//            Log.e(TAG, "onSizeChanged: 3");
            mHeight = h;
            mNodeDimen[1] = (h - heightLineCount * mLineH) / mRowCount - mLineH;
        } else {
//            Log.e(TAG, "onSizeChanged: 4");
            mHeight = designHeight;
        }

        // 正方形格子，实际宽高，以较短为主
        if (mNodeSquare && Math.abs(mNodeDimen[0] - mNodeDimen[1]) > .01) {
            if (mNodeDimen[0] < mNodeDimen[1]) {
                mNodeDimen[1] = mNodeDimen[0];
                mHeight = (mNodeDimen[0] + mLineH) * mColumnCount + widthLineCount * mLineH;
            } else {
                mNodeDimen[0] = mNodeDimen[1];
                mWidth = (mNodeDimen[1] + mLineH) * mRowCount + heightLineCount * mLineH;
            }
        }

        Log.e(TAG, "onSizeChanged: " + mWidth + "---" + mHeight + "===" + mNodeDimen[0] + "---" + mNodeDimen[1] );
        Log.e(TAG, "onSizeChanged: " + mLineH );
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Log.e(TAG, "onDraw: ");
        super.onDraw(canvas);

        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(mLineH);
        mPaint.setColor(mLineColor);
        float halfLineHeight = mLineH / 2;
        float oneNodeWidth = mNodeDimen[0] + mLineH;
        float oneNodeHeight = mNodeDimen[1] + mLineH;

        int i = mNoLeft ? 1 : 0;
        int j = mNoRight ? mColumnCount - 1 : mColumnCount;
        //竖线
        for (; i <= j; i++) {
            float drawX = oneNodeWidth * i - (mNoLeft ? mLineH : 0) + halfLineHeight;
            canvas.drawLine(drawX,
                    0,
                    drawX,
                    mHeight,
                    mPaint);
        }

        i = mNoTop ? 1 : 0;
        j = mNoBottom ? mRowCount - 1 : mRowCount;
        //横线
        for (; i <= j; i++) {
            float drawY = oneNodeHeight * i - (mNoTop ? mLineH : 0) + halfLineHeight;
            canvas.drawLine(0,
                    drawY,
                    mWidth,
                    drawY,
                    mPaint);
        }

        for (NodeImp node : mNodeList) {
            float leftX = node.getX() * oneNodeWidth + (mNoLeft ? 0 : mLineH);
            float topY = node.getY() * oneNodeHeight + (mNoTop ? 0 : mLineH);
            node.draw(canvas,
                    mPaint,
                    leftX,
                    topY,
                    leftX + mNodeDimen[0],
                    topY + mNodeDimen[1]);
        }
    }

    public static float dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (dpValue * scale + 0.5f);
    }

    public void setRowCount(int rowCount) {
        this.mRowCount = rowCount;
    }

    public void setColumnCount(int columnCount) {
        this.mColumnCount = columnCount;
    }

    public void setLineColor(int lineColor) {
        this.mLineColor = lineColor;
    }

    public void setNodeList(List<NodeImp> nodeList) {
        this.mNodeList = nodeList;
    }

    public void setLineH(float lineH) {
        this.mLineH = lineH;
    }

    public void setNoRight(boolean noRight) {
        this.mNoRight = noRight;
    }

    public void setNoBottom(boolean noBottom) {
        this.mNoBottom = noBottom;
    }

    public void setmNodeDimen(float[] mNodeDimen) {
        this.mNodeDimen = mNodeDimen;
    }
}
