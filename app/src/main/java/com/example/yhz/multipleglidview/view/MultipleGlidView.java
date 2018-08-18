package com.example.yhz.multipleglidview.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.example.yhz.multipleglidview.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yanghaozhang on 2018/7/3.
 */
public class MultipleGlidView extends View {
    private static final String TAG = "MultipleGlidView";

    private Paint mPaint;

    private float mWidth;

    private float mHeight;

    /*行数*/
    private int mRowCount;

    /*列数*/
    private int mColumnCount;

    /*0:宽, 1:高*/
    private float[] mNodeDimen;

    /*是否是正方形*/
    private boolean mNodeSquare;

    /*未指定格子内容宽度*/
    private boolean mNodeWidthNoExactly;

    /*未指定格子内容高度*/
    private boolean mNodeHeightNoExactly;

    /*线宽*/
    private float mLineWeight;

    private int mLineColor;

    private List<NodeImp> mNodeList;

    private boolean mNoRight;

    private boolean mNoBottom;

    private boolean mNoLeft;

    private boolean mNoTop;

    public MultipleGlidView(Context context) {
        this(context, null);
    }

    public MultipleGlidView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MultipleGlidView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
        TypedArray a =
                context.obtainStyledAttributes(attrs,
                                               R.styleable.MultipleGlidView,
                                               defStyleAttr,
                                               0);
        mRowCount = a.getInteger(R.styleable.MultipleGlidView_row_count, 1);
        mColumnCount = a.getInteger(R.styleable.MultipleGlidView_column_count, 1);

        mNodeDimen[0] = a.getDimension(R.styleable.MultipleGlidView_node_width, -1);
        mNodeDimen[1] = a.getDimension(R.styleable.MultipleGlidView_node_height, -1);

        mNodeSquare = a.getBoolean(R.styleable.MultipleGlidView_node_is_square, false);

        mLineColor = a.getColor(R.styleable.MultipleGlidView_line_color, Color.BLACK);
        mLineWeight = a.getDimension(R.styleable.MultipleGlidView_line_weight, 1);

        mNoLeft = a.getBoolean(R.styleable.MultipleGlidView_no_left_line, false);
        mNoTop = a.getBoolean(R.styleable.MultipleGlidView_no_top_line, false);
        mNoRight = a.getBoolean(R.styleable.MultipleGlidView_no_right_line, false);
        mNoBottom = a.getBoolean(R.styleable.MultipleGlidView_no_bottom_line, false);

        if (mNodeDimen[0] == -1) {
            mNodeWidthNoExactly = true;
        }
        if (mNodeDimen[1] == -1) {
            mNodeHeightNoExactly = true;
        }

        a.recycle();
    }

    private void init() {
        mNodeList = new ArrayList<>();
        mNodeDimen = new float[2];
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DEV_KERN_TEXT_FLAG);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (MeasureSpec.getMode(widthMeasureSpec) != MeasureSpec.EXACTLY) {
            if (mNodeDimen[0] == -1) {
                if (mNodeDimen[1] > 0 && mNodeSquare) {
                    mNodeDimen[0] = mNodeDimen[1];
                    mNodeWidthNoExactly = false;
                }
            }
            int width =
                    (int) ((getOneNodeWidth()) * mRowCount + getWidthRevise() * mLineWeight + 0.5f);
            widthMeasureSpec = MeasureSpec.makeMeasureSpec(width, MeasureSpec.EXACTLY);
        }
        if (MeasureSpec.getMode(heightMeasureSpec) != MeasureSpec.EXACTLY) {
            if (mNodeDimen[1] == -1) {
                if (mNodeDimen[0] > 0 && mNodeSquare) {
                    mNodeDimen[1] = mNodeDimen[0];
                    mNodeHeightNoExactly = false;
                }
            }
            int height =
                    (int) ((getOneNodeHeight()) * mRowCount + getHeightRevise() * mLineWeight + 0.5f);
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY);
        }
        Log.e(TAG, "Class:YHZGridView-----Method:onMeasure\n");
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        int widthRevise = getWidthRevise();
        int heightRevise = getHeightRevise();

        /*如果未指定格子内容宽度，则按占满整个view算*/
        if (mNodeWidthNoExactly) {
            mNodeDimen[0] = (w - widthRevise * mLineWeight) / mColumnCount - mLineWeight;
        }
        if (mNodeHeightNoExactly) {
            mNodeDimen[1] = (h - heightRevise * mLineWeight) / mRowCount - mLineWeight;
        }

        // 正方形格子,计算设计宽高以较长为主
        if (mNodeSquare && Math.abs(mNodeDimen[0] - mNodeDimen[1]) > .01) {
            if (mNodeDimen[0] < mNodeDimen[1]) {
                mNodeDimen[0] = mNodeDimen[1];
            } else {
                mNodeDimen[1] = mNodeDimen[0];
            }
        }

        float designWidth = (getOneNodeWidth()) * mColumnCount + widthRevise * mLineWeight;
        float designHeight = (getOneNodeHeight()) * mRowCount + heightRevise * mLineWeight;
        if (designWidth > w) {
            mWidth = w;
            mNodeDimen[0] = (w - widthRevise * mLineWeight) / mColumnCount - mLineWeight;
        } else {
            mWidth = designWidth;
        }
        if (designHeight > h) {
            mHeight = h;
            mNodeDimen[1] = (h - heightRevise * mLineWeight) / mRowCount - mLineWeight;
        } else {
            mHeight = designHeight;
        }

        // 正方形格子，实际宽高，以较短为主
        if (mNodeSquare && Math.abs(mNodeDimen[0] - mNodeDimen[1]) > .01) {
            if (mNodeDimen[0] < mNodeDimen[1]) {
                mNodeDimen[1] = mNodeDimen[0];
                mHeight =
                        (getOneNodeHeight()) * mRowCount + heightRevise * mLineWeight;
            } else {
                mNodeDimen[0] = mNodeDimen[1];
                mWidth = (getOneNodeWidth()) * mColumnCount + widthRevise * mLineWeight;
            }
        }
        Log.e(TAG,
              "onSizeChanged: " + w + "---" + h + "---" + mWidth + "---" + mHeight + "===" + mNodeDimen[0] + "---" + mNodeDimen[1] + "---" + mLineWeight);
        Log.e(TAG, "onSizeChanged: " + designWidth + "---" + designHeight);
    }

    private float getOneNodeHeight() {
        return mNodeDimen[1] + mLineWeight;
    }

    private float getOneNodeWidth() {
        return mNodeDimen[0] + mLineWeight;
    }

    /*修正没有left边框，right边框的宽度*/
    private int getWidthRevise() {
        return mNoLeft && mNoRight ? -1 : (!mNoLeft && !mNoRight ? 1 : 0);
    }

    /*修正没有top边框，bottom边框的宽度*/
    private int getHeightRevise() {
        return mNoTop && mNoBottom ? -1 : (!mNoTop && !mNoBottom ? 1 : 0);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Log.e(TAG, "onDraw: ");
        super.onDraw(canvas);

        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(mLineWeight);
        mPaint.setColor(mLineColor);
        float halfLineHeight = mLineWeight / 2;
        float oneNodeWidth = getOneNodeWidth();
        float oneNodeHeight = getOneNodeHeight();

        int i = mNoLeft ? 1 : 0;
        int j = mNoRight ? mColumnCount - 1 : mColumnCount;
        //竖线
        for (; i <= j; i++) {
            float drawX = oneNodeWidth * i - (mNoLeft ? mLineWeight : 0) + halfLineHeight;
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
            float drawY = oneNodeHeight * i - (mNoTop ? mLineWeight : 0) + halfLineHeight;
            canvas.drawLine(0,
                            drawY,
                            mWidth,
                            drawY,
                            mPaint);
        }

        for (NodeImp node : mNodeList) {
            float leftX = node.getX() * oneNodeWidth + (mNoLeft ? 0 : mLineWeight);
            float topY = node.getY() * oneNodeHeight + (mNoTop ? 0 : mLineWeight);
            node.draw(canvas,
                      mPaint,
                      leftX,
                      topY,
                      leftX + mNodeDimen[0],
                      topY + mNodeDimen[1]);
        }
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
        invalidate();
    }

    public void setLineWeight(float lineH) {
        this.mLineWeight = lineH;
    }

    public void setNoRight(boolean noRight) {
        this.mNoRight = noRight;
    }

    public void setNoBottom(boolean noBottom) {
        this.mNoBottom = noBottom;
    }

    public void setmNodeDimen(float[] mNodeDimen) {
        this.mNodeDimen = mNodeDimen;
        if (mNodeDimen[0] > 0) {
            mNodeWidthNoExactly = false;
        }
        if (mNodeDimen[1] > 0) {
            mNodeHeightNoExactly = false;
        }
    }
}
