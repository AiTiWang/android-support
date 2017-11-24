package com.i4evercai.android.support.utils

import android.content.Context
import android.graphics.Point
import android.util.DisplayMetrics
import android.util.TypedValue
import android.view.WindowManager


/**
 *
 * @Description: 常用单位转换的辅助类
 * @author Fitz
 * @email FitzPro@qq.com
 * @date 2017/7/8 10:38
 * @version V1.0
 */
object DensityUtils {
    /**
     * dp转px
     * @param context
     * @param val
     * @return
     */
    @JvmStatic
    fun dp2px(context: Context, dpVal: Float): Int {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpVal, context.getResources().getDisplayMetrics()).toInt()
    }

    /**
     * sp转px
     * @param context
     * @param val
     * @return
     */
    @JvmStatic
    fun sp2px(context: Context, spVal: Float): Int {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                spVal, context.getResources().getDisplayMetrics()).toInt()
    }

    /**
     * px转dp
     * @param context
     * @param pxVal
     * @return
     */
    @JvmStatic
    fun px2dp(context: Context, pxVal: Float): Float {
        val scale = context.getResources().getDisplayMetrics().density
        return pxVal / scale
    }

    /**
     * px转sp
     * @param fontScale
     * @param pxVal
     * @return
     */
    @JvmStatic
    fun px2sp(context: Context, pxVal: Float): Float {
        return pxVal / context.getResources().getDisplayMetrics().scaledDensity
    }

    @JvmStatic
    fun getWidth(context: Context): Int {
        val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val outMetrics = DisplayMetrics()
        wm.defaultDisplay.getMetrics(outMetrics)
        return outMetrics.widthPixels
    }

    @JvmStatic
    fun getHeight(context: Context): Int {
        val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val outMetrics = DisplayMetrics()
        wm.defaultDisplay.getMetrics(outMetrics)
        return outMetrics.heightPixels

    }

}