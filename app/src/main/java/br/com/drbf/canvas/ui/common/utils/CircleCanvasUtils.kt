package br.com.drbf.canvas.ui.common.utils

import androidx.compose.ui.geometry.Offset
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.sin


private const val circleDegrees = 360f
private const val semiCircleDegrees = 180f

internal fun touchPointTo360Angle(
    width: Float,
    height: Float,
    touchX: Float,
    touchY: Float,
): Float {
    val angle = figureOutAngle(width, height, touchX, touchY)
    return if (angle in (circleDegrees * -1)..0f) angle + circleDegrees else angle
}

internal fun touchPointTo180Angle(
    width: Float,
    height: Float,
    touchX: Float,
    touchY: Float,
): Float? {
    val angle = figureOutAngle(width, height, touchX, touchY)
    return if (angle in (semiCircleDegrees * -1)..0f) angle + semiCircleDegrees else null
}

private fun figureOutAngle(
    width: Float,
    height: Float,
    touchX: Float,
    touchY: Float
): Float {
    val centerX = width / 2
    val centerY = height / 2
    val x = touchX - centerX
    val y = touchY - centerY
    return Math.toDegrees(atan2(y.toDouble(), x.toDouble())).toFloat()
}

fun figureOutPointFromAngle(
    angle: Float,
    radius: Float
): Offset {
    return Offset(
        radius - figureOutXfromAngle(angle, radius),
        radius - figureOutYfromAngle(angle, radius)
    )
}

fun figureOutXfromAngle(
    angle: Float,
    radius: Float
): Float {
    return radius * cos(Math.PI * 2 * angle / 360).toFloat()
}

fun figureOutYfromAngle(
    angle: Float,
    radius: Float
): Float {
    return radius * sin(Math.PI * 2 * angle / 360).toFloat()
}

fun Float.percentageToAngle(
    maxAngle: Float = 360f
): Float {
    return this / 100 * maxAngle
}
