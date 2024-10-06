package com.example.composepractice.spinwheel.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
internal fun SpinWheelSlice(
    modifier: Modifier = Modifier,
    size: Dp,
    brush: Brush,
    degree: Float,
    content: @Composable () -> Unit,
) {
    Box(
        modifier = modifier
            .size(size)
    ) {
        Canvas(
            modifier = Modifier
                .size(size)
        ) {
            drawArc(
                brush = brush,
                startAngle = -90f - (degree / 2),
                sweepAngle = degree,
                useCenter = true,
            )
        }
        Box(modifier = Modifier
            .align(Alignment.TopCenter)
            .padding(top = 20.dp)) {
            content()
        }
    }
}

@Preview
@Composable
private fun OneSpinWheelSlice2Preview() {
    Box(
        modifier = Modifier
            .size(200.dp)
    ) {
        Canvas(
            modifier = Modifier
                .fillMaxSize()
        ) {
            drawArc(
                brush = Brush.verticalGradient(
                    listOf(
                        Color.Red,
                        Color.Yellow
                    ),
                    endY = 200.dp.toPx() /2f
                ),
                startAngle = -90f - (30f / 2),
                sweepAngle = 30f,
                useCenter = true,
            )
        }
    }
}
