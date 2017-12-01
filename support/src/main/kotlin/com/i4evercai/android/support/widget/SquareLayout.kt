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
open class SquareLayout : RatioLayout {


    constructor(context: Context) : super(context) {

    }

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0) {

    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        setRatio(1.0f)
    }


}