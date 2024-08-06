package br.com.drbf.canvas.ui.chart.common.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import br.com.drbf.canvas.ui.chart.common.ChartEntry

@Composable

fun PieChart(
    modifier: Modifier,
    charts: List<ChartEntry>,
    size: Dp = 300.dp,
    strokeWidth: Dp = 16.dp
) {
    val title = "Assets"
    val textMeasurer = rememberTextMeasurer()
    val textLayoutResult = textMeasurer.measure(text = AnnotatedString(title))
    val textSize = textLayoutResult.size

    Canvas(modifier = modifier
        .size(size)
        .background(Color.Transparent)
        .padding(12.dp), onDraw = {

        var startAngle = 0f
        var sweepAngle: Float

        charts.forEach {

            sweepAngle = (it.percentage / 100) * 360

            drawArc(
                color = it.color,
                startAngle = startAngle,
                sweepAngle = sweepAngle,
                useCenter = false,
                style = Stroke(
                    width = strokeWidth.toPx(),
                    cap = StrokeCap.Round,
                    join = StrokeJoin.Round
                )
            )

            startAngle += sweepAngle
        }

        drawText(
            textMeasurer, title,
            topLeft = Offset(
                (this.size.width - textSize.width) / 2f,
                (this.size.height - textSize.height) / 2f
            ),
        )
    })

}