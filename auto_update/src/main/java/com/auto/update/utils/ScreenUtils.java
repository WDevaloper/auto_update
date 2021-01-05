package com.auto.update.utils;

import android.content.Context;

public class ScreenUtils {

    public static float dp2Px(Context context, float dp) {
        if (context == null) {
            return -1f;
        }
        return dp * density(context);
    }

    public static float px2Dp(Context context, float px) {
        if (context == null) {
            return -1f;
        }
        return px / density(context);
    }

    public static float density(Context context) {
        return context.getResources().getDisplayMetrics().density;
    }


    public static int sp2px(Context context, float spValue) {
        float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    public static int px2sp(Context context, float pxValue) {
        float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }
}
