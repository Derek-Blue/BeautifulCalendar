package com.farris.beauty.time.sdjdi.model.repository.threedays

import com.farris.beauty.time.sdjdi.model.repository.RepositoryLocationForecast
import com.farris.beauty.time.sdjdi.model.service.ForecastType
import com.farris.beauty.time.sdjdi.model.web.ForecastApiWeb
import com.farris.beauty.time.sdjdi.model.web.ServiceWebProvider
import com.farris.beauty.time.sdjdi.utils.CountyType
import com.farris.beauty.time.sdjdi.utils.WeatherElementType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class ThreeDaysForecastRepositoryImpl(
    private val service: ForecastApiWeb = ServiceWebProvider.getForecastApiWeb(),
    private val defaultDispatcher: CoroutineContext = Dispatchers.IO
) : ThreeDaysForecastRepository {

    override suspend fun getData(
        countyType: CountyType,
        getTypes: List<WeatherElementType>
    ): Result<List<RepositoryLocationForecast>> {
        return withContext(defaultDispatcher) {
            val type = ForecastType.threeDaysForecastFromName(countyType._name)
            service.searchLocation(type).mapCatching { root ->
                root.records?.locations?.map { county ->
                    county.location?.map { townShip ->
                        townShip.weatherElements?.map { weather ->
                           weather.weatherElementTime?.map { time ->
                                time.elementValue?.map {
                                    RepositoryLocationForecast(
                                        path = type.path,
                                        county = county.locationsName ?: "",
                                        township = townShip.locationName ?: "",
                                        geoCode = townShip.geocode ?: "",
                                        lat = townShip.lat ?: "",
                                        lon = townShip.lon ?: "",
                                        elementName = weather.elementName ?: "",
                                        startTime = time.startTime ?: "",
                                        endTime = time.endTime ?: "",
                                        elementValue = it.value ?: "",
                                        elementMeasures = it.measures ?: ""
                                    )
                                } ?: emptyList()
                            }?.flatten() ?: emptyList()
                        }?.flatten() ?: emptyList()
                    }?.flatten() ?: emptyList()
                }?.flatten() ?: emptyList()
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
}