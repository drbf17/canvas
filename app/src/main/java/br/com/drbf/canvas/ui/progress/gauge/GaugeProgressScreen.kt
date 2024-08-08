package br.com.drbf.canvas.ui.progress.gauge

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import br.com.drbf.canvas.ui.progress.common.component.GaugeProgress
import br.com.drbf.canvas.ui.progress.common.component.ProgressWithSlider

@Composable
fun GaugeProgressScreen(
    modifier: Modifier = Modifier
) {
    ProgressWithSlider(
        modifier = modifier,
    ) { sliderPosition -> GaugeProgress(sliderPosition) }
}

@Preview(showBackground = true)
@Composable
fun GaugeScreenPreview() {
    GaugeProgressScreen()
}