package com.farris.beauty.time.sdjdi.module.usecase.forecast

import java.util.*

data class UseCaseWeatherTime(
    val townShip: String,
    val elementName: String,
    val startTime: Calendar,
    val endTime: Calendar?,
    val weatherElements: List<UseCaseWeatherElement>
)
