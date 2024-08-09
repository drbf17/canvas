package br.com.drbf.canvas.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import br.com.drbf.canvas.ui.Destination

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navigate: ((Any) -> Unit)? = null
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        items(actions) { action ->
            Button(onClick = {
                navigate?.invoke(action.destination)
            }) {
                Text(text = action.title)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}

data class Action(
    val title: String,
    val destination: Destination
)

private val actions = listOf(
    Action("Line Chart", Destination.ChartLine),
    Action("Bar Chart", Destination.ChartBar),
    Action("Horizontal Bar Chart", Destination.ChartBarHorizontal),
    Action("Pie Chart", Destination.ChartPieArc),
    Action("Gauge", Destination.Gauge),
    Action("Circle Progress", Destination.ProgressCircle),
    Action("Gauge Progress", Destination.ProgressGauge),
    Action("Triangule Progress", Destination.ProgressTriangule),
    Action("Arc Slider", Destination.SliderArc)
)