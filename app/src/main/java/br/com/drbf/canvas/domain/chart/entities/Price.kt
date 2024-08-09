package br.com.drbf.canvas.domain.chart.entities

import java.math.BigDecimal
import java.time.LocalDate

data class Price(
    val value: BigDecimal,
    val date: LocalDate
)
