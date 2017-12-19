package com.i4evercai.android.support.widget.RecyclerView

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.support.annotation.DrawableRes
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View

/**
 *
 * @Description: RecycleViewDivider
 * @author Fitz
 * @email FitzPro@qq.com
 * @date 2017/8/1 16:25
 * @version V1.0
 */
class RecycleViewLinearDivider constructor(context: Context, orientation: Int) : RecyclerView.ItemDecoration() {

    private var mPaint: Paint? = null
    private var mDivider: Drawable
    private var mDividerHeight = 1//分割线高度，默认为1px
    private val mOrientation: Int //列表的方向：LinearLayoutManager.VERTICAL或LinearLayoutManager.HORIZONTAL
    private val ATTRS = intArrayOf(android.R.attr.listDivider)

    init {
        if (orientation != LinearLayoutManager.VERTICAL && orientation != LinearLayoutManager.HORIZONTAL) {
            throw IllegalArgumentException("请输入正确的参数！");
        }
        mOrientation = orientation;
        val a: TypedArray = context.obtainStyledAttributes(ATTRS);
        mDivider = a.getDrawable(0);
        a.recycle();
    }

    constructor(context: Context, orientation: Int, @DrawableRes drawableId: Int) : this(context, orientation) {
        mDivider = ContextCompat.getDrawable(context, drawableId);
        mDividerHeight = mDivider.getIntrinsicHeight();
    }

    constructor(context: Context, orientation: Int, dividerHeight: Int, dividerColor: Int) : this(context, orientation) {
        mDividerHeight = dividerHeight;
        mPaint = Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint!!.setColor(dividerColor);
        mPaint!!.setStyle(Paint.Style.FILL);
    }

    override fun getItemOffsets(outRect: Rect?, view: View?, parent: RecyclerView?, state: RecyclerView.State?) {
        outRect?.set(0, 0, 0, mDividerHeight);
    }

    override fun onDraw(c: Canvas?, parent: RecyclerView?, state: RecyclerView.State?) {
        super.onDraw(c, parent, state)
        if (c!=null && parent!=null){
            if (mOrientation == LinearLayoutManager.VERTICAL) {
                drawVertical(c, parent);
            } else {
                drawHorizontal(c, parent);
            }
        }

    }

    //绘制横向 item 分割线
    private fun drawVertical(canvas: Canvas, parent: RecyclerView) {
        val left = parent.paddingLeft
        val right = parent.measuredWidth - parent.paddingRight
        val childSize = parent.childCount - 1
        for (i in 0..childSize - 1) {
            val child = parent.getChildAt(i)
            val layoutParams = child.layoutParams as RecyclerView.LayoutParams
            val top = child.bottom + layoutParams.bottomMargin
            val bottom = top + mDividerHeight
            if (mDivider != null) {
                mDivider.setBounds(left, top, right, bottom)
                mDivider.draw(canvas)
            }
            if (mPaint != null) {
                canvas.drawRect(left.toFloat(), top.toFloat(), right.toFloat(), bottom.toFloat(), mPaint)
            }
        }
    }

    //绘制纵向 item 分割线
    private fun drawHorizontal(canvas: Canvas, parent: RecyclerView) {
        val top = parent.paddingTop
        val bottom = parent.measuredHeight - parent.paddingBottom
        val childSize = parent.childCount - 1
        for (i in 0..childSize - 1) {
            val child = parent.getChildAt(i)
            val layoutParams = child.layoutParams as RecyclerView.LayoutParams
            val left = child.right + layoutParams.rightMargin
            val right = left + mDividerHeight
            if (mDivider != null) {
                mDivider.setBounds(left, top, right, bottom)
                mDivider.draw(canvas)
            }
            if (mPaint != null) {
                canvas.drawRect(left.toFloat(), top.toFloat(), right.toFloat(), bottom.toFloat(), mPaint)
            }
        }
    }
}