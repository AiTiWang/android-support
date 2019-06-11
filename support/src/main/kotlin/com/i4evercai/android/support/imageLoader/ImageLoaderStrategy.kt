package com.i4evercai.android.support.imageLoader


import android.content.Context
import android.graphics.Bitmap
import android.widget.ImageView
import androidx.annotation.DrawableRes

/**
 *
 * @Description: 图片加载策略
 * @author Fitz
 * @email FitzPro@qq.com
 * @date 2017/7/13 9:16
 * @version V1.0
 */
interface ImageLoaderStrategy {


    fun init(context: Context)
    fun getBitmap(path: String, onGetBitmapListener: OnGetBitmapListener)
    /**
     * 获取图片同步
     */
    fun getBitmapSync(path: String): Bitmap

    fun cleanMemory(context: Context)

    /**
     * 显示图片
     *
     * @param imageView 图片显示view
     * @param path 图片地址
     */
    fun showImage(imageView: ImageView, path: String)
    /* fun showImage(imageView: ImageView, path: String,imageLoaderOptions: ImageLoaderOptions)*/
    /**
     * 显示图片
     *
     * @param imageView 图片显示view
     * @param path 图片地址
     * @param placeholderResId 占位图resId
     */
    fun showImage(imageView: ImageView, path: String, @DrawableRes placeholderResId: Int)

    /**
     * 显示图片
     *
     * @param imageView 图片显示view
     * @param path 图片地址
     * @param placeholderResId 占位图resId
     * @param failureResId 加载失败图resId
     */
    fun showImage(imageView: ImageView, path: String, @DrawableRes placeholderResId: Int, @DrawableRes failureResId: Int)

    fun showImage(imageView: ImageView, path: String, isGif: Boolean, placeholderResId: Int, failureResId: Int)
}