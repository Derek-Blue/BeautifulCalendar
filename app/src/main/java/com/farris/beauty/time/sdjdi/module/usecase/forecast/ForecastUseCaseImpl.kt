package com.farris.beauty.time.sdjdi.module.usecase

import com.farris.beauty.time.sdjdi.module.repository.forecast.ForecastRepository
import com.farris.beauty.time.sdjdi.module.repository.forecast.OneWeekForecastRepositoryImpl
import com.farris.beauty.time.sdjdi.module.repository.forecast.ThreeDaysForecastRepositoryImpl
import com.farris.beauty.time.sdjdi.type.CountyType
import com.farris.beauty.time.sdjdi.type.CycleType
import com.farris.beauty.time.sdjdi.type.WeatherElementType
import com.farris.beauty.time.sdjdi.utils.getNstCalendar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.IllegalArgumentException

class ForecastUseCaseImpl(
    private val threeDaysRepository: ForecastRepository = ThreeDaysForecastRepositoryImpl(),
    private val oneWeekRepository: ForecastRepository = OneWeekForecastRepositoryImpl()
) : ForecastUseCase {

    override suspend operator fun invoke(
        countyType: CountyType,
        cycleType: CycleType,
        weatherElementType: List<WeatherElementType>
    ): Result<Map<WeatherElementType, List<UseCaseWeatherTime>>> {
        return withContext(Dispatchers.IO) {
            val source = fromWhere(cycleType)
            source.getData(countyType, weatherElementType)
                .mapCatching { repository ->
                    val mediator = repository.groupBy ({
                        "${it.elementName}_${it.startTime}"
                    }, {
                        Mediator(
                            it.elementName,
                            it.startTime,
                            it.endTime,
                            listOf(
                                UseCaseWeatherElement(it.elementValue, it.elementMeasures)
                            )
                        )
                    }).mapNotNull { entry ->
                        if (entry.value.isEmpty()) return@mapNotNull null
                        Mediator(
                            entry.value.first().elementName,
                            entry.value.first().startTime,
                            entry.value.first().endTime,
                            entry.value.map { it.weatherElements }.flatten(),
                        )
                    }

                    mediator.groupBy({
                        WeatherElementType.fromName(it.elementName)
                            ?: throw IllegalArgumentException("Use Case not found WeatherElementType")
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
                            startTime, endTime, it.weatherElements
                        )
                    })
                }
        }
    }

    private fun fromWhere(cycleType: CycleType): ForecastRepository {
        return when (cycleType) {
            CycleType.Week -> oneWeekRepository
            CycleType.TreeDays -> threeDaysRepository
        }
    }

    private data class Mediator(
        val elementName: String,
        val startTime: Long,
        val endTime: Long,
        val weatherElements: List<UseCaseWeatherElement>
    )
}