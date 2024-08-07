package br.com.drbf.canvas.ui.chart.common.utils

import android.util.Log
import kotlin.math.atan2


private const val circleDegrees = 360f

internal fun touchPointToAngle(
    width: Float,
    height: Float,
    touchX: Float,
    touchY: Float,
): Double {

    val x = touchX - (width * 0.5f)
    val y = touchY - (height * 0.5f)
    var angle = Math.toDegrees(atan2(y.toDouble(), x.toDouble()) + Math.PI / 2)
    Log.d("PieChart", "angle: $angle")
    angle = if (angle < 0) angle + 360f else angle
    angle = if (angle <= 90) angle + 270 else angle - 90
    return angle
}
