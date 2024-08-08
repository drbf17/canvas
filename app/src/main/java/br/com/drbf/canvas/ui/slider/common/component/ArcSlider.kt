package br.com.drbf.canvas.ui.slider.common.component

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextMeasurer
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import br.com.drbf.canvas.ui.common.utils.figureOutPointFromAngle
import br.com.drbf.canvas.ui.common.utils.percentageToAngle
import br.com.drbf.canvas.ui.common.utils.touchPointTo180Angle

@Composable
fun ArcSlider(
    startPercentage: Float = 0f,
) {

    var progressAngle by remember {
        mutableFloatStateOf(
            startPercentage.percentageToAngle(
                maxAngle = 180f
            )
        )
    }

    val animatedProgressValue by animateFloatAsState(
        targetValue = progressAngle, animationSpec = tween(
            durationMillis = 200, easing = LinearOutSlowInEasing
        ), label = "animatedProgressValue"
    )

    val percentage by remember {
        derivedStateOf {
            val newPercentage = (animatedProgressValue / 180f * 100f)
            if (newPercentage > 99f) {
                100
            } else {
                newPercentage.toInt()

            }
        }
    }

    val textMeasurer = rememberTextMeasurer()
    val textLayoutResult = textMeasurer.measure(text = AnnotatedString(percentage.toString()))
    val textSize = textLayoutResult.size

    Canvas(modifier = Modifier
        .size(300.dp)
        .padding(16.dp)
        .pointerInput(Unit) {
            detectTapGestures { offset ->
                updateProgress(offset, size.width.toFloat()) { newProgress ->
                    progressAngle = newProgress
                }
            }
        }
        .pointerInput(Unit) {
            detectDragGestures(onDrag = { change, _ ->
                updateProgress(
                    change.position, size.width.toFloat()
                ) { newProgress ->
                    progressAngle = newProgress
                }
            })
        }, onDraw = {

        baseArc()
        progressArc(animatedProgressValue)
        progressPoint(animatedProgressValue)
        label(textMeasurer, percentage, textSize)

    })
}


private fun DrawScope.label(
    textMeasurer: TextMeasurer,
    percentage: Int,
    textSize: IntSize
) {
    drawText(
        textMeasurer, percentage.toString(),
        topLeft = Offset(
            (this.size.width - textSize.width) / 2f,
            (this.size.height - textSize.height) / 2f
        ),
    )
}


private fun DrawScope.progressPoint(animatedProgressValue: Float) {
    val radius = size.width / 2

    drawCircle(
        color = Color.Blue,
        radius = 25f,
        center = figureOutPointFromAngle(animatedProgressValue, radius)
    )
}

private fun DrawScope.progressArc(animatedProgressValue: Float) {
    drawArc(
        color = Color.Blue.copy(alpha = 0.5f),
        startAngle = 180f,
        sweepAngle = animatedProgressValue,
        useCenter = false,
        style = Stroke(
            width = 20f, cap = StrokeCap.Round, join = StrokeJoin.Round
        )
    )
}


private fun DrawScope.baseArc() {
    drawArc(
        color = Color.LightGray.copy(alpha = 0.5f),
        startAngle = 180f,
        sweepAngle = 180f,
        useCenter = false,
        style = Stroke(
            width = 20f, cap = StrokeCap.Round, join = StrokeJoin.Round
        )
    )
}

private fun updateProgress(
    offset: Offset, canvasSize: Float, onProgressChanged: (Float) -> Unit
) {
    touchPointTo180Angle(
        width = canvasSize,
        height = canvasSize,
        touchX = offset.x,
        touchY = offset.y,
    )?.let { angle -> onProgressChanged(angle) }
}