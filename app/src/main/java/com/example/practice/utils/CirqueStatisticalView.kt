package com.example.practice.utils

import android.content.Context
import android.graphics.Point

import android.graphics.Canvas

import android.util.Log

import android.graphics.RectF

import android.opengl.ETC1.getWidth

import java.util.Stack

import android.graphics.Color

import android.view.View.MeasureSpec

import android.graphics.Paint

import android.util.AttributeSet

import android.graphics.PorterDuff

import android.graphics.PorterDuffXfermode

import android.view.View
import androidx.annotation.Nullable
import kotlin.math.abs
import kotlin.math.cos
import kotlin.math.sin


class CirqueStatisticalView(context: Context?, @Nullable attrs: AttributeSet?, defStyleAttr: Int) :
    View(context, attrs, defStyleAttr) {
//    private var radius = 0
//    private var centerRadius = 0
//    private var total = 0
//    private var discRect: RectF? = null
//    private val mPaint: Paint = Paint()
//    private var items: List<DataItem>? = null
//    private val itemHeight = 40
//    private val margin = 30
//    private val textSize = 30
//    private val textPaint: Paint
//    private var top = 0
//    private val mode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
//
//    constructor(context: Context?, @Nullable attrs: AttributeSet?) : this(context, attrs, 0) {}
//    constructor(context: Context?) : this(context, null) {}
//
//    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
//        val widthSize = MeasureSpec.getSize(widthMeasureSpec)
//        val heightSize = measure(widthSize)
//        radius = widthSize / 4
//        centerRadius = widthSize / 7
//        setMeasuredDimension(widthSize, heightSize)
//    }
//
////    private fun drawCycle(canvas: Canvas) {
////        var startPercent = 0f
////        var sweepPercent = 0f
////        for (i in 0 until strPercent.length) {
////            cyclePaint.setColor(mColor.get(i))
////            startPercent = sweepPercent + startPercent
////            //这里采用比例占100的百分比乘于360的来计算出占用的角度，使用先乘再除可以算出值
////            sweepPercent = strPercent.get(i) * 360 / 100
////            canvas.drawArc(
////                RectF(0, 0, mRadius, mRadius),
////                startPercent,
////                sweepPercent,
////                false,
////                cyclePaint
////            )
////        }
////    }
//
////    private fun measure(width: Int): Int {
////        var top = 0
////        var bottom = 0
////        val angle = FloatArray(items!!.size)
////        val point = arrayOfNulls<Point>(
////            items!!.size
////        )
////        val stack = Stack<Point?>()
////        var endPoint: Point? = null
////        for (i in items!!.indices) {
////            angle[i] = items!![i].getValue() * 1.0f / total * 360.0f
////            point[i] = getPointByAngle((angle[i] / 2 ).plus(if (i > 0) angle[i - 1] else 0) )
////            endPoint = getEndPoint(point[i], endPoint, width)
////            if (i > 0) angle[i] += angle[i - 1]
////            if (point[i]!!.x < 0 && point[i]!!.y > 0 || point[i]!!.x > 0 && point[i]!!.y < 0) {
////                if (!stack.isEmpty() && !isSameQuadrant(stack.peek(), point[i])) {
////                    endPoint = point[i - 1]
////                }
////                stack.push(point[i])
////            } else if (!stack.isEmpty()) {
////                endPoint = getEndPoint(point[i], point[i - 1], width)
////            }
////            top = top.coerceAtMost(endPoint!!.y)
////            bottom = bottom.coerceAtLeast(endPoint.y)
////        }
////        this.top = top
////        return if (2 * top + bottom > 0) {
////            bottom - top * 2
////        } else bottom - top
////    }
//
//    override fun onDraw(canvas: Canvas) {
//        super.onDraw(canvas)
//        mPaint.xfermode = mode
//        drawItem(canvas)
//        drawInnerCircle(canvas)
//        mPaint.xfermode = null
//
//    }
//
//    private fun drawInnerCircle(canvas: Canvas) {
//        mPaint.color = Color.WHITE
//        canvas.drawCircle(0f, 0f, centerRadius.toFloat(), mPaint)
//    }
//
//
//    private fun isSameQuadrant(point: Point?, prePoint: Point?): Boolean {
//        return point!!.x * prePoint!!.x >= 0 && point.y * prePoint.y >= 0
//    }
//
//
//    private fun drawItem(canvas: Canvas) {
//        var start = 0f
//        var need = 0f
//        val heightCenter = radius - top / 2
//        canvas.translate((width / 2).toFloat(), (heightCenter + (abs(top) shr 3)).toFloat())
//        discRect = RectF(
//            (-radius).toFloat(), (-radius).toFloat(), radius.toFloat(),
//            radius.toFloat()
//        )
//        for (item in items) {
//            mPaint.color = item.getColor()
//            mPaint.strokeWidth = 20f
//            start += need
//            need = item.getValue() * 1.0f / total * 360.0f
//            canvas.drawArc(discRect!!, start, need, true, mPaint)
//        }
//    }
//
//
//    fun setItems(items: List<DataItem>) {
//        this.items = items
//        total = 0
//        for (item in items) {
//            total += item.getValue()
//        }
//        invalidate()
//    }
//
//    init {
//        mPaint.isAntiAlias = true
//        textPaint = Paint()
//        textPaint.isAntiAlias = true
//        textPaint.color = Color.parseColor("#8f8e8e")
//        textPaint.textSize = textSize.toFloat()
//    }
}