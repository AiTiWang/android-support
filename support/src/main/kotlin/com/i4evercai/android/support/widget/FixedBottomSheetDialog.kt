package com.i4evercai.android.support.widget

import android.content.Context
import android.content.DialogInterface
import android.support.annotation.StyleRes
import android.support.design.widget.BottomSheetDialog
import android.view.ViewGroup
import android.os.Bundle
import com.i4evercai.android.support.utils.ScreenUtils


/**
 * @author Fitz
 * @version V1.0
 * @Description: 修复BottomSheetDialog弹出状态栏变黑问题
 * @email FitzPro@qq.com
 * @date 2017/5/12 11:41
 */
class FixedBottomSheetDialog : BottomSheetDialog {

    constructor(context: Context) : this(context, 0)

    constructor(context: Context, @StyleRes theme: Int) : super(context, theme)

    constructor(context: Context, cancelable: Boolean, cancelListener: DialogInterface.OnCancelListener) : super(context, cancelable, cancelListener)

    override fun onCreate(savedInstanceState: Bundle) {
        super.onCreate(savedInstanceState)
        val screenHeight = ScreenUtils.getScreenHeight(ownerActivity)
        val statusBarHeight = ScreenUtils.getStatusHeight(context)
        val dialogHeight = screenHeight - statusBarHeight
        window!!.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, if (dialogHeight == 0) ViewGroup.LayoutParams.MATCH_PARENT else dialogHeight)
    }


}