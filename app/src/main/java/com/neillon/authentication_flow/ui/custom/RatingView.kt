package com.neillon.authentication_flow.ui.custom

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.os.Build
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import com.neillon.authentication_flow.R
import kotlin.math.min
import kotlin.math.round


class RatingView(context: Context, attributeSet: AttributeSet) : View(context, attributeSet) {
    private var rating =
        INITIAL_VALUE
    private var angle = 0

    private val circlePaint = Paint().apply {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            color = context.getColor(R.color.colorPrimary)
        }
        flags = Paint.ANTI_ALIAS_FLAG
    }

    private val textPaint = Paint().apply {
        color = Color.WHITE
        textSize = 16f.sp
        flags = Paint.ANTI_ALIAS_FLAG
    }

    private val progressPaint = Paint().apply {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            color = context.getColor(R.color.colorPrimaryVariant)
        }
        flags = Paint.ANTI_ALIAS_FLAG
    }

    private val Float.sp
        get() = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, this, resources.displayMetrics)

    fun setRating(value: Float) {
        val newRating = if (value > MAX_VALUE) MAX_VALUE
                        else value
        ValueAnimator.ofFloat(INITIAL_VALUE, newRating)
            .apply {
                duration = 1000L
                addUpdateListener { updateCurrentRatingAndAngle(it.animatedValue as Float) }
            }?.start()
    }

    private fun updateCurrentRatingAndAngle(value: Float) {
        rating = value.toDouble().round(1).toFloat()
        angle = calculateCurrentAngle(rating)

        invalidate()
    }

    private fun Double.round(decimals: Int): Double {
        var multiplier = 1.0
        repeat(decimals) { multiplier *= 10 }
        return round(this * multiplier) / multiplier
    }

    private fun calculateCurrentAngle(value: Float): Int {
        return ((value * 360) / 10).toInt()
    }

    override fun onDraw(canvas: Canvas?) {
        val (xCordinate, yCordinate) = arrayOf(
            (width / 2).toFloat(),
            (height / 2).toFloat()
        )
        val radius = min(xCordinate, yCordinate)

        canvas?.apply {
            super.onDraw(canvas)

            // draw base circle
            drawCircle(xCordinate, yCordinate, radius, circlePaint)

            // Draw custom progress
            drawArc(
                RectF(xCordinate - radius, yCordinate - radius, xCordinate + radius, yCordinate + radius),
                270f,
                angle.toFloat(),
                true,
                progressPaint
            )

            // Draw center circle
            drawCircle(xCordinate, yCordinate, radius - 8, circlePaint)

            // draw text on center
            val text = rating.toString()
            val (textX, texY) = calculateCenterTextPosition(text, xCordinate, yCordinate)
            drawText(rating.toString(), textX, texY, textPaint)
        }
    }

    private fun calculateCenterTextPosition(
        text: String,
        xCordinate: Float,
        yCordinate: Float
    ): Array<Float> {
        val bounds = Rect()
        textPaint.getTextBounds(text, 0, text.length, bounds)
        val yOffset = (yCordinate + (bounds.height().toFloat() / 2))

        val textWidth: Float = textPaint.measureText(text)
        val xOffset = (xCordinate - (textWidth) / 2)

        return arrayOf(xOffset, yOffset)
    }

    companion object {
        const val TAG = "RatingView"
        const val INITIAL_VALUE = 0f
        const val MAX_VALUE = 10f
    }
}
