package com.example.composepractice.features.onboarding.components

import android.graphics.drawable.shapes.Shape
import android.util.Size
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.composepractice.ui.theme.ComposePracticeTheme


@Composable
fun PageIndicator(
    modifier: Modifier = Modifier,
    selectedPage: Int,
    indicatorSize: Dp,
    indicatorCount: Int
) {
    Row (modifier = modifier){
        repeat(indicatorCount){ page ->
            Box(
                modifier = modifier
                    .padding(5.dp, 0.dp)
                    .size(indicatorSize)
                    .clip(CircleShape)
                    .background(
                        if (selectedPage == page) Color(0xFFFB9400) else Color(0xFFE5E7EB),
                    )

            )
        }
    }

}

@Preview
@Composable
fun PageIndicatorPreview(){
    ComposePracticeTheme {
        PageIndicator(selectedPage = 0, indicatorCount = 3, indicatorSize = 20.dp)
    }
}