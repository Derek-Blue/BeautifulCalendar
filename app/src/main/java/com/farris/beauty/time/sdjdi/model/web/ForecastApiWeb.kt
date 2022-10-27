package com.farris.beauty.time.sdjdi.model.web

import com.farris.beauty.time.sdjdi.model.service.ForecastType
import com.farris.beauty.time.sdjdi.model.service.response.ForecastRootResponse
import com.farris.beauty.time.sdjdi.model.service.response.thirtysixhours.ThirtySixForecastRootResponse
import com.farris.beauty.time.sdjdi.model.service.response.tidal.TidalRootResponse

interface ForecastApiWeb {

    suspend fun getThirtySixHours(): Result<ThirtySixForecastRootResponse>

    suspend fun searchLocation(forecastType: ForecastType): Result<ForecastRootResponse>

    suspend fun filterLocation(forecastTypes: List<ForecastType>): Result<ForecastRootResponse>

    suspend fun tidal(): Result<TidalRootResponse>
}