package br.com.drbf.canvas.ui.progress.triangule

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import br.com.drbf.canvas.ui.progress.common.component.ProgressWithSlider
import br.com.drbf.canvas.ui.progress.common.component.TriangleProgress

@Composable
fun TrianguleScreen(
    modifier: Modifier = Modifier
) {
    ProgressWithSlider {
        TriangleProgress(it)
    }

}

@Preview(showBackground = true)
@Composable
fun TrianguleScreenPreview() {
    TrianguleScreen()
}