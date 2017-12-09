package com.i4evercai.android.support.widget

import android.content.Context
import android.support.v7.widget.AppCompatEditText
import android.support.v7.widget.AppCompatTextView
import android.util.AttributeSet

/**
 *
 * @Description: SingleLineEditText
 * @author Fitz
 * @email FitzPro@qq.com
 * @date 2017/7/13 15:15
 * @version V1.0
 */
class SingleLineClearEditText : ClearEditText {

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