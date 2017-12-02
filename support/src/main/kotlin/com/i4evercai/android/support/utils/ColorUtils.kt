package com.i4evercai.android.support.utils

import android.graphics.Color

/**
 *
 * @Description: 颜色相关工具类
 * @author Fitz
 * @email FitzPro@qq.com
 * @date 2017/7/8 10:38
 * @version V1.0
 */
object ColorUtils {

    /**
     * 根据百分比改变颜色透明度
     */
    fun changeAlpha(color: Int, fraction: Float): Int {
        if (fraction >= 1) {
            return color
        } else if (fraction <= 0) {
            return Color.TRANSPARENT
        }
        val red = Color.red(color);
        val green = Color.green(color);
        val blue = Color.blue(color);
        val alpha = (Color.alpha(color) * fraction).toInt()
        return Color.argb(alpha, red, green, blue);
    }
}