package com.farris.beauty.time.sdjdi.module.usecase.forecast

import com.farris.beauty.time.sdjdi.type.CountyType
import com.farris.beauty.time.sdjdi.type.CycleType
import com.farris.beauty.time.sdjdi.type.WeatherElementType

interface ForecastUseCase {

    /**
     * return Map<[ [ town ship ] to Map< [ WeatherElement ] to [ List< WeatherTime > ] > >
     */

    suspend operator fun invoke(
        countyType: CountyType,
        cycleType: CycleType,
        weatherElementType: List<WeatherElementType> = emptyList()
    ): Result<Map<String, Map<WeatherElementType, List<UseCaseWeatherTime>>>>
}