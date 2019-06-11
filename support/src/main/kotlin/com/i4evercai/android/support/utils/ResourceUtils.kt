package com.i4evercai.android.support.utils


import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Build
import androidx.annotation.DrawableRes


/**
 *
 * @Description: Resource资源相关工具类
 * @author Fitz
 * @email FitzPro@qq.com
 * @date 2017/7/8 11:20
 * @version V1.0
 */
object ResourceUtils {
    @JvmStatic
    fun getDrawable(context: Context, @DrawableRes resId: Int): Drawable {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            return context.resources.getDrawable(resId)
        } else {
            return context.getDrawable(resId)
        }
    }

    @JvmStatic
    fun getColor(context: Context, colorId: Int): Int {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return context.resources.getColor(colorId)
        } else {
            return context.getColor(colorId)
        }
    }
}