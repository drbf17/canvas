package br.com.drbf.canvas.ui.chart.line

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.drbf.canvas.data.assets.AssetsRepository
import br.com.drbf.canvas.domain.chart.entities.Price
import br.com.drbf.canvas.ui.chart.common.components.line.LineChartComponent
import br.com.drbf.canvas.ui.chart.common.components.line.LineChartModel
import br.com.drbf.canvas.ui.chart.common.components.line.toLineChartModel


@Composable
fun LineChartScreen(
    modifier: Modifier = Modifier,
    prices: List<Price>,
) {
    val density = LocalDensity.current
    var lineChartModel: LineChartModel? by remember { mutableStateOf(null) }

    LaunchedEffect(prices) {
        lineChartModel = prices.toLineChartModel(
            widthDp = 300.dp,
            heightDp = 300.dp,
            density
        )
    }
    Box(modifier = modifier
        .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        lineChartModel?.let {
            LineChartComponent(
                modifier = modifier,
                lineChartModel = it

            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LineChartScreenPreview() {
    LineChartScreen(
        prices = AssetsRepository().prices)
}


