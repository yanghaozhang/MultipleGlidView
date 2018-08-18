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
}
