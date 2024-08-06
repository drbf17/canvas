package br.com.drbf.canvas.ui.chart.common

import androidx.compose.ui.graphics.Color
import java.math.BigDecimal

data class ChartEntry(
    val name: String,
    val value: BigDecimal,
    val percentage: Float,
    val color: Color,
)