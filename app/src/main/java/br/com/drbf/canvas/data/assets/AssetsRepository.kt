package br.com.drbf.canvas.data.assets

import br.com.drbf.canvas.domain.chart.entities.Asset

class AssetsRepository {

    val assets = listOf(
        Asset("Bitcoin", 100.toBigDecimal()),
        Asset("Ethereum", 50.toBigDecimal()),
        Asset("Litecoin", 30.toBigDecimal()),
        Asset("Dogecoin", 20.toBigDecimal()),
        Asset("Cardano", 10.toBigDecimal())
    )
}