package com.example.myapplication

import androidx.annotation.DrawableRes

data class Feature(
    val title: String,
    @DrawableRes val iconId: Int,
    val lightColor: androidx.compose.ui.graphics.Color,
    val mediumColor: androidx.compose.ui.graphics.Color,
    val darkColor: androidx.compose.ui.graphics.Color
)
