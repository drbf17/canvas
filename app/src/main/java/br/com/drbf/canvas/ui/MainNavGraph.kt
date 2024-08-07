package br.com.drbf.canvas.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.drbf.canvas.data.assets.AssetsRepository
import br.com.drbf.canvas.domain.chart.usecase.GetBalanceUseCase
import br.com.drbf.canvas.ui.chart.common.toPieChartListEntry
import br.com.drbf.canvas.ui.chart.pie.PieChartScreen
import br.com.drbf.canvas.ui.home.HomeScreen
import br.com.drbf.canvas.ui.progress.circle.CircleScreen
import br.com.drbf.canvas.ui.progress.gauge.GaugeScreen
import br.com.drbf.canvas.ui.progress.triangule.TrianguleScreen
import kotlinx.serialization.Serializable

sealed interface Destination {

    @Serializable
    data object ChartPieArc : Destination

    @Serializable
    data object ProgressCircle : Destination

    @Serializable
    data object ProgressGauge : Destination

    @Serializable
    data object Home : Destination

    @Serializable
    data object ProgressTriangule : Destination


}

@Composable
fun MainNavGraph(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    val balance = GetBalanceUseCase(AssetsRepository()).invoke()
    val pieChartEntries = balance.entries.toPieChartListEntry()

    NavHost(
        navController = navController,
        startDestination = Destination.Home
    ) {
        composable<Destination.Home> {
            HomeScreen(modifier = modifier) { destination ->
                navController.navigate(destination)
            }

        }
        composable<Destination.ProgressCircle> {
            CircleScreen(modifier = modifier)

        }

        composable<Destination.ChartPieArc> {
            PieChartScreen(
                modifier = modifier,
                chartEntries = pieChartEntries,
                totalValue = balance.totalAmount
            )

        }
        composable<Destination.ProgressGauge> {
            GaugeScreen(modifier = modifier)

        }
        composable<Destination.ProgressTriangule> {
            TrianguleScreen(modifier = modifier)

        }
    }

}




