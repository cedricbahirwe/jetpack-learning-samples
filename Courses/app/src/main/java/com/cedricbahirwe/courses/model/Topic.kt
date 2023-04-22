package com.cedricbahirwe.courses.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Topic(
    @StringRes val topicResId: Int,
    val quantity: Int,
    @DrawableRes val imageResId: Int
)