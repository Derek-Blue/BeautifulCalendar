package com.farris.beauty.time.sdjdi.module.repository.forecast

import com.farris.beauty.time.sdjdi.module.database.ForecastDataBase
import com.farris.beauty.time.sdjdi.module.repository.RepositoryLocationForecast
import com.farris.beauty.time.sdjdi.module.repository.cachetime.ForecastCacheTimeRepository
import com.farris.beauty.time.sdjdi.module.repository.cachetime.ForecastCacheTimeRepositoryImpl
import com.farris.beauty.time.sdjdi.module.web.ForecastApiWeb
import com.farris.beauty.time.sdjdi.module.web.ServiceWebProvider
import com.farris.beauty.time.sdjdi.type.CountyType
import com.farris.beauty.time.sdjdi.type.ForecastApiType
import com.farris.beauty.time.sdjdi.type.WeatherElementType
import com.farris.beauty.time.sdjdi.utils.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class OneWeekForecastRepositoryImpl(
    dataBase: ForecastDataBase = ForecastDataBase.getInstance(),
    private val cacheTimeRepository: ForecastCacheTimeRepository = ForecastCacheTimeRepositoryImpl(),
    private val service: ForecastApiWeb = ServiceWebProvider.getForecastApiWeb(),
    private val dispatcher: CoroutineContext = Dispatchers.IO
) : ForecastRepository {

    companion object {
        private const val CACHE_HOLDER_TIME =
            ONE_SECOND_MILLISECONDS * ONE_MINUTE_SECONDS * ONE_HOUR_MINUTES * 6
    }

    private val dao = dataBase.forecastDao()

    override suspend fun getData(
        countyType: CountyType,
        getTypes: List<WeatherElementType>
    ): Result<List<RepositoryLocationForecast>> {
        return withContext(dispatcher) {
            val type = ForecastApiType.oneWeekForecastFromName(countyType._name)
            val cache = getTypes.map {
                getDataFromDataBase(type.path, it)
            }

            if (isExpire(type) || cache.any { it.isEmpty() } ) {
                getDataFromService(type, getTypes)
            } else {
                Result.success(cache.flatten())
            }
        }
    }

    private suspend fun isExpire(type: ForecastApiType.TownShip.OneWeek): Boolean {
        val lastUpdateTime = cacheTimeRepository.getLastTime(type.path)
        val currentTime = getNstCalendar().timeInMillis
        return if ((currentTime - lastUpdateTime) > CACHE_HOLDER_TIME) {
            dao.delete(type.path)
            true
        } else {
            false
        }
    }

    private fun getDataFromDataBase(
        countyPath: String,
        getType: WeatherElementType
    ): List<RepositoryLocationForecast> {
        return dao.select(countyPath, listOf(getType.elementName))
    }

    private suspend fun getDataFromService(
        type: ForecastApiType.TownShip.OneWeek,
        getTypes: List<WeatherElementType>
    ): Result<List<RepositoryLocationForecast>> {
        return service.searchLocation(type, getTypes).mapCatching { root ->
            root.records?.locations?.map { county ->
                county.location?.map { townShip ->
                    townShip.weatherElements?.map { weather ->
                        weather.weatherElementTime?.mapIndexed { index, time ->
                                val startTime =
                                    (time.startTime ?: time.dataTime)?.let { dateStr ->
                                        dateStr.formatDate(FORECAST_FORMAT)?.timeInMillis
                                    } ?: 0
                                val endTime =
                                    time.endTime?.formatDate(FORECAST_FORMAT)?.timeInMillis ?: 0
                                val elementName = weather.elementName ?: ""
                                RepositoryLocationForecast(
                                    id = "${type.path}_${townShip.geocode}_${elementName}_$index",
                                    path = type.path,
                                    county = county.locationsName ?: "",
                                    township = townShip.locationName ?: "",
                                    geoCode = townShip.geocode ?: "",
                                    lat = townShip.lat ?: "",
                                    lon = townShip.lon ?: "",
                                    elementName = elementName,
                                    startTime = startTime,
                                    endTime = endTime,
                                    elementValue = time.elementValue.toString(),
                                )
                        } ?: emptyList()
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