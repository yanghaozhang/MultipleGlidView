package com.example.yhz.multipleglidview;

import android.content.Context;

/**
 * Created by yanghaozhang on 2018/8/17.
 */
public class Utils {

    public static float dip2px(Context context, float dpValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return dpValue * scale;
    }

    /*将原来的网格内容，切割成part份，需要减去中间的线占的宽度*/
    public static float cutNodeWidth(Context context, int dp, int part, float lineWeight) {
        return (dip2px(context, dp) - lineWeight * (part - 1)) / part;
    }
}
