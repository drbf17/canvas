package br.com.drbf.canvas.ui.slider.arc

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import br.com.drbf.canvas.ui.slider.common.component.ArcSlider


@Composable
fun ArcSliderScreen(
    modifier: Modifier = Modifier,
) {

    Box(
        modifier = modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center

    ) {
        ArcSlider()
    }
}

@Preview(showBackground = true)
@Composable
fun ArcSliderScreenPreview() {
    ArcSliderScreen()
}

