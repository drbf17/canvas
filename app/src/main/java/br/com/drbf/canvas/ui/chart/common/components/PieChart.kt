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
import br.com.drbf.canvas.ui.chart.common.PieChartEntry

@Composable

fun PieChart(
    modifier: Modifier,
    title: String = "Assets",
    charts: List<PieChartEntry>,
    size: Dp = 300.dp,
    strokeWidth: Dp = 16.dp
) {

    val textMeasurer = rememberTextMeasurer()
    val textLayoutResult = textMeasurer.measure(text = AnnotatedString(title))
    val textSize = textLayoutResult.size

    Canvas(modifier = modifier
        .size(size)
        .background(Color.Transparent)
        .padding(12.dp), onDraw = {


        charts.forEach {


            drawArc(
                color = it.color,
                startAngle = it.startAngle,
                sweepAngle = it.sweepAngle,
                useCenter = false,
                style = Stroke(
                    width = strokeWidth.toPx(),
                    cap = StrokeCap.Round,
                    join = StrokeJoin.Round
                )
            )

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