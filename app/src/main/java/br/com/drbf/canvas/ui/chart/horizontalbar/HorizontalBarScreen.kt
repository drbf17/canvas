package br.com.drbf.canvas.ui.chart.horizontalbar

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import br.com.drbf.canvas.ui.chart.common.components.bar.BarModel
import br.com.drbf.canvas.ui.chart.common.components.bar.HorizontalBarComponent

@Composable
fun HorizontalBarScreen(
    modifier: Modifier = Modifier,
) {
    HorizontalBarComponent(
        modifier = modifier,
        bars = horizontalBarValues,
    )
}
val horizontalBarValues = listOf(
    BarModel("Ação 1", 10f),
    BarModel("Ação 2", 20f),
    BarModel("Ação 3", 30f),
    BarModel("Ação 4", 40f),
    BarModel("Ação 5", 50f),
    BarModel("Ação 6", 60f),
    BarModel("Ação 7", 70f),
    BarModel("Ação 8", 80f),
    BarModel("Ação 9", 90f),
    BarModel("Ação 10", 100f),
    BarModel("Ação 11", 10f),
    BarModel("Ação 12", 20f),
    BarModel("Ação 13", 30f),
    BarModel("Ação 14", 40f),
    BarModel("Ação 15", 50f),
    BarModel("Ação 16", 60f),
    BarModel("Ação 17", 70f),
    BarModel("Ação 18", 80f),
    BarModel("Ação 19", 90f),
    BarModel("Ação 20", 100f),
    BarModel("Ação 21", 10f),
    BarModel("Ação 22", 20f),
    BarModel("Ação 23", 30f),
    BarModel("Ação 24", 40f),
    BarModel("Ação 25", 50f),
    BarModel("Ação 26", 60f),
    BarModel("Ação 27", 70f),
    BarModel("Ação 28", 80f),
    BarModel("Ação 29", 90f),
    BarModel("Ação 30", 100f),
    )

@Preview(showBackground = true)
@Composable
fun HorizontalBarScreenPreview() {
    HorizontalBarScreen()
}
