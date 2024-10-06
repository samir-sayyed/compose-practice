package com.example.composepractice.common

import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composepractice.R
import com.example.composepractice.ui.theme.ComposePracticeTheme
import com.example.composepractice.ui.theme.PrimaryColor

@Composable
fun AppButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        modifier = modifier.clip(RoundedCornerShape(12.dp)),
        colors = ButtonDefaults.textButtonColors(
            PrimaryColor
        )
    ) {
        Text(text = text, style = TextStyle(
            fontSize = 14.sp,
            lineHeight = 20.sp,
            fontWeight = FontWeight(600),
            color = Color(0xFFFFFFFF),
            textAlign = TextAlign.Center,
        )
        )
    }
}

@Preview
@Composable
fun AppButtonPreview(){
    ComposePracticeTheme {
        AppButton(text = "Get Started", onClick = { /*TODO*/ })
    }
}