package com.i4evercai.android.support.widget

import android.content.Context
import android.graphics.drawable.AnimationDrawable
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView

/**
 *
 * @Description: 自动播放动画ImageView
 * @author Fitz
 * @email FitzPro@qq.com
 * @date 2017/8/7 15:16
 * @version V1.0
 */
open class AnimImageView : ImageView {
    constructor(context: Context) : super(context) {

    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {

    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {

    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes) {

    }

    override fun onVisibilityChanged(changedView: View?, visibility: Int) {
        super.onVisibilityChanged(changedView, visibility)
        if (visibility == View.VISIBLE) {
            startAnim()
        } else {
            stopAnim()
        }
    }


    fun startAnim() {
        val backgroudDrawable: Drawable? = drawable

        if (backgroudDrawable != null && backgroudDrawable is AnimationDrawable) {
            backgroudDrawable.start()
        }
    }

    fun stopAnim() {

        val backgroudDrawable: Drawable? = drawable
        if (backgroudDrawable != null && backgroudDrawable is AnimationDrawable && backgroudDrawable.isRunning) {
            backgroudDrawable.stop()

        }
    }


    /* override fun setVisibility(visibility: Int) {
         super.setVisibility(visibility)
         if (visibility == View.VISIBLE) {
             startAnim()
         } else {
             stopAnim()
         }
     }*/
    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        startAnim()
    }

    override fun onDetachedFromWindow() {
        stopAnim()
        super.onDetachedFromWindow()
    }
}