package com.i4evercai.android.support.imageLoader

import android.graphics.Bitmap

/**
 *
 * @Description: 获取图片监听
 * @author Fitz
 * @email FitzPro@qq.com
 * @date 2017/7/13 11:06
 * @version V1.0
 */
interface OnGetBitmapListener {

    fun onSuccess(bitmap: Bitmap)
    fun onFailure()
}