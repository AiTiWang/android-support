package com.i4evercai.android.support.widget;

import android.content.Context
import android.graphics.drawable.Drawable
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import com.i4evercai.android.support.R


/**
 *
 * @Description: 自带清除按钮的EditText
 * @author Fitz
 * @email FitzPro@qq.com
 * @date 2017/8/11 16:06
 * @version V1.0
 */
open class ClearEditText : AppCompatEditText, View.OnTouchListener, View.OnFocusChangeListener, TextWatcher {

    private lateinit var mClearTextIcon: Drawable
    private var mOnFocusChangeListener: OnFocusChangeListener? = null
    private var mOnTouchListener: OnTouchListener? = null

    init {
        init(context)
    }

    constructor(context: Context) : super(context) {

    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {

    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {

    }

    fun init(context: Context) {
        val drawable = ContextCompat.getDrawable(context, R.drawable.support_ic_edit_text_delete)
        val wrappedDrawable = DrawableCompat.wrap(drawable!!)
        DrawableCompat.setTint(wrappedDrawable, getCurrentHintTextColor());
        mClearTextIcon = wrappedDrawable;
        mClearTextIcon.setBounds(0, 0, mClearTextIcon.getIntrinsicHeight(), mClearTextIcon.getIntrinsicHeight());
        setClearIconVisible(false);
        super.setOnTouchListener(this);
        super.setOnFocusChangeListener(this);
        addTextChangedListener(this);
    }

    override fun setOnTouchListener(l: OnTouchListener?) {
        mOnTouchListener = l
    }

    override fun setOnFocusChangeListener(l: OnFocusChangeListener?) {
        mOnFocusChangeListener = l
    }

    override fun onFocusChange(v: View?, hasFocus: Boolean) {
        if (hasFocus) {
            var lenght = 0
            val text = text
            if (text!=null){
                lenght = text.length
            }
            setClearIconVisible(lenght > 0);
        } else {
            setClearIconVisible(false);
        }
        if (mOnFocusChangeListener != null) {
            mOnFocusChangeListener!!.onFocusChange(v, hasFocus);
        }
    }

    override fun onTouch(view: View?, motionEvent: MotionEvent?): Boolean {
        if (motionEvent != null) {
            val x :Float = motionEvent.x
            if (mClearTextIcon.isVisible && x > width - paddingRight - mClearTextIcon.intrinsicWidth) {
                if (motionEvent.action === MotionEvent.ACTION_UP) {
                    error = null
                    setText("")
                }
                return true
            }

        }
        return mOnTouchListener != null && mOnTouchListener!!.onTouch(view, motionEvent)
    }

    override fun onTextChanged(text: CharSequence?, start: Int, lengthBefore: Int, lengthAfter: Int) {
        if (isFocused() && text != null) {
            setClearIconVisible(text.length > 0);
        }
    }

    override fun beforeTextChanged(text: CharSequence?, start: Int, lengthBefore: Int, lengthAfter: Int) {

    }

    override fun afterTextChanged(editText: Editable?) {

    }

    fun setClearIconVisible(visible: Boolean) {
        mClearTextIcon.setVisible(visible, false)
        val compoundDrawables = getCompoundDrawables()
        if (visible) {
            setCompoundDrawables( compoundDrawables[0], compoundDrawables[1], mClearTextIcon , compoundDrawables[3]);
        }else {
            setCompoundDrawables( compoundDrawables[0], compoundDrawables[1], null , compoundDrawables[3]);
        }

    }

}