package br.com.drbf.canvas.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
    navigate: ((Any) -> Unit)? = null) {
    Column (
        modifier = modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(onClick = {
            navigate?.invoke(Destination.ChartPieArc)
        }) {
            Text(text = "Static Pie Chart")
        }
        Button(onClick = {
            navigate?.invoke(Destination.ProgressCircle)
        }) {
            Text(text = "Circle Progress")
        }
        Button(onClick = {
            navigate?.invoke(Destination.ProgressGauge)
        }) {
            Text(text = "Gauge Progress")
        }
        Button(onClick = {
            navigate?.invoke(Destination.ProgressTriangule)
        }) {
            Text(text = "Triangule Progress")
        }

    }

}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}