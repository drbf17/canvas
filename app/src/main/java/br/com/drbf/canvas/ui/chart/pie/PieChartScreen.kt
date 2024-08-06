package br.com.drbf.canvas.ui.chart.pie

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.drbf.canvas.ui.chart.common.ChartEntry
import br.com.drbf.canvas.ui.chart.common.components.PieChart
import br.com.drbf.canvas.ui.chart.common.components.PieChartDetail
import br.com.drbf.canvas.ui.theme.CanvasTheme

@Composable
fun PieChartScreen(
    modifier: Modifier,
    chartEntries: List<ChartEntry>
) {
    var accumulatedPercentage = 0f

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        item { PieChart(modifier = modifier, charts = chartEntries) }

        items(chartEntries) { chartEntry ->
            ChartEntryRoom(modifier, chartEntry, accumulatedPercentage)
            accumulatedPercentage += chartEntry.percentage

        }
    }

}

@Composable
private fun ChartEntryRoom(
    modifier: Modifier,
    chartEntry: ChartEntry,
    accumulatedPercentage: Float
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        PieChartDetail(
            modifier = modifier,
            color = chartEntry.color,
            accumulatedPercentage = accumulatedPercentage,
            percentage = chartEntry.percentage,
        )
        Spacer(modifier = Modifier.padding(8.dp))

        Text(text = chartEntry.name)
    }
}

@Preview(showBackground = true)
@Composable
fun PieChartPreview() {
    CanvasTheme {
        PieChartScreen(
            modifier = Modifier,
            chartEntries = listOf(
                ChartEntry("Entry 1", "10".toBigDecimal(), 50f, Color.Red),
                ChartEntry("Entry 2", "20".toBigDecimal(), 25f, Color.Blue),
                ChartEntry("Entry 3", "70".toBigDecimal(), 25f, Color.Green)
            )
        )
    }

}




