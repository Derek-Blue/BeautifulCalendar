package com.farris.beauty.time.sdjdi.module.usecase

import com.farris.beauty.time.sdjdi.type.CountyType
import com.farris.beauty.time.sdjdi.type.CycleType
import com.farris.beauty.time.sdjdi.type.WeatherElementType

interface ForecastUseCase {

    /**
     * return Map<[ Map< [ WeatherElement ] to [ List< WeatherTime > ] >
     */

    suspend operator fun invoke(
        countyType: CountyType,
        cycleType: CycleType,
        weatherElementType: List<WeatherElementType> = emptyList()
    ): Result<Map<WeatherElementType, List<UseCaseWeatherTime>>>
}