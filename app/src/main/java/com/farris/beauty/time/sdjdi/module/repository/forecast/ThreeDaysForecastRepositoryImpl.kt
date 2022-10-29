package com.farris.beauty.time.sdjdi.module.repository.forecast

import com.farris.beauty.time.sdjdi.module.database.ForecastDataBase
import com.farris.beauty.time.sdjdi.module.repository.RepositoryLocationForecast
import com.farris.beauty.time.sdjdi.module.repository.cachetime.ForecastCacheTimeRepository
import com.farris.beauty.time.sdjdi.module.repository.cachetime.ForecastCacheTimeRepositoryImpl
import com.farris.beauty.time.sdjdi.type.ForecastApiType
import com.farris.beauty.time.sdjdi.module.web.ForecastApiWeb
import com.farris.beauty.time.sdjdi.module.web.ServiceWebProvider
import com.farris.beauty.time.sdjdi.type.CountyType
import com.farris.beauty.time.sdjdi.type.WeatherElementType
import com.farris.beauty.time.sdjdi.utils.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class ThreeDaysForecastRepositoryImpl(
    dataBase: ForecastDataBase = ForecastDataBase.getInstance(),
    private val cacheTimeRepository: ForecastCacheTimeRepository = ForecastCacheTimeRepositoryImpl(),
    private val service: ForecastApiWeb = ServiceWebProvider.getForecastApiWeb(),
    private val dispatcher: CoroutineContext = Dispatchers.IO
) : ForecastRepository {

    companion object {
        private const val CACHE_HOLDER_TIME = ONE_SECOND_MILLISECONDS * ONE_MINUTE_SECONDS * ONE_HOUR_MINUTES * 3
    }

    private val dao = dataBase.forecastDao()

    override suspend fun getData(
        countyType: CountyType,
        getTypes: List<WeatherElementType>
    ): Result<List<RepositoryLocationForecast>> {
        return withContext(dispatcher) {
            val type = ForecastApiType.threeDaysForecastFromName(countyType._name)
            val cache = getDataFromDataBase(type.path, getTypes)
            val lastUpdateTime = cacheTimeRepository.getLastTime(type.path)
            val currentTime = getNstCalendar().timeInMillis
            val isExpire = (currentTime - lastUpdateTime) > CACHE_HOLDER_TIME
            if (cache.isEmpty() || isExpire) {
                getDataFromService(type, getTypes)
            } else {
                Result.success(cache)
            }
        }
    }

    private fun getDataFromDataBase(
        countyPath: String,
        getTypes: List<WeatherElementType>
    ): List<RepositoryLocationForecast> {
        return getTypes.map {
            dao.select(countyPath, it.elementName)
        }.flatten()
    }

    private suspend fun getDataFromService(
        type: ForecastApiType.TownShip.ThreeDays,
        getTypes: List<WeatherElementType>
    ): Result<List<RepositoryLocationForecast>> {
        dao.delete(type.path)
        return service.searchLocation(type).mapCatching { root ->
            root.records?.locations?.map { county ->
                county.location?.map { townShip ->
                    townShip.weatherElements?.map { weather ->
                        weather.weatherElementTime?.mapIndexed { index, time ->
                            time.elementValue?.map {
                                val startTime =
                                    (time.startTime ?: time.dataTime)?.let { dateStr ->
                                        dateStr.formatDate(FORECAST_FORMAT)?.timeInMillis
                                    } ?: 0
                                val endTime =
                                    time.endTime?.formatDate(FORECAST_FORMAT)?.timeInMillis ?: 0
                                val elementName = weather.elementName ?: ""
                                RepositoryLocationForecast(
                                    id = "${type.path}_${elementName}_$index",
                                    path = type.path,
                                    county = county.locationsName ?: "",
                                    township = townShip.locationName ?: "",
                                    geoCode = townShip.geocode ?: "",
                                    lat = townShip.lat ?: "",
                                    lon = townShip.lon ?: "",
                                    elementName = elementName,
                                    startTime = startTime,
                                    endTime = endTime,
                                    elementValue = it.value ?: "",
                                    elementMeasures = it.measures ?: ""
                                )
                            } ?: emptyList()
                        }?.flatten() ?: emptyList()
                    }?.flatten() ?: emptyList()
                }?.flatten() ?: emptyList()
            }?.flatten() ?: emptyList()
        }
            .onSuccess {
                dao.insert(it)
                cacheTimeRepository.updateTime(type.path)
            }
            .map { result ->
                if (getTypes.isEmpty()) {
                    result
                } else {
                    result.filter { repository ->
                        getTypes.any {
                            it.elementName == repository.elementName
                        }
                    }
                }
            }
    }
}