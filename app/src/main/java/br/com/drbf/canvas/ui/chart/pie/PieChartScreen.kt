package br.com.drbf.canvas.ui.chart.pie

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.drbf.canvas.ui.chart.common.PieChartEntry
import br.com.drbf.canvas.ui.chart.common.components.PieChart
import br.com.drbf.canvas.ui.chart.common.components.PieChartDetail
import br.com.drbf.canvas.ui.theme.CanvasTheme
import kotlinx.coroutines.launch
import java.math.BigDecimal

@Composable
fun PieChartScreen(
    modifier: Modifier = Modifier,
    chartEntries: List<PieChartEntry>,
    totalValue: BigDecimal
) {

    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    var selectedItem by remember { mutableStateOf<PieChartEntry?>(null) }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        state = listState,
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        item {
            PieChart(
                modifier = modifier,
                charts = chartEntries,
                totalValue = totalValue
            ) {
                selectedItem = it
                coroutineScope.launch {
                    chartEntries.indexOf(it).let { index ->
                        listState.animateScrollToItem(index)
                    }

                }
            }
        }

        items(chartEntries) { chartEntry ->
            ChartEntryRoom(
                pieChartEntry = chartEntry,
                isSelected = selectedItem == chartEntry
            )

        }
    }

}

@Composable
private fun ChartEntryRoom(
    modifier: Modifier = Modifier,
    pieChartEntry: PieChartEntry,
    isSelected: Boolean,
) {

    var backgroundColor = if (isSelected) Color.Yellow else Color.Transparent
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(8.dp)
            ),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        PieChartDetail(
            modifier = modifier,
            pieChartEntry = pieChartEntry,
        )
        Spacer(modifier = Modifier.padding(8.dp))

        Column {
            Text(text = pieChartEntry.name)
            Text(text = "US$ ${pieChartEntry.value}")
        }

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
            ),
            totalValue = 409.94.toBigDecimal()
        )
    }

}




