package com.wavvy.seek.view

/*
 * @Module     : com.wavvy.seek
 * @FileName   : FlowLayout
 * @Author     : lsj
 * @CreateTime : 2026/7/15 16:59
 * @Desc       : 搜索记录
 */
import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup

class FlowLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ViewGroup(context, attrs, defStyleAttr) {

    private val horizontalGap = 24
    private val verticalGap = 16

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val paddingLeft = paddingLeft
        val paddingRight = paddingRight
        val paddingTop = paddingTop
        val paddingBottom = paddingBottom

        val widthSize = MeasureSpec.getSize(widthMeasureSpec)
        val maxLineWidth = widthSize - paddingLeft - paddingRight

        var lineWidth = 0
        var lineMaxHeight = 0
        var totalHeight = paddingTop
        var maxRowWidth = 0 // 用于记录最宽的那一行，用来支持 wrap_content 宽度

        val childCount = childCount
        for (i in 0 until childCount) {
            val child = getChildAt(i)
            // 1. 过滤掉不显示的控件
            if (child.visibility == View.GONE) continue

            measureChildWithMargins(child, widthMeasureSpec, 0, heightMeasureSpec, 0)

            val w = child.measuredWidth
            val h = child.measuredHeight
            val lp = child.layoutParams as MarginLayoutParams
            val childFullWidth = w + lp.leftMargin + lp.rightMargin

            // 2. 换行判定（确保当前行已有子 View，且加上新 View 超过最大宽度）
            if (lineWidth > 0 && lineWidth + childFullWidth > maxLineWidth) {
                totalHeight += lineMaxHeight + verticalGap // 修复：累加高度而非宽度
                maxRowWidth = maxOf(maxRowWidth, lineWidth - horizontalGap) // 记录最大行宽
                lineWidth = 0
                lineMaxHeight = 0
            }

            lineWidth += childFullWidth + horizontalGap
            lineMaxHeight = maxOf(lineMaxHeight, h + lp.topMargin + lp.bottomMargin)
        }

        // 3. 处理最后一行数据
        maxRowWidth = maxOf(maxRowWidth, lineWidth - horizontalGap)
        totalHeight += lineMaxHeight + paddingBottom

        // 4. 使用 resolveSize 兼容 wrap_content 属性
        val finalWidth = resolveSize(maxRowWidth + paddingLeft + paddingRight, widthMeasureSpec)
        val finalHeight = resolveSize(totalHeight, heightMeasureSpec)

        setMeasuredDimension(finalWidth, finalHeight)
    }

    override fun onLayout(
        changed: Boolean,
        l: Int,
        t: Int,
        r: Int,
        b: Int
    ) {
        val paddingLeft = paddingLeft
        val maxLineWidth = r - l - paddingLeft - paddingRight

        var lineX = paddingLeft
        var lineY = paddingTop
        var lineMaxH = 0

        for (i in 0 until childCount) {
            val child = getChildAt(i)
            // 1. 过滤掉不显示的控件
            if (child.visibility == View.GONE) continue

            val w = child.measuredWidth
            val h = child.measuredHeight
            val lp = child.layoutParams as MarginLayoutParams
            val fullW = w + lp.leftMargin + lp.rightMargin

            // 2. 换行判定
            if (lineX > paddingLeft && lineX + fullW > maxLineWidth) {
                lineX = paddingLeft
                lineY += lineMaxH + verticalGap
                lineMaxH = 0
            }

            // 3. 修复：计算子 View 自己的具体坐标并正确布局
            val childLeft = lineX + lp.leftMargin
            val childTop = lineY + lp.topMargin
            val childRight = childLeft + w
            val childBottom = childTop + h
            child.layout(childLeft, childTop, childRight, childBottom)

            lineX += fullW + horizontalGap
            lineMaxH = maxOf(lineMaxH, h + lp.topMargin + lp.bottomMargin)
        }
    }

    override fun generateLayoutParams(attrs: AttributeSet): MarginLayoutParams {
        return MarginLayoutParams(context, attrs)
    }

    override fun generateDefaultLayoutParams(): MarginLayoutParams {
        return MarginLayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
    }

    // 4. 修复：重写该方法，防止代码中动态 addView 时抛出 ClassCastException 异常
    override fun generateLayoutParams(p: LayoutParams): LayoutParams {
        return MarginLayoutParams(p)
    }
}