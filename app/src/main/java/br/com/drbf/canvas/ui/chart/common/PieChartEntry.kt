package br.com.drbf.canvas.ui.chart.common

import androidx.compose.ui.graphics.Color
import br.com.drbf.canvas.domain.chart.entities.BalanceEntry
import java.math.BigDecimal

data class PieChartEntry(
    val name: String,
    val value: BigDecimal,
    val percentage: Float,
    val startAngle : Float,
    val sweepAngle: Float,
    val color: Color,
)

fun List<BalanceEntry>.toPieChartListEntry(
    colors: List<Color> = listOf(
        Color.Cyan,
        Color.Red,
        Color.Blue,
        Color.Green,
        Color.Magenta,
        Color.Yellow,
    )
) : List<PieChartEntry> = this.mapIndexed { index, balanceEntry ->
    val sweepAngle = (balanceEntry.percentage / 100) * 360
    val startAngle =
        this.subList(0, index).sumOf { it.percentage.toDouble() / 100 * 360 }
    PieChartEntry(
        name = balanceEntry.name,
        value = balanceEntry.value,
        percentage = balanceEntry.percentage,
        startAngle = startAngle.toFloat(),
        sweepAngle = sweepAngle,
        color = colors[index % colors.size]
    )
}