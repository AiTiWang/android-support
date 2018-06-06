package com.i4evercai.android.support.widget

import android.content.Context
import android.support.v7.widget.AppCompatTextView
import android.text.TextUtils
import android.util.AttributeSet

/**
 *
 * @Description: 跑马灯效果TextView
 * @author Fitz
 * @email FitzPro@qq.com
 * @date 2017/7/13 15:15
 * @version V1.0
 */
class MarqueeTextView : SingleLineTextView {
    constructor(context: Context) : super(context) {
        setAlawayMarquee()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        setAlawayMarquee()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        setAlawayMarquee()
    }

    private fun setAlawayMarquee() {
       // setSingleLine(true)
        // maxLines = 1
        ellipsize = TextUtils.TruncateAt.MARQUEE
        marqueeRepeatLimit = -1

    }

    override fun isFocused(): Boolean {
        return true
    }
}