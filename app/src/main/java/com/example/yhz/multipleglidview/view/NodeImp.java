package com.example.yhz.multipleglidview.view;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by yanghaozhang on 2018/7/3.
 */
public interface NodeImp {

    void draw(Canvas canvas, Paint paint, float left, float top, float right, float bottom);

    int getX();

    int getY();
}
