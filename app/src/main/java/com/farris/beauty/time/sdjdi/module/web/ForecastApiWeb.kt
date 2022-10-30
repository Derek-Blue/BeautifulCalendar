package com.farris.beauty.time.sdjdi.module.web

import com.farris.beauty.time.sdjdi.type.ForecastApiType
import com.farris.beauty.time.sdjdi.module.service.response.ForecastRootResponse
import com.farris.beauty.time.sdjdi.module.service.response.thirtysixhours.ThirtySixForecastRootResponse
import com.farris.beauty.time.sdjdi.module.service.response.tidal.TidalRootResponse
import com.farris.beauty.time.sdjdi.type.WeatherElementType

interface ForecastApiWeb {

    suspend fun getThirtySixHours(): Result<ThirtySixForecastRootResponse>

    suspend fun searchLocation(
        forecastApiType: ForecastApiType,
        elements: List<WeatherElementType> = emptyList()
    ): Result<ForecastRootResponse>

    suspend fun filterLocation(forecastApiTypes: List<ForecastApiType>): Result<ForecastRootResponse>

    suspend fun tidal(): Result<TidalRootResponse>
}