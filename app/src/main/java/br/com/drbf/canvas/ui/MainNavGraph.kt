package br.com.drbf.canvas.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.drbf.canvas.data.assets.AssetsRepository
import br.com.drbf.canvas.domain.chart.usecase.GetBalanceUseCase
import br.com.drbf.canvas.ui.chart.bar.BarChartScreen
import br.com.drbf.canvas.ui.chart.common.components.pie.toPieChartListEntry
import br.com.drbf.canvas.ui.chart.horizontalbar.HorizontalBarScreen
import br.com.drbf.canvas.ui.chart.line.LineChartScreen
import br.com.drbf.canvas.ui.chart.pie.PieChartScreen
import br.com.drbf.canvas.ui.gauge.GaugeScreen
import br.com.drbf.canvas.ui.home.HomeScreen
import br.com.drbf.canvas.ui.progress.circle.CircleScreen
import br.com.drbf.canvas.ui.progress.gauge.GaugeProgressScreen
import br.com.drbf.canvas.ui.progress.triangule.TrianguleScreen
import br.com.drbf.canvas.ui.slider.arc.ArcSliderScreen
import kotlinx.serialization.Serializable

sealed interface Destination {



    @Serializable
    data object ChartBar : Destination

    @Serializable
    data object ChartBarHorizontal : Destination

    @Serializable
    data object ChartLine : Destination

    @Serializable
    data object ChartPieArc : Destination

    @Serializable
    data object Home : Destination

    @Serializable
    data object Gauge : Destination

    @Serializable
    data object ProgressCircle : Destination

    @Serializable
    data object ProgressGauge : Destination


    @Serializable
    data object ProgressTriangule : Destination

    @Serializable
    data object SliderArc : Destination


}

@Composable
fun MainNavGraph(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    val balance = GetBalanceUseCase(AssetsRepository()).invoke()
    val pieChartEntries = balance.entries.toPieChartListEntry()
    val prices = AssetsRepository().prices

    NavHost(
        navController = navController,
        startDestination = Destination.Home
    ) {
        composable<Destination.Home> {
            HomeScreen(modifier = modifier) { destination ->
                navController.navigate(destination)
            }

        }
        composable<Destination.ChartBar> {
            BarChartScreen(
                modifier = modifier,
            )

        }
        composable<Destination.ChartBarHorizontal> {
            HorizontalBarScreen(
                modifier = modifier,
            )

        }
        composable<Destination.ChartLine> {
            LineChartScreen(
                modifier = modifier,
                prices = prices
            )

        }
        composable<Destination.ChartPieArc> {
            PieChartScreen(
                modifier = modifier,
                chartEntries = pieChartEntries,
                totalValue = balance.totalAmount
            )

        }
        composable<Destination.Gauge> {
            GaugeScreen()
        }

        composable<Destination.ProgressCircle> {
            CircleScreen(modifier = modifier)

        }

        composable<Destination.ProgressGauge> {
            GaugeProgressScreen(modifier = modifier)

        }
        composable<Destination.ProgressTriangule> {
            TrianguleScreen(modifier = modifier)

        }

        composable<Destination.SliderArc> {
            ArcSliderScreen(modifier = modifier)

        }
    }

}




