package br.com.drbf.canvas.ui.progress.gauge

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.drbf.canvas.ui.common.component.ProgressWithSlider

@Composable
fun GaugeScreen(
    modifier: Modifier = Modifier
) {
    ProgressWithSlider(
        modifier = modifier,
    ) { sliderPosition -> GaugeProgress(sliderPosition) }
}

@Composable
private fun GaugeProgress(
    sliderPosition: Float,
) {
    val textMeasurer = rememberTextMeasurer()
    val textColor: Color by animateColorAsState(
        if (sliderPosition > 0) Color.Cyan else Color.LightGray,
        label = "textColor"
    )
    Canvas(
        modifier = Modifier
            .size(250.dp)
            .padding(16.dp)
    ) {
        drawArc(
            brush = SolidColor(Color.LightGray),
            startAngle = 120f,
            sweepAngle = 300f,
            useCenter = false,
            style = Stroke(35f, cap = StrokeCap.Round)
        )

        val convertedValue = sliderPosition / 100 * 300

        drawArc(
            brush = SolidColor(Color.Cyan),
            startAngle = 120f,
            sweepAngle = convertedValue,
            useCenter = false,
            style = Stroke(35f, cap = StrokeCap.Round)
        )
        val measuredText =
            textMeasurer.measure(
                AnnotatedString("${sliderPosition.toInt()}%"),
                style = TextStyle(
                    color = textColor, fontSize = 48.sp
                )
            )
        drawText(
            textLayoutResult = measuredText,
            topLeft = Offset(
                (size.width / 2) - (measuredText.size.width / 2),
                (size.height / 2) - (measuredText.size.height / 2)
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GaugeScreenPreview() {
    GaugeScreen()
}