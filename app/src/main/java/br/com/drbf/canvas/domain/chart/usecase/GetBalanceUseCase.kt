package br.com.drbf.canvas.domain.chart.usecase

import br.com.drbf.canvas.data.assets.AssetsRepository
import br.com.drbf.canvas.domain.chart.entities.Balance

class GetBalanceUseCase(private val assetsRepository: AssetsRepository) {

    operator fun invoke(): Balance {

        return Balance.fromAssets(assetsRepository.assets)

    }
}