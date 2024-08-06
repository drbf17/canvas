package br.com.drbf.canvas.ui.chart.common.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import br.com.drbf.canvas.ui.chart.common.ChartEntry

@Composable

fun PieChartDetail(
    modifier: Modifier,
    color: Color,
    accumulatedPercentage: Float,
    percentage: Float,
    size: Dp = 60.dp,
    strokeWidth: Dp = 4.dp
) {

    val textMeasurer = rememberTextMeasurer()
    val textLayoutResult = textMeasurer.measure(text = AnnotatedString("$percentage%"))
    val textSize = textLayoutResult.size
    val startAngle = (accumulatedPercentage / 100) * 360
    val sweepAngle = (percentage / 100) * 360

    Canvas(modifier = modifier
        .size(size)
        .background(Color.Transparent)
        .padding(4.dp), onDraw = {

        drawArc(
            color = Color.LightGray,
            startAngle = 0f,
            sweepAngle = 360f,
            useCenter = false,
            style = Stroke(
                width = strokeWidth.toPx(),
                cap = StrokeCap.Round,
                join = StrokeJoin.Round
            )

        )
        drawArc(
            color = color,
            startAngle = startAngle,
            sweepAngle = sweepAngle,
            useCenter = false,
            style = Stroke(
                width = strokeWidth.toPx(),
                cap = StrokeCap.Round,
                join = StrokeJoin.Round
            )
        )


        drawText(
            textMeasurer, "$percentage%",
            topLeft = Offset(
                (this.size.width - textSize.width) / 2f,
                (this.size.height - textSize.height) / 2f
            ),
        )
    })

}

@Preview(showBackground = true)
@Composable
fun PieChartDetailPreview() {
    PieChartDetail(
        modifier = Modifier,
        color = Color.Red,
        accumulatedPercentage = 10f,
        percentage = 50f
    )
}