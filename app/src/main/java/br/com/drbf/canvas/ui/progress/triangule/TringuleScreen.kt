package br.com.drbf.canvas.ui.progress.triangule

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.ClipOp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.drbf.canvas.ui.common.component.ProgressWithSlider

@Composable
fun TrianguleScreen(
    modifier: Modifier = Modifier
) {
    ProgressWithSlider {
        TriangleProgress(it)
    }

}

@Composable
private fun TriangleProgress(sliderPosition: Float) {
    Canvas(
        modifier = Modifier
            .width(340.dp)
            .height(180.dp)
            .padding(16.dp)
    ) {
        val path = Path()
        path.moveTo(size.width, 0f)
        path.lineTo(size.width, size.height)
        path.lineTo(0f, size.height)
        clipPath(
            path = path,
            clipOp = ClipOp.Intersect
        ) {
            drawPath(
                path = path,
                brush = SolidColor(Color.Gray)
            )

            drawRect(
                SolidColor(Color.Cyan),
                size = Size(
                    sliderPosition / 100 * size.width,
                    size.height
                )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TrianguleScreenPreview() {
    TrianguleScreen()
}