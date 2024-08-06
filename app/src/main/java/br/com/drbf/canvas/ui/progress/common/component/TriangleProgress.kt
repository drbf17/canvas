package br.com.drbf.canvas.ui.progress.common.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.ClipOp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.unit.dp

@Composable
fun TriangleProgress(sliderPosition: Float) {
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