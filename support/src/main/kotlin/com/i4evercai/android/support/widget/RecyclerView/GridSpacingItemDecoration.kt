package com.i4evercai.android.support.widget.RecyclerView

import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View
/**
 *
 * @Description: GridSpacingItemDecoration
 * @author Fitz
 * @email FitzPro@qq.com
 * @date 2017/12/2 11:25
 * @version V1.0
 */
class GridSpacingItemDecoration : RecyclerView.ItemDecoration {
    private val spanCount: Int
    private val spacing: Int
    private val includeEdge: Boolean
    private val headerNum: Int

    constructor(spanCount: Int, spacing: Int, includeEdge: Boolean, headerNum: Int) : super() {
        this.spanCount = spanCount
        this.spacing = spacing
        this.includeEdge = includeEdge
        this.headerNum = headerNum

    }

    override fun getItemOffsets(outRect: Rect?, view: View?, parent: RecyclerView?, state: RecyclerView.State?) {
        if (parent != null && outRect != null) {
            val position = parent.getChildAdapterPosition(view) - headerNum // item position

            if (position >= 0) {
                val column = position % spanCount // item column

                if (includeEdge) {
                    outRect.left = spacing - column * spacing / spanCount // spacing - column * ((1f / spanCount) * spacing)
                    outRect.right = (column + 1) * spacing / spanCount // (column + 1) * ((1f / spanCount) * spacing)

                    if (position < spanCount) { // top edge
                        outRect.top = spacing
                    }
                    outRect.bottom = spacing // item bottom
                } else {
                    outRect.left = column * spacing / spanCount // column * ((1f / spanCount) * spacing)
                    outRect.right = spacing - (column + 1) * spacing / spanCount // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                    if (position >= spanCount) {
                        outRect.top = spacing // item top
                    }
                }
            } else {
                outRect.left = 0
                outRect.right = 0
                outRect.top = 0
                outRect.bottom = 0
            }
        }

    }
}