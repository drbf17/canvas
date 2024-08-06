package br.com.drbf.canvas.ui.chart.common.extensions

import androidx.compose.ui.graphics.Color
import br.com.drbf.canvas.domain.chart.entities.BalanceEntry
import br.com.drbf.canvas.ui.chart.common.ChartEntry
import kotlin.random.Random

fun BalanceEntry.toChartEntry(): ChartEntry {
    return ChartEntry(
        name,
        value,
        percentage,
        color = generateRandomColor()
    )
}

fun generateRandomColor(): Color {
  return Color(
        red = Random.nextInt(256),
        green = Random.nextInt(256),
        blue = Random.nextInt(256)
    )
}