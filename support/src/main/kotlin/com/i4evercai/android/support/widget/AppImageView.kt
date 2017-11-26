package com.i4evercai.android.support.widget

import android.content.Context
import android.support.annotation.DrawableRes
import android.util.AttributeSet
import android.widget.ImageView
import com.i4evercai.android.support.R
import com.i4evercai.android.support.imageLoader.ImageLoaderManager
/**
 *
 * @Description: 图片显示控件，替换ImageView，以防后期替换图片加载控件需要大量更改原来的代码
 * @author Fitz
 * @email FitzPro@qq.com
 * @date 2017/7/13 15:15
 * @version V1.0
 */
open class AppImageView : ImageView {


    constructor(context: Context) : super(context) {

    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {

    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {

    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes) {

    }

    /**
     * 加载图片
     * 支持HTTP/HTTPS网络图片
     * 支持asset资源（"file:///android_asset/test.png"）
     * 支持SD卡资源（"file://sdcard/test.jpg"）
     * 支持raw资源（"android.resource://包名/raw/文件名"）或者（"android.resource://包名/raw/"+R.raw.test）
     * 支持drawable资源（"android.resource://包名/drawable/文件名"）或者（"android.resource://包名/drawable/"+R.drawable.test）
     * 支持ContentProvider资源（"content://media/external/images/media/139469"）
     */
    fun loadImage(path: String) {
        loadImage(path, R.drawable.support_ic_default_loading, R.drawable.support_ic_default_loading_fail)
    }

    fun loadImage(path: String, @DrawableRes placeholderResId: Int, @DrawableRes failureResId: Int) {
        /*  if (ImageLoaderManager.instance.baseImageUrl.equals(path)) {
              loadFail(failureResId)
          } else {
              ImageLoaderManager.instance.showImage(this, path, placeholderResId, failureResId)
          }
  */
        ImageLoaderManager.instance.showImage(this, path, placeholderResId, failureResId)
    }

    fun loadFail() {
        loadFail(R.drawable.support_ic_default_loading_fail)
    }

    fun loadFail(@DrawableRes failureResId: Int) {
        setImageResource(failureResId)
    }
}