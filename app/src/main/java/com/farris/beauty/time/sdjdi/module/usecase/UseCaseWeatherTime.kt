package com.farris.beauty.time.sdjdi.module.usecase

import java.util.*

data class UseCaseWeatherTime(
    val startTime: Calendar,
    val endTime: Calendar?,
    val weatherElements: List<UseCaseWeatherElement>
)
