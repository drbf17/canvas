package br.com.drbf.canvas.ui.gauge


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.drbf.canvas.ui.gauge.common.component.GaugeChart
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Composable
fun GaugeScreen(modifier: Modifier = Modifier) {

    val scope = rememberCoroutineScope()
    var percents by remember { mutableStateOf(listOf(10f, 40f, 90f)) }

    LaunchedEffect(Unit) {
        scope.launch {
            while (true) {
                delay(3 * 1000)
                percents = percents.map {
                    (0..100).random().toFloat()
                }
            }
        }
    }

    LazyColumn(
        modifier = modifier
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(percents) { percent ->
            GaugeChart(
                value = percent,
                needleColor = MaterialTheme.colorScheme.primary
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GaugeChartPreview() {
    GaugeScreen()
}
