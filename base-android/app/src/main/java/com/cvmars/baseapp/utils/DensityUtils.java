package com.cvmars.baseapp.utils;

import android.content.Context;
import android.util.TypedValue;

import java.lang.reflect.Field;

/** 
 * 常用单位转换的辅助类 
 *  
 *  
 *  
 */  
public class DensityUtils  
{  
    private DensityUtils()  
    {  
        /* cannot be instantiated */  
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    private  static int statusBarHeight;


    public static int getStatusBarHeight(Context context) {
        if (statusBarHeight == 0) {
            try {
                Class<?> c = Class.forName("com.android.internal.R$dimen");
                Object o = c.newInstance();
                Field field = c.getField("status_bar_height");
                int x = (Integer) field.get(o);
                statusBarHeight = context.getResources().getDimensionPixelSize(x);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return statusBarHeight;
    }

    /** 
     * dp转px 
     *  
     * @param context 
     * @return
     */  
    public static int dp2px(Context context, float dpVal)
    {  
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpVal, context.getResources().getDisplayMetrics());  
    }  
  
    /** 
     * sp转px 
     *  
     * @param context 
     * @return
     */  
    public static int sp2px(Context context, float spVal)
    {  
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                spVal, context.getResources().getDisplayMetrics());  
    }  
  
    /** 
     * px转dp 
     *  
     * @param context 
     * @param pxVal 
     * @return 
     */  
    public static float px2dp(Context context, float pxVal)
    {  
        final float scale = context.getResources().getDisplayMetrics().density;  
        return (pxVal / scale);  
    }  
  
    /** 
     * px转sp 
     *  
     * @param pxVal
     * @return 
     */  
    public static float px2sp(Context context, float pxVal)
    {  
        return (pxVal / context.getResources().getDisplayMetrics().scaledDensity);  
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}  