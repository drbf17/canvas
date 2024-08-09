package br.com.drbf.canvas.ui.chart.common.components.line

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import br.com.drbf.canvas.ui.theme.CanvasTheme

private const val animationDuration = 2 * 1000

@Composable
fun LineChartComponent(
    modifier: Modifier,
    lineChartModel: LineChartModel,
    lineStrokeWidthDp: Dp = 1.dp,
    pointRadiusDp: Dp = 1.dp,
    lineColor: Color = Color.Magenta,
    pointColor: Color = Color.Blue,
    animated: Boolean = true
) {

    val density = LocalDensity.current
    val lineStrokeWidth = density.run { lineStrokeWidthDp.toPx() }
    val pointRadius = density.run { pointRadiusDp.toPx() }

    val lineIndex = remember { Animatable(initialValue = 0f) }
    val points : List<LineChartPoint> by remember {
        derivedStateOf {
            lineChartModel.points.take(lineIndex.value.toInt())
        }
    }

    LaunchedEffect(lineChartModel) {
        lineIndex.animateTo(
            targetValue = lineChartModel.points.size.toFloat(),
            animationSpec = tween(if (animated) animationDuration else 0)
        )
    }

    Box(
        modifier = modifier
            .padding(8.dp)
    ) {

        Canvas(
            modifier = Modifier
                .width(lineChartModel.widthDp)
                .height(lineChartModel.heightDp)
        ) {
            val path = Path()
            points.forEachIndexed { index, point ->
                with(point) {
                    if (index == 0) {
                        path.moveTo(offset.x, size.height - offset.y)
                    } else {
                        path.lineTo(offset.x, size.height - offset.y)

                    }
                }

            }

            drawPath(path, lineColor, style = Stroke(width = lineStrokeWidth))

            points.forEach { point ->
                with(point) {
                    drawCircle(
                        color = pointColor,
                        radius = pointRadius,
                        center = Offset(offset.x, size.height - offset.y)
                    )
                }
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun LineChartPreview() {
    CanvasTheme {
        LineChartComponent(
            modifier = Modifier,
            lineChartModel = LineChartModel(
                widthDp = 300.dp,
                heightDp = 300.dp,
                points = listOf(
                    LineChartPoint(
                        offset = Offset(0f, 0f),
                        labelX = "0",
                        labelY = "0",
                    ),
                    LineChartPoint(
                        offset = Offset(50f, 50f),
                        labelX = "50",
                        labelY = "50",
                    ),
                    LineChartPoint(
                        offset = Offset(100f, 100f),
                        labelX = "100",
                        labelY = "100",
                    ),
                    LineChartPoint(
                        offset = Offset(150f, 50f),
                        labelX = "150",
                        labelY = "50",
                    ),
                    LineChartPoint(
                        offset = Offset(200f, 150f),
                        labelX = "200",
                        labelY = "150",
                    ),

                    )
            )
        )
    }
}