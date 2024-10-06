package com.example.composepractice.features.onboarding

import com.example.composepractice.R

data class OnboardingItem(
    val image: Int,
    val text: String,
    val subText: String
)

val items = listOf(
    OnboardingItem(
        R.drawable.onboarding_image_1,
        "Thousands of tested recipes",
        "There is no need to fear failure. Tested recipes are guaranteed to work by our professional chefs."
    ),
    OnboardingItem(
        R.drawable.onboarding_image_1,
        "Thousands of tested recipes",
        "There is no need to fear failure. Tested recipes are guaranteed to work by our professional chefs."
    ),
    OnboardingItem(
        R.drawable.onboarding_image_1,
        "Thousands of tested recipes",
        "There is no need to fear failure. Tested recipes are guaranteed to work by our professional chefs."
    )
)


