package br.com.drbf.canvas.ui.chart.bar

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times


@Composable
fun BarChartScreen(modifier: Modifier = Modifier) {

    val values by remember {
        mutableStateOf(
            listOf(
                10f, 20f, 30f, 40f, 50f, 60f, 70f, 80f, 90f, 100f,
                90f, 80f, 70f, 60f, 50f, 40f, 30f, 20f, 10f, 0f,
                10f, 20f, 30f, 40f, 50f, 60f, 70f, 80f, 90f, 100f,
            )
        )
    }

    val density = LocalDensity.current
    val canvasHeightDp = 300.dp
    val canvasHeight = density.run { canvasHeightDp.toPx() }
    val barWidthDp = 16.dp
    val barWidth = density.run { barWidthDp.toPx() }
    val spaceWidthDp = 4.dp
    val spaceWidth = density.run { spaceWidthDp.toPx() }
    val width = (values.size ) * (barWidthDp + spaceWidthDp)
    val listState = rememberLazyListState()

    LazyRow(
        modifier = Modifier
            .wrapContentWidth()
            .height(canvasHeightDp),
        state = listState


    ) {
        item {
            Canvas(
                modifier = modifier
                    .width(width)
            ) {
                values.forEachIndexed { index, value ->
                    drawRect(
                        color = Color.Blue,
                        topLeft = Offset(
                            x = index * (barWidth + spaceWidth),
                            y = canvasHeight - value
                        ),
                        size = Size(width = barWidth, height = value)
                    )
                }
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun BarChartScreenPreview() {
    BarChartScreen()
}


