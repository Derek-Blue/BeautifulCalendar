package com.farris.beauty.time.sdjdi.module.web

import com.farris.beauty.time.sdjdi.module.remoteconfig.FireBaseRemoteConfigImpl
import com.farris.beauty.time.sdjdi.module.remoteconfig.RemoteConfig
import com.farris.beauty.time.sdjdi.module.service.ForecastApiService
import com.farris.beauty.time.sdjdi.module.service.RetrofitManager
import com.farris.beauty.time.sdjdi.module.service.data.checkIsSuccessful
import com.farris.beauty.time.sdjdi.module.service.data.requireBody
import com.farris.beauty.time.sdjdi.module.service.response.ForecastRootResponse
import com.farris.beauty.time.sdjdi.module.service.response.thirtysixhours.ThirtySixForecastRootResponse
import com.farris.beauty.time.sdjdi.module.service.response.tidal.TidalRootResponse
import com.farris.beauty.time.sdjdi.type.ForecastApiType
import com.farris.beauty.time.sdjdi.type.WeatherElementType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class ForecastApiWebImpl(
    private val remoteConfig: RemoteConfig = FireBaseRemoteConfigImpl(),
    private val service: ForecastApiService = RetrofitManager.getForecastApi,
    private val defaultDispatcher: CoroutineContext = Dispatchers.IO
) : ForecastApiWeb {

    override suspend fun getThirtySixHours(): Result<ThirtySixForecastRootResponse> {
        return withContext(defaultDispatcher) {
            runCatching {
                service.thirtySixHours(remoteConfig.getCWBToken())
                    .checkIsSuccessful()
                    .requireBody()
            }
        }
    }

    override suspend fun searchLocation(
        forecastApiType: ForecastApiType,
        elements: List<WeatherElementType>
    ): Result<ForecastRootResponse> {
        return withContext(defaultDispatcher) {
            runCatching {
                val elementName = if (elements.isNotEmpty()) {
                    elements.joinToString { it.elementName }
                } else {
                    null
                }
                service.location(
                    auth = remoteConfig.getCWBToken(),
                    path = forecastApiType.path,
                    elementName = elementName
                )
                    .checkIsSuccessful()
                    .requireBody()
            }
        }
    }

    override suspend fun filterLocation(forecastApiTypes: List<ForecastApiType>): Result<ForecastRootResponse> {
        return withContext(defaultDispatcher) {
            runCatching {
                val locationId = forecastApiTypes.joinToString(",") {
                    it.path
                }
                service.chooseLocation(locationId = locationId, auth = remoteConfig.getCWBToken())
                    .checkIsSuccessful()
                    .requireBody()
            }
        }
    }

    override suspend fun tidal(): Result<TidalRootResponse> {
        return withContext(defaultDispatcher) {
            runCatching {
                service.tidal(remoteConfig.getCWBToken())
                    .checkIsSuccessful()
                    .requireBody()
            }
        }
    }
}