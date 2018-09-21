package com.i4evercai.android.support.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Path
import android.util.AttributeSet

class CornerImageView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0
) : AppImageView(context, attrs, defStyleAttr) {

    private var topLeftRadius = 0f
    private var topRightRadius = 0f
    private var bottomLeftRadius = 0f
    private var bottomRightRadius = 0f


    init {
        val ATTRS = intArrayOf(android.R.attr.radius,
                android.R.attr.topLeftRadius, android.R.attr.topRightRadius,
                android.R.attr.bottomLeftRadius, android.R.attr.bottomRightRadius)

        context.obtainStyledAttributes(attrs, ATTRS).apply {
            try {
                val radius = getDimensionPixelSize(0, 0).toFloat()
                @SuppressWarnings("ResourceType")
                if (radius > 0) {
                    topLeftRadius = radius
                    topRightRadius = radius
                    bottomRightRadius = radius
                    bottomLeftRadius = radius
                } else {
                    topLeftRadius = getDimensionPixelSize(1, 0).toFloat()
                    topRightRadius = getDimensionPixelSize(2, 0).toFloat()
                    bottomLeftRadius = getDimensionPixelSize(3, 0).toFloat()
                    bottomRightRadius = getDimensionPixelSize(4, 0).toFloat()
                }

            } catch (e: Exception) {
            }
            recycle()
        }
    }

    override fun onDraw(canvas: Canvas?) {
        if (canvas!=null){
            //这里做下判断，只有图片的宽高大于设置的圆角距离的时候才进行裁剪
            val maxLeft = Math.max(topLeftRadius, bottomLeftRadius);
            val maxRight = Math.max(topRightRadius, bottomRightRadius);
            val minWidth = maxLeft + maxRight;
            val maxTop = Math.max(topLeftRadius, topRightRadius);
            val maxBottom = Math.max(bottomLeftRadius, bottomRightRadius);
            val minHeight = maxTop + maxBottom;
            val width = width.toFloat()
            val height = height.toFloat()
            if (width >= minWidth && height > minHeight) {
                val path = Path();
                //四个角：右上，右下，左下，左上
                path.moveTo(topLeftRadius, 0f);
                path.lineTo(width - topRightRadius, 0f);
                path.quadTo(width, 0f, width, topRightRadius);

                path.lineTo(width, height - bottomRightRadius);
                path.quadTo(width, height, width - bottomRightRadius, height);

                path.lineTo(bottomLeftRadius, height);
                path.quadTo(0f, height, 0f, height - bottomLeftRadius);

                path.lineTo(0f, topLeftRadius);
                path.quadTo(0f, 0f, topLeftRadius, 0f);

                canvas.clipPath(path);
            }
        }



        super.onDraw(canvas)
    }
}