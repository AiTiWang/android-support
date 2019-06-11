package com.i4evercai.android.support.widget

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView


/**
 *
 * @Description: SingleLineTextView
 * @author Fitz
 * @email FitzPro@qq.com
 * @date 2017/7/13 15:15
 * @version V1.0
 */
open class SingleLineTextView : AppCompatTextView {

    constructor(context: Context) : super(context) {
        setSingleLine(true)
        maxLines = 1
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        setSingleLine(true)
        maxLines = 1
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        setSingleLine(true)
        maxLines = 1
    }


}