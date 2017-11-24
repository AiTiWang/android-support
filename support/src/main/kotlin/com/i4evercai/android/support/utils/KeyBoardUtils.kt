package com.i4evercai.android.support.utils

import android.content.Context
import android.view.inputmethod.InputMethodManager
import android.widget.EditText


/**
 *
 * @Description: 软键盘相关辅助类
 * @author Fitz
 * @email FitzPro@qq.com
 * @date 2017/7/8 11:16
 * @version V1.0
 */
object KeyBoardUtils {
        /**
         * 打开软键盘
         *
         * @param mEditText 输入框
         * @param mContext  下文
         */
        @JvmStatic
        fun openKeybord(context: Context,editText: EditText) {
            val imm = context
                    .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.showSoftInput(editText, InputMethodManager.RESULT_SHOWN)
            imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,
                    InputMethodManager.HIDE_IMPLICIT_ONLY)
        }

        /**
         * 关闭软键盘
         *
         * @param mEditText 输入框
         * @param mContext 上下文
         */
        @JvmStatic
        fun closeKeybord(context: Context,editText: EditText) {
            val imm = context
                    .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

            imm.hideSoftInputFromWindow(editText.windowToken, 0)
        }

}