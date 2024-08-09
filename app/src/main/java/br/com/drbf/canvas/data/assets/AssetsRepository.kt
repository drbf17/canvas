package br.com.drbf.canvas.data.assets

import br.com.drbf.canvas.domain.chart.entities.Asset
import br.com.drbf.canvas.domain.chart.entities.Price
import java.time.LocalDate
import java.util.Date

class AssetsRepository {

    val assets = listOf(
        Asset("Bitcoin", 500.toBigDecimal()),
        Asset("Ethereum", 200.toBigDecimal()),
        Asset("Litecoin", 100.toBigDecimal()),
        Asset("Dogecoin", 100.toBigDecimal()),
        Asset("Cardano", 100.toBigDecimal())
    )

    val prices = listOf(
        Price(200.toBigDecimal(), LocalDate.now().minusDays(9)),
        Price(300.toBigDecimal(), LocalDate.now().minusDays(8)),
        Price(550.toBigDecimal(), LocalDate.now().minusDays(7)),
        Price(600.toBigDecimal(), LocalDate.now().minusDays(6)),
        Price(500.toBigDecimal(), LocalDate.now().minusDays(5)),
        Price(500.toBigDecimal(), LocalDate.now().minusDays(4)),
        Price(600.toBigDecimal(), LocalDate.now().minusDays(3)),
        Price(650.toBigDecimal(), LocalDate.now().minusDays(2)),
        Price(600.toBigDecimal(), LocalDate.now().minusDays(1)),
        Price(900.toBigDecimal(), LocalDate.now())
    )
}