package com.example.practice.utils

import android.R.attr
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
import android.R.attr.textColor
import com.example.practice.R
import com.example.practice.bean.CircleDataBean
import com.example.practice.data.CircleData


class CirqueStatisticalView(context: Context?, @Nullable attrs: AttributeSet?, defStyleAttr: Int) :
    View(context, attrs, defStyleAttr) {
    private var radius = 0
    private var centerRadius = 0
    private var total = 0
    private var discRect: RectF? = null
    private val mPaint: Paint = Paint()
    private var items: List<CircleDataBean>? = null
    private val itemHeight = 40
    private val margin = 30

    private val mode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)

    private val textColor = 0xFF000000

    //-------------View相关-------------
    //View自身的宽和高
    private var mHeight = 0
    private var mWidth = 0
    //圆的直径
    private val mRadius = 600f

    //圆的粗细
    private val mStrokeWidth = 80f

    //文字大小
    private val textSize = 40
    //-------------画笔相关-------------
    //圆环的画笔
    private var cyclePaint: Paint? = null

    //文字的画笔
    private var textPaint: Paint? = null

    //标注的画笔
    private var labelPaint: Paint? = null

    constructor(context: Context?, @Nullable attrs: AttributeSet?) : this(context, attrs, 0) {}
    constructor(context: Context?) : this(context, null) {}

//    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
//        val widthSize = MeasureSpec.getSize(widthMeasureSpec)
//        val heightSize = measure(widthSize)
//        radius = widthSize / 4
//        centerRadius = widthSize / 7
//        setMeasuredDimension(widthSize, heightSize)
//    }
    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        mWidth = w
        mHeight = h
    }
//    private fun drawCycle(canvas: Canvas) {
//        var startPercent = 0f
//        var sweepPercent = 0f
//        for (i in 0 until strPercent.length) {
//            cyclePaint.setColor(mColor.get(i))
//            startPercent = sweepPercent + startPercent
//            //这里采用比例占100的百分比乘于360的来计算出占用的角度，使用先乘再除可以算出值
//            sweepPercent = strPercent.get(i) * 360 / 100
//            canvas.drawArc(
//                RectF(0, 0, mRadius, mRadius),
//                startPercent,
//                sweepPercent,
//                false,
//                cyclePaint
//            )
//        }
//    }

//    private fun measure(width: Int): Int {
//        var top = 0
//        var bottom = 0
//        val angle = FloatArray(items!!.size)
//        val point = arrayOfNulls<Point>(
//            items!!.size
//        )
//        val stack = Stack<Point?>()
//        var endPoint: Point? = null
//        for (i in items!!.indices) {
//            angle[i] = items!![i].getValue() * 1.0f / total * 360.0f
//            point[i] = getPointByAngle((angle[i] / 2 ).plus(if (i > 0) angle[i - 1] else 0) )
//            endPoint = getEndPoint(point[i], endPoint, width)
//            if (i > 0) angle[i] += angle[i - 1]
//            if (point[i]!!.x < 0 && point[i]!!.y > 0 || point[i]!!.x > 0 && point[i]!!.y < 0) {
//                if (!stack.isEmpty() && !isSameQuadrant(stack.peek(), point[i])) {
//                    endPoint = point[i - 1]
//                }
//                stack.push(point[i])
//            } else if (!stack.isEmpty()) {
//                endPoint = getEndPoint(point[i], point[i - 1], width)
//            }
//            top = top.coerceAtMost(endPoint!!.y)
//            bottom = bottom.coerceAtLeast(endPoint.y)
//        }
//        this.top = top
//        return if (2 * top + bottom > 0) {
//            bottom - top * 2
//        } else bottom - top
//    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.translate(mWidth / 2 - mRadius / 2, mHeight / 2 - mRadius / 2);
        //初始化画笔
        initPaint();
        //画圆环
        drawCycle(canvas);
        //画文字和标注
        drawTextAndLabel(canvas);

    }
    private fun initPaint() {
        //边框画笔
        cyclePaint = Paint()
        cyclePaint!!.isAntiAlias = true
        cyclePaint!!.style = Paint.Style.STROKE
        cyclePaint!!.strokeWidth = mStrokeWidth
        //文字画笔
        textPaint = Paint()
        textPaint!!.isAntiAlias = true
        textPaint!!.color = textColor.toInt()
        textPaint!!.style = Paint.Style.STROKE
        textPaint!!.strokeWidth = 1f
        textPaint!!.textSize = textSize.toFloat()
        //标注画笔
        labelPaint = Paint()
        labelPaint!!.isAntiAlias = true
        labelPaint!!.style = Paint.Style.FILL
        labelPaint!!.strokeWidth = 2F
    }

    private fun drawCycle(canvas: Canvas) {
        var startPercent = 0f
        var sweepPercent = 0f
        for (item in items!!) {
            cyclePaint!!.color = item.color
            startPercent += sweepPercent
            //这里采用比例占100的百分比乘于360的来计算出占用的角度，使用先乘再除可以算出值
            sweepPercent = (item.value * 360 / 100).toFloat()
            canvas.drawArc(
                RectF(0F, 0F, mRadius, mRadius), startPercent, sweepPercent, false,
                cyclePaint!!
            )
        }
        if(total < 100){
            cyclePaint!!.color = 0xFF888888.toInt()
            startPercent += sweepPercent
            sweepPercent = ((101-total) * 360 / 100).toFloat()

            canvas.drawArc(
                RectF(0F, 0F, mRadius, mRadius), startPercent, sweepPercent, false,
                cyclePaint!!
            )
        }
    }
    private fun drawInnerCircle(canvas: Canvas) {
        mPaint.color = Color.WHITE
        canvas.drawCircle(0f, 0f, centerRadius.toFloat(), mPaint)
    }



    private fun drawItem(canvas: Canvas) {
        var start = 0f
        var need = 0f
        val heightCenter = radius - top / 2
        canvas.translate((width / 2).toFloat(), (heightCenter + (abs(top) shr 3)).toFloat())
        discRect = RectF(
            (-radius).toFloat(), (-radius).toFloat(), radius.toFloat(),
            radius.toFloat()
        )
        for (item in items!!) {
            mPaint.color = item.color
            mPaint.strokeWidth = 20f
            start += need
            need = item.value * 1.0f / total * 360.0f
            canvas.drawArc(discRect!!, start, need, true, mPaint)
        }
    }
    private fun drawTextAndLabel(canvas: Canvas) {
        var start = 0f
        var need = 0f
        for (item in items!!)  {
            start++
            //文字离右边环边距为60，文字与文字之间的距离为40
            item.contentText
                ?.let { canvas.drawText(it, (start*40+ 100),  mRadius+mStrokeWidth+20f, textPaint!!) }
            //画标注,标注离右边环边距为40,y轴则要减去半径（10）的一半才能对齐文字
            labelPaint!!.color = item.color
            canvas.drawCircle(mRadius + 40, (start * 40 - 5), 10f, labelPaint!!)
        }

    }

    fun setItems(items: List<CircleDataBean>) {
        this.items = items
        total = 0
        for (item in items) {
            total += item.value
        }
        invalidate()
    }

//    init {
//        mPaint.isAntiAlias = true
//        textPaint = Paint()
//        textPaint!!.isAntiAlias = true
//        textPaint!!.color = Color.parseColor("#8f8e8e")
//        textPaint!!.textSize = textSize.toFloat()
//    }
}