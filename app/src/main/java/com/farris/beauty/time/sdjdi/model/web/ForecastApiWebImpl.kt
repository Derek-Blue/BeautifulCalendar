package com.farris.beauty.time.sdjdi.model.web

import com.farris.beauty.time.sdjdi.model.service.ForecastApiService
import com.farris.beauty.time.sdjdi.model.service.ForecastType
import com.farris.beauty.time.sdjdi.model.service.RetrofitManager
import com.farris.beauty.time.sdjdi.model.service.data.checkIsSuccessful
import com.farris.beauty.time.sdjdi.model.service.data.requireBody
import com.farris.beauty.time.sdjdi.model.service.response.ForecastRootResponse
import com.farris.beauty.time.sdjdi.model.service.response.thirtysixhours.ThirtySixForecastRootResponse
import com.farris.beauty.time.sdjdi.model.service.response.tidal.TidalRootResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class ForecastApiWebImpl(
    private val service: ForecastApiService = RetrofitManager.getForecastApi,
    private val defaultDispatcher: CoroutineContext = Dispatchers.IO
) : ForecastApiWeb {

    override suspend fun getThirtySixHours(): Result<ThirtySixForecastRootResponse> {
        return withContext(defaultDispatcher) {
            runCatching {
                service.thirtySixHours()
                    .checkIsSuccessful()
                    .requireBody()
            }
        }
    }

    override suspend fun searchLocation(forecastType: ForecastType): Result<ForecastRootResponse> {
        return withContext(defaultDispatcher) {
            runCatching {
                service.location(forecastType.path)
                    .checkIsSuccessful()
                    .requireBody()
            }
        }
    }

    override suspend fun filterLocation(forecastTypes: List<ForecastType>): Result<ForecastRootResponse> {
        return withContext(defaultDispatcher) {
            runCatching {
                val locationId = forecastTypes.joinToString(",") {
                    it.path
                }
                service.chooseLocation(locationId = locationId)
                    .checkIsSuccessful()
                    .requireBody()
            }
        }
    }

    override suspend fun tidal(): Result<TidalRootResponse> {
        return withContext(defaultDispatcher) {
            runCatching {
                service.tidal()
                    .checkIsSuccessful()
                    .requireBody()
            }
        }
    }
}