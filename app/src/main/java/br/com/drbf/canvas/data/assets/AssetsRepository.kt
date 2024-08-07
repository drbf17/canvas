package br.com.drbf.canvas.data.assets

import br.com.drbf.canvas.domain.chart.entities.Asset

class AssetsRepository {

    val assets = listOf(
        Asset("Bitcoin", 500.toBigDecimal()),
        Asset("Ethereum", 200.toBigDecimal()),
        Asset("Litecoin", 100.toBigDecimal()),
        Asset("Dogecoin", 100.toBigDecimal()),
        Asset("Cardano", 100.toBigDecimal())
    )
}