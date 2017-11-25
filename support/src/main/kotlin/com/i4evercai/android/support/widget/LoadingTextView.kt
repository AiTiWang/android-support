package com.mokktech.support.widget

import android.content.Context
import android.os.Handler
import android.os.Message
import android.util.AttributeSet
import android.view.View
import android.widget.TextView

/**
 *
 * @Description: 自动在文字后面加...动画
 * @author Fitz
 * @email FitzPro@qq.com
 * @date 2017/8/7 15:26
 * @version V1.0
 */
class LoadingTextView : TextView {
    private val MSG_WHAT_POINT_ZERO = 0
    private val MSG_WHAT_POINT_ONE = 1
    private val MSG_WHAT_POINT_TWO = 2
    private val MSG_WHAT_POINT_THREE = 3
    private val delayMillis = 300L
    private val mHandler: Handler by lazy {
        object : Handler() {
            override fun handleMessage(msg: Message?) {
                super.handleMessage(msg)
                if (msg != null) {
                    var mTextO: String = text.toString().trim()
                    var mText = mTextO
                    val length = mTextO.length
                    if (mTextO.endsWith("...")) {
                        mText = mTextO.substring(0, length - 3)
                    } else if (mTextO.endsWith("..")) {
                        mText = mTextO.substring(0, length - 2)
                    } else if (mTextO.endsWith(".")) {
                        mText = mTextO.substring(0, length - 1)
                    }
                    when (msg.what) {
                        MSG_WHAT_POINT_ZERO -> {
                            text = mText + "   "
                            mHandler.sendEmptyMessageDelayed(MSG_WHAT_POINT_ONE, delayMillis)
                        }
                        MSG_WHAT_POINT_ONE -> {
                            text = mText + ".  "
                            mHandler.sendEmptyMessageDelayed(MSG_WHAT_POINT_TWO, delayMillis)
                        }
                        MSG_WHAT_POINT_TWO -> {
                            text = mText + ".. "
                            mHandler.sendEmptyMessageDelayed(MSG_WHAT_POINT_THREE, delayMillis)
                        }
                        MSG_WHAT_POINT_THREE -> {
                            text = mText + "..."
                            mHandler.sendEmptyMessageDelayed(MSG_WHAT_POINT_ZERO, delayMillis)
                        }
                    }
                }
            }
        }
    }


    constructor(context: Context) : super(context) {

    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {

    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {

    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes) {

    }

    fun startAnim() {
        mHandler.sendEmptyMessage(MSG_WHAT_POINT_ZERO)
    }

    fun stopAnim() {
        mHandler.removeCallbacksAndMessages(null)
    }

    /*override fun setVisibility(visibility: Int) {
        super.setVisibility(visibility)
        if (visibility == View.VISIBLE) {
            stopAnim()
            startAnim()
        } else {
            stopAnim()
        }
    }*/

    override fun onVisibilityChanged(changedView: View?, visibility: Int) {
        super.onVisibilityChanged(changedView, visibility)
        if (visibility == View.VISIBLE) {
            startAnim()
        } else {
            stopAnim()
        }
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        stopAnim()
        startAnim()
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        stopAnim()
    }
}