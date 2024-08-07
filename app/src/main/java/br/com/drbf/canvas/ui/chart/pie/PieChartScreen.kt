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
import br.com.drbf.canvas.ui.chart.common.PieChartEntry
import br.com.drbf.canvas.ui.chart.common.components.PieChart
import br.com.drbf.canvas.ui.chart.common.components.PieChartDetail
import br.com.drbf.canvas.ui.theme.CanvasTheme

@Composable
fun PieChartScreen(
    modifier: Modifier,
    chartEntries: List<PieChartEntry>
) {

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        item { PieChart(modifier = modifier, charts = chartEntries) }

        items(chartEntries) { chartEntry ->
            ChartEntryRoom(modifier, chartEntry)

        }
    }

}

@Composable
private fun ChartEntryRoom(
    modifier: Modifier,
    pieChartEntry: PieChartEntry,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        PieChartDetail(
            modifier = modifier,
            pieChartEntry = pieChartEntry,
        )
        Spacer(modifier = Modifier.padding(8.dp))

        Text(text = pieChartEntry.name)
    }
}

@Preview(showBackground = true)
@Composable
fun PieChartPreview() {
    CanvasTheme {
        PieChartScreen(
            modifier = Modifier,
            chartEntries = listOf(
                PieChartEntry(
                    name = "Bitcoin",
                    value = 100.toBigDecimal(),
                    percentage = 25f,
                    startAngle = 0f,
                    sweepAngle = 90f,
                    color = Color.Blue
                ),
                PieChartEntry(
                    name = "DroidCoin",
                    value = 103.toBigDecimal(),
                    percentage = 25f,
                    startAngle = 90f,
                    sweepAngle = 90f,
                    color = Color.Yellow
                ),
                PieChartEntry(
                    name = "FakeCoin",
                    value = 103.toBigDecimal(),
                    percentage = 25f,
                    startAngle = 180f,
                    sweepAngle = 90f,
                    color = Color.Green
                ),
                PieChartEntry(
                    name = "MyCoin",
                    value = 103.toBigDecimal(),
                    percentage = 25f,
                    startAngle = 270f,
                    sweepAngle = 90f,
                    color = Color.Red
                ),
            )
        )
    }

}




