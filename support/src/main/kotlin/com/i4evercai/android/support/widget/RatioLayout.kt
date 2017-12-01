package com.i4evercai.android.support.widget


import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import android.widget.RelativeLayout
import com.i4evercai.android.support.R

/**
 *
 * @Description: RatioLayout
 * @author Fitz
 * @email FitzPro@qq.com
 * @date 2017/10/18 16:48
 * @version V1.0
 */
open class RatioLayout : RelativeLayout {
    private var ratio: Float = 0f

    constructor(context: Context) : super(context) {

    }

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0) {

    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        val typedArray: TypedArray = context.obtainStyledAttributes(attrs, R.styleable.RatioLayout, defStyleAttr, 0);
        ratio = typedArray.getFloat(R.styleable.RatioLayout_ratio, -1f);
        typedArray.recycle();
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        // 1.获取宽度
        // 2.根据宽度和比例ratio,计算控件高度
        // 3.重新测量控件

        if (ratio > 0) {
            val width = View.MeasureSpec.getSize(widthMeasureSpec)// 获取宽度值
            val widthMode = View.MeasureSpec.getMode(widthMeasureSpec)// 获取宽度模式

            var height = View.MeasureSpec.getSize(heightMeasureSpec)
            val heightMode = View.MeasureSpec.getMode(heightMeasureSpec)
            // 宽度确定，高度不确定，ratio合法，才计算高度值
            if (widthMode == MeasureSpec.EXACTLY
                    && heightMode != MeasureSpec.EXACTLY) {
                val realWidth = width - paddingLeft - paddingRight// 实际内容宽
                val realHeight = (realWidth / ratio).toInt()
                height = realHeight + getPaddingBottom() + getPaddingTop();
                val newHeightMeasureSpec = MeasureSpec.makeMeasureSpec(height,
                        MeasureSpec.EXACTLY)
                super.onMeasure(widthMeasureSpec, newHeightMeasureSpec)
                return
            }
        }

        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }


    public fun setRatio(ratio: Float) {
        this.ratio = ratio
        invalidate()
    }
}