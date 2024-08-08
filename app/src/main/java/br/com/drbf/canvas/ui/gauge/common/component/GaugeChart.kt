package br.com.drbf.canvas.ui.gauge.common.component

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.rotate
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import br.com.drbf.canvas.ui.common.utils.percentageToAngle
import kotlin.math.min

private const val animationDuration = 800

@Composable
fun GaugeChart(
    modifier: Modifier = Modifier,
    value: Float,
    minValue: Float = 0f,
    maxValue: Float = 100f,
    needleColor: Color,
    animated: Boolean = true
) {

    assert(value in minValue..maxValue) { "percent must be between $minValue - $maxValue" }
    val percent = value / (maxValue - minValue) * 100f

    val progress = remember { Animatable(initialValue = 0f) }
    LaunchedEffect(percent) {
        progress.animateTo(
            targetValue = percent,
            animationSpec = tween(if (animated) animationDuration else 0)
        )
    }

    val density = LocalDensity.current
    val needleBaseSize = density.run { 1.dp.toPx() }
    val arcStrokeWidth = density.run { 6.dp.toPx() }
    val textFontSize = density.run { 16.dp.toPx() }
    val fontPadding = density.run { 5.dp.toPx() }

    val arcMaxDegrees = 180
    val arcStartAngle = 180f

    val needlePaint = remember { Paint().apply { color = needleColor } }
    val textPaint = remember {
        android.graphics.Paint().apply {
            color = needleColor.toArgb()
            textSize = textFontSize
            textAlign = android.graphics.Paint.Align.CENTER
        }
    }

    val arcStrokeBrush = Brush.horizontalGradient(
        0.3f to Color.Green,
        0.6f to Color.Yellow,
        0.9f to Color.Red,
    )

    BoxWithConstraints(modifier = modifier.padding(16.dp), contentAlignment = Alignment.Center) {

        val canvasSize = min(constraints.maxWidth, constraints.maxHeight)
        val canvasSizeDp = density.run { canvasSize.toDp() }
        val size = Size(canvasSize.toFloat(), canvasSize.toFloat())
        val width = size.width
        val height = size.height
        val center = Offset(width / 2, height / 2)
        val textY = center.y + textFontSize + fontPadding

        Canvas(
            modifier = Modifier.size(canvasSizeDp),
            onDraw = {

                drawArc(arcStrokeBrush, arcStartAngle, arcMaxDegrees, size, arcStrokeWidth)
                drawNeedle(
                    progress,
                    arcMaxDegrees,
                    center,
                    needleBaseSize,
                    width,
                    needlePaint,
                    percent,
                    textY,
                    textPaint
                )
            }
        )
    }

}


private fun DrawScope.drawNeedle(
    progress: Animatable<Float, AnimationVector1D>,
    arcMaxDegrees: Int,
    center: Offset,
    needleBaseSize: Float,
    width: Float,
    needlePaint: Paint,
    percent: Float,
    textY: Float,
    textPaint: android.graphics.Paint
) {
    drawIntoCanvas { canvas ->


        canvas.save()

        // rotate canvas based on progress, to move the needle
        canvas.rotate(
            degrees = progress.value.percentageToAngle(maxAngle = arcMaxDegrees.toFloat()),
            pivotX = center.x,
            pivotY = center.y
        )

        //draw Needle shape
        canvas.drawPath(
            Path().apply {
                moveTo(center.x, center.x)
                lineTo(center.x, center.y + needleBaseSize)
                lineTo(width / 6, center.y)
                lineTo(center.x, center.y - 5)
                close()
            },
            needlePaint
        )

        canvas.restore()


        drawNeedleLabel(canvas, percent, center, textY, textPaint)

    }
}

private fun drawNeedleLabel(
    canvas: Canvas,
    percent: Float,
    center: Offset,
    textY: Float,
    textPaint: android.graphics.Paint
) {
    canvas.nativeCanvas.drawText(
        percent.toInt().toString(),
        center.x,
        textY,
        textPaint
    )
}


private fun DrawScope.drawArc(
    brush: Brush,
    startAngle: Float,
    gaugeDegrees: Int,
    size: Size,
    strokeWidth: Float
) {
    drawArc(
        brush = brush,
        startAngle = startAngle,
        sweepAngle = gaugeDegrees.toFloat(),
        useCenter = false,
        size = size,
        style = Stroke(width = strokeWidth, cap = StrokeCap.Round)
    )
}