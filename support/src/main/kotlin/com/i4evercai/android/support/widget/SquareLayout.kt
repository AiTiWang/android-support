package com.i4evercai.android.support.widget

import android.content.Context
import android.util.AttributeSet
import android.widget.RelativeLayout


/**
 * @author Fitz
 * @version V1.0
 * @Description: 正方形layout
 * @email FitzPro@qq.com
 * @date 2017/4/28 15:19
 */
class SquareLayout : RelativeLayout {

    constructor(context: Context) : super(context) {

    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {

    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {

    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val childWidthSize = measuredWidth
        val childHeightSize = measuredHeight

        val newWidthMeasureSpec = MeasureSpec.makeMeasureSpec(
                childWidthSize, MeasureSpec.EXACTLY)
        val newHeightMeasureSpec = newWidthMeasureSpec
        super.onMeasure(newWidthMeasureSpec, newHeightMeasureSpec)
    }
}