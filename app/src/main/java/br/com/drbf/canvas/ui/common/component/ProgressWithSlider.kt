package br.com.drbf.canvas.ui.common.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ProgressWithSlider(
    modifier: Modifier = Modifier,
    content: @Composable (sliderPosition: Float) -> Unit,
) {

    var sliderPosition by rememberSaveable { mutableFloatStateOf(0f) }

    Column(
        modifier =
        modifier
            .padding(16.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        content(sliderPosition)

        Slider(
            value = sliderPosition,
            valueRange = 0f..100f,
            onValueChange = { sliderPosition = it }
        )
        Text(text = "${sliderPosition}%")
    }

}