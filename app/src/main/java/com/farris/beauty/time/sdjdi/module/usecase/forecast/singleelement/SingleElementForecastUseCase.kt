package com.farris.beauty.time.sdjdi.module.usecase.forecast.singleelement

import com.farris.beauty.time.sdjdi.module.usecase.forecast.UseCaseWeatherTime
import com.farris.beauty.time.sdjdi.type.CountyType
import com.farris.beauty.time.sdjdi.type.CycleType
import com.farris.beauty.time.sdjdi.type.WeatherElementType

interface SingleElementForecastUseCase {

    suspend operator fun invoke(
        countyType: CountyType,
        cycleType: CycleType,
        weatherElementType: WeatherElementType
    ): Result<Map<String, List<UseCaseWeatherTime>>>
}