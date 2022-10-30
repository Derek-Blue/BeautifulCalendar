package com.farris.beauty.time.sdjdi.module.usecase.forecast.singleelement

import com.farris.beauty.time.sdjdi.module.repository.forecast.ForecastRepository
import com.farris.beauty.time.sdjdi.module.repository.forecast.OneWeekForecastRepositoryImpl
import com.farris.beauty.time.sdjdi.module.repository.forecast.ThreeDaysForecastRepositoryImpl
import com.farris.beauty.time.sdjdi.module.usecase.forecast.UseCaseWeatherElement
import com.farris.beauty.time.sdjdi.module.usecase.forecast.UseCaseWeatherTime
import com.farris.beauty.time.sdjdi.type.CountyType
import com.farris.beauty.time.sdjdi.type.CycleType
import com.farris.beauty.time.sdjdi.type.WeatherElementType
import com.farris.beauty.time.sdjdi.utils.getNstCalendar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.IllegalArgumentException

class SingleElementForecastUseCaseImpl(
    private val threeDaysRepository: ForecastRepository = ThreeDaysForecastRepositoryImpl(),
    private val oneWeekRepository: ForecastRepository = OneWeekForecastRepositoryImpl()
): SingleElementForecastUseCase {

    override suspend fun invoke(
        countyType: CountyType,
        cycleType: CycleType,
        weatherElementType: WeatherElementType
    ): Result<Map<String, List<UseCaseWeatherTime>>> {
        return withContext(Dispatchers.IO) {
            val source = fromWhere(cycleType)
            source.getData(countyType, listOf(weatherElementType))
                .mapCatching { repository ->
                    repository.groupBy({
                        "${it.township}_${it.elementName}_${it.startTime}"
                    }, {
                        val startTime = if (it.startTime > 0) {
                            getNstCalendar(it.startTime)
                        } else {
                            throw IllegalArgumentException("Use Case get startTime is zero")
                        }

                        val endTime = if (it.endTime > 0) {
                            getNstCalendar(it.endTime)
                        } else {
                            null
                        }

                        UseCaseWeatherTime(
                            it.township,
                            it.elementName,
                            startTime,
                            endTime,
                            listOf(
                                UseCaseWeatherElement(it.elementValue, it.elementMeasures)
                            )
                        )
                    }).mapNotNull { entry ->
                        if (entry.value.isEmpty()) return@mapNotNull null
                        UseCaseWeatherTime(
                            entry.value.first().townShip,
                            entry.value.first().elementName,
                            entry.value.first().startTime,
                            entry.value.first().endTime,
                            entry.value.map { it.weatherElements }.flatten(),
                        )
                    }.groupBy { it.townShip }
                }
        }
    }

    private fun fromWhere(cycleType: CycleType): ForecastRepository {
        return when (cycleType) {
            CycleType.Week -> oneWeekRepository
            CycleType.TreeDays -> threeDaysRepository
        }
    }

}