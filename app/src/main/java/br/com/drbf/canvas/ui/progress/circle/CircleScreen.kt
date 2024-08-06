package br.com.drbf.canvas.ui.progress.circle

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
import br.com.drbf.canvas.ui.progress.common.component.CircleProgress
import br.com.drbf.canvas.ui.progress.common.component.ProgressWithSlider

@Composable
fun CircleScreen(
    modifier: Modifier = Modifier
) {
    ProgressWithSlider(
        modifier = modifier,
    ) { sliderPosition -> CircleProgress(sliderPosition) }
}

@Preview(showBackground = true)
@Composable
fun TrianguleScreenPreview() {
    CircleScreen()
}