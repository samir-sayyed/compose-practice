package com.example.composepractice.features.onboarding.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.dp
import com.example.composepractice.R
import com.example.composepractice.features.onboarding.OnboardingItem
import com.example.composepractice.ui.theme.ComposePracticeTheme


@Composable
fun OnboardingItem(
    onboardingItem: OnboardingItem,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = onboardingItem.image),
            contentDescription = "",
            alignment = Alignment.Center
        )
        Spacer(modifier = Modifier.padding(0.dp, 24.dp))
        Text(
            text = onboardingItem.text,
            fontWeight = FontWeight(600),
            fontSize = 18.sp,
            color = Color(0xFF0F172A),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.padding(0.dp, 4.dp))

        Text(
            text = onboardingItem.subText,
            fontSize = 14.sp,
            fontWeight = FontWeight(400),
            color = Color(0xFF9CA3AF),
            textAlign = TextAlign.Center,
            lineHeight = 20.sp
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun OnboardingItemPreview() {
    ComposePracticeTheme {
        OnboardingItem(
            onboardingItem =
            OnboardingItem(
                R.drawable.onboarding_image_1,
                "Thousands of tested recipes",
                "There is no need to fear failure. Tested recipes are guaranteed to work by our professional chefs."
            )
        )
    }
}