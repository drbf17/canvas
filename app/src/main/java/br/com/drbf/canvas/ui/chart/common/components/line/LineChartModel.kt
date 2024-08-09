package br.com.drbf.canvas.ui.chart.common.components.line

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import br.com.drbf.canvas.domain.chart.entities.Price
import java.time.Period

data class LineChartModel(
    val widthDp: Dp = 300.dp,
    val heightDp: Dp = 300.dp,
    val points: List<LineChartPoint>
)

data class LineChartPoint(
    val offset: Offset,
    val labelX: String,
    val labelY: String
)

fun List<Price>.toLineChartModel(
    widthDp: Dp = 300.dp,
    heightDp: Dp = 300.dp,
    density: Density
): LineChartModel {

    val width = density.run { widthDp.toPx() }
    val height = density.run { heightDp.toPx() }

    val maxPrice = maxOf { it.value }.toFloat()

    val minData = minOf { it.date }
    val maxData = maxOf { it.date }

    val numberOfDays = Period.between(minData, maxData).days


    val chartPoints = sortedBy { it.date }.map { price ->
        val offset = Offset(
            x = (Period.between(
                minData,
                price.date
            ).days.toFloat() / numberOfDays.toFloat()) * width,
            y = (price.value.toFloat() / maxPrice) * height
        )
        LineChartPoint(
            offset = offset,
            labelX = price.date.toString(),
            labelY = price.value.toString()
        )
    }

    return LineChartModel(
        widthDp = widthDp,
        heightDp = heightDp,
        points = chartPoints
    )


}
