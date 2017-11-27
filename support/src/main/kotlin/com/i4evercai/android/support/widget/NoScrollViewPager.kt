package com.mokktech.support.widget

import android.content.Context
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.view.MotionEvent

/**
 *
 * @Description: 禁止滑动ViewPager
 * @author Fitz
 * @email FitzPro@qq.com
 * @date 2017/8/23 15:26
 * @version V1.0
 */
class NoScrollViewPager : ViewPager {
    private var isScroll = false

    constructor(context: Context) : super(context) {

    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {

    }


    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        return super.dispatchTouchEvent(ev)
    }

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        if (isScroll) {
            return super.onInterceptTouchEvent(ev)
        } else {
            return false
        }
    }

    override fun onTouchEvent(ev: MotionEvent?): Boolean {
        if (isScroll) {
            return super.onTouchEvent(ev)
        } else {
            return true
        }
    }

    public fun setScroll(scroll: Boolean) {
        this.isScroll = scroll
    }
}