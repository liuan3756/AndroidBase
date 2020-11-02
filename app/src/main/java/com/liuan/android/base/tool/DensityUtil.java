package com.liuan.android.base.tool;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.TypedValue;


import com.liuan.android.base.BaseApplication;

import java.lang.reflect.Field;

/**
 * @author Peach Parrot
 * @date 2019年05月15日 21:20
 */
public class DensityUtil {
    private static int statusBarHeight;

    public static int dp2px(Context context, int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                context.getResources()
                        .getDisplayMetrics());
    }

    public static int sp2px(Context context, int sp) {
        final float fontScale = context.getResources()
                .getDisplayMetrics().scaledDensity;
        return (int) (sp * fontScale + 0.5f);
    }

    @SuppressLint("PrivateApi")
    public static int getStatusBarHeight() {
        if (statusBarHeight == 0) {
            Class<?> c;
            Object obj;
            Field field;
            int x;
            try {
                c = Class.forName("com.android.internal.R$dimen");
                obj = c.newInstance();
                field = c.getField("status_bar_height");
                x = Integer.parseInt(field.get(obj)
                        .toString());
                statusBarHeight = BaseApplication.getApplication()
                                                 .getResources()
                                                 .getDimensionPixelSize(x);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        return statusBarHeight;
    }
}