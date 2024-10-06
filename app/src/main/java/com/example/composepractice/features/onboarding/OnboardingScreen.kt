package com.example.composepractice.features.onboarding

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composepractice.R
import com.example.composepractice.common.AppButton
import com.example.composepractice.features.onboarding.components.OnboardingItem
import com.example.composepractice.features.onboarding.components.PageIndicator
import com.example.composepractice.ui.theme.ComposePracticeTheme
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnboardingScreen() {
    Column(
        modifier = Modifier.fillMaxHeight(),
        verticalArrangement = Arrangement.Bottom
    ) {
        val pagerState = rememberPagerState(
            initialPage = 0,
            initialPageOffsetFraction = 0f
        ) {
            items.size
        }

        LaunchedEffect(key1 = pagerState.currentPage) {
            launch {
                while (true) {
                    delay(1500)
                    withContext(NonCancellable) {
                        if (pagerState.currentPage < 2) {
                            pagerState.animateScrollToPage(pagerState.currentPage + 1)
                        } else {
                            pagerState.scrollToPage(0)
                        }
                    }
                }
            }
        }


        HorizontalPager(state = pagerState) {
            OnboardingItem(
                modifier = Modifier.padding(0.dp, 80.dp),
                onboardingItem = items[it]
            )
        }

        Spacer(modifier = Modifier.padding(0.dp, 10.dp))
        PageIndicator(
            modifier = Modifier
                .align(Alignment.CenterHorizontally),
            selectedPage = pagerState.currentPage, indicatorSize = 8.dp, indicatorCount = 3
        )
        Spacer(modifier = Modifier.padding(0.dp, 20.dp))
        AppButton(
            text = "Get Started",
            onClick = { /*TODO*/ },
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .align(Alignment.CenterHorizontally)
                .padding(0.dp, 0.dp, 0.dp, 20.dp)
        )
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun OnboardingScreenPreview() {
    ComposePracticeTheme {
        OnboardingScreen()
    }
}