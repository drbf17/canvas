package br.com.drbf.canvas.ui.chart.common.components.bar

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.times
import kotlin.math.roundToLong


private const val animationDuration = 2 * 1000

@Composable
fun HorizontalBarComponent(
    modifier: Modifier = Modifier,
    bars: List<BarModel>,
    maxValue: Float = 100f,
    barColor: Color = Color.Blue,
    labelColor: Color = Color.White,
    animated: Boolean = true
) {

    val progression = remember { Animatable(initialValue = 0f) }
    val density = LocalDensity.current
    val barWidthDp = 24.dp
    val barWidth = density.run { barWidthDp.toPx() }
    val spaceWidthDp = 4.dp
    val spaceWidth = density.run { spaceWidthDp.toPx() }
    val heightDp = (bars.size) * (barWidthDp + spaceWidthDp)
    var canvasWidth by remember { mutableFloatStateOf(0f) }
    val maxBarLength by remember {
        derivedStateOf {
            canvasWidth * 2 / 3
        }
    }
    val textMeasurer = rememberTextMeasurer()
    val labelStyle = TextStyle(
        fontSize = 14.sp,
        color = labelColor,
    )

    val valueStyle = TextStyle(
        fontSize = 14.sp,
        fontWeight = FontWeight.Bold,
        color = Color.Black,
    )
    val textPadding = density.run { 4.dp.toPx() }


    LaunchedEffect(bars) {
        progression.animateTo(
            targetValue = 1f,
            animationSpec = tween(if (animated) animationDuration else 0)
        )
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {

        Canvas(
            modifier = modifier
                .fillMaxWidth()
                .height(heightDp)
                .onGloballyPositioned {
                    canvasWidth = it.size.width.toFloat()
                }
        ) {

            bars.forEachIndexed { index, bar ->

                val y = (barWidth + spaceWidth) * index

                drawRect(
                    color = barColor.copy(alpha = 0.5f),
                    topLeft = Offset(
                        x = 0f,
                        y = y,
                    ),
                    size = Size(
                        width = maxBarLength,
                        height = barWidth
                    )
                )

                val x = (bar.value / maxValue) * maxBarLength * progression.value

                drawRect(
                    color = barColor,
                    topLeft = Offset(
                        x = 0f,
                        y = y,
                    ),
                    size = Size(
                        width = x,
                        height = barWidth
                    )
                )
                drawText(
                    textMeasurer = textMeasurer,
                    text = bar.label,
                    style = labelStyle,
                    topLeft = Offset(
                        0f + textPadding,
                        y = y + textPadding,
                    )
                )

                drawText(
                    textMeasurer = textMeasurer,
                    text = "US$ ${"%.2f".format((bar.value * progression.value))}",
                    style = valueStyle,
                    topLeft = Offset(
                        maxBarLength + textPadding,
                        y = y + textPadding,
                    )
                )

            }
        }

    }
}