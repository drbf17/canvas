package br.com.drbf.canvas.ui.progress.common.component

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
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CircleProgress(
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
        drawCircle(
            SolidColor(Color.LightGray),
            size.width / 2,
            style = Stroke(35f)
        )
        val convertedValue = sliderPosition / 100 * 360
        drawArc(
            brush = SolidColor(Color.Cyan),
            startAngle = -90f,
            sweepAngle = convertedValue,
            useCenter = false,
            style = Stroke(35f)
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
                ((size.width  - measuredText.size.width) / 2),
                ((size.height  - measuredText.size.height) / 2)
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CircleProgressPreview() {
    CircleProgress(50f)
}