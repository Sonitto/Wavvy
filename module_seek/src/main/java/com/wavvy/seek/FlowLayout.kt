package com.wavvy.seek

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup

/*
 * @Module     : com.wavvy.seek
 * @FileName   : FlowLayout
 * @Author     : lsj
 * @CreateTime : 2026/7/15 16:59
 * @Desc       : 搜索记录
 */
class FlowLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet?=null,
    defStyleAttr: Int=0
) : ViewGroup(context,attrs,defStyleAttr){
    private val horizontalGap=24
    private val verticalGap=16
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val paddingLeft=paddingLeft
        val paddingRight=paddingRight
        val paddingTop = paddingTop
        val paddingBottom = paddingBottom
        val maxLineWidth= MeasureSpec.getSize(widthMeasureSpec)-paddingLeft-paddingRight
        var lineWidth=0
        var lineMaxHeight=0
        var totalHeight=paddingTop
        for (i in 0 until childCount){
            val child=getChildAt(i)
            measureChildWithMargins(child,widthMeasureSpec,0,heightMeasureSpec,0)
            val w=child.measuredWidth
            val h=child.measuredHeight
            val lp=child.layoutParams as MarginLayoutParams
            val childFullWidth = w + lp.leftMargin + lp.rightMargin
            if (lineWidth+childFullWidth>maxLineWidth){
                totalHeight+=lineWidth+verticalGap
                lineWidth=0
                lineMaxHeight=0
            }
            lineWidth+=childFullWidth+horizontalGap
            lineMaxHeight=maxOf(lineMaxHeight, h +lp.topMargin+lp.bottomMargin)
        }
        totalHeight += lineMaxHeight + paddingBottom
        setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec), resolveSize(totalHeight, heightMeasureSpec))
    }
    override fun onLayout(
        changed: Boolean,
        l: Int,
        t: Int,
        r: Int,
        b: Int
    ) {
        val paddingLeft=paddingLeft
        val maxLineWidth=r-l-paddingLeft-paddingRight

        var lineX=paddingLeft
        var lineY=paddingTop
        var lineMaxH=0
        for(i in 0 until childCount){
            val child=getChildAt(i)
            val w=child.measuredWidth
            val h=child.measuredHeight
            val lp=child.layoutParams as MarginLayoutParams
            val fullW=w+lp.leftMargin+lp.rightMargin
            if (lineX+fullW>maxLineWidth){
                lineX=paddingLeft
                lineY+=lineMaxH+verticalGap
                lineMaxH=0
            }
            child.layout(left,top,right,bottom)
            lineX+=fullW+horizontalGap
            lineMaxH= maxOf(lineMaxH,h+lp.topMargin+lp.bottomMargin)
            }
        }
    override fun generateLayoutParams(attrs: AttributeSet): MarginLayoutParams {
        return MarginLayoutParams(context, attrs)
    }
    override fun generateDefaultLayoutParams(): MarginLayoutParams {
        return MarginLayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
    }

}