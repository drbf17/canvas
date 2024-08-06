package br.com.drbf.canvas.domain.chart.entities

import java.math.BigDecimal
import java.math.RoundingMode

data class Balance(
    val totalAmount: BigDecimal,
    val entries: List<BalanceEntry>

) {
    companion object {
        fun fromAssets(assets: List<Asset>): Balance {
            val totalAmount = assets.map { it.value }.reduce { acc, bigDecimal -> acc + bigDecimal }
            val entries = assets.map { asset ->
                val percentage = (asset.value.divide(totalAmount, 2, RoundingMode.HALF_UP)
                    .times(100.toBigDecimal()))
                BalanceEntry(asset, percentage.toFloat())
            }
            return Balance(totalAmount, entries)
        }
    }
}

data class BalanceEntry(
    private val asset: Asset,
    val percentage: Float
) {
    val name = asset.name
    val value = asset.value
}


