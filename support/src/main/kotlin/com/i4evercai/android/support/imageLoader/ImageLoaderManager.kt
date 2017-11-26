package com.i4evercai.android.support.imageLoader

import android.content.Context
import android.support.annotation.DrawableRes
import android.widget.ImageView

/**
 *
 * @Description: 图片加载管理器
 * @author Fitz
 * @email FitzPro@qq.com
 * @date 2017/7/13 14:23
 * @version V1.0
 */
class ImageLoaderManager {
    private lateinit var imageLoaderStrategy: ImageLoaderStrategy
    var baseImageUrl = ""

    private object Holder {
        val INSTANCE = ImageLoaderManager()
    }

    companion object {
        val instance: ImageLoaderManager by lazy { Holder.INSTANCE }
    }

    private constructor(){

    }

    fun init(context: Context,imageLoaderStrategy: ImageLoaderStrategy) {
        this.imageLoaderStrategy = imageLoaderStrategy
        imageLoaderStrategy.init(context)
    }

    fun showImage(imageView: ImageView, path: String) {
        imageLoaderStrategy.showImage(imageView, path)
    }

    fun showImage(imageView: ImageView, path: String, @DrawableRes placeholderResId: Int, @DrawableRes failureResId: Int) {
        imageLoaderStrategy.showImage(imageView, path, placeholderResId, failureResId)
    }


}