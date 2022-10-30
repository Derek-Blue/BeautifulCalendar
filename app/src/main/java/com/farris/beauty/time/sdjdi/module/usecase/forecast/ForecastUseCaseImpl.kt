package com.farris.beauty.time.sdjdi.module.usecase.forecast

import com.farris.beauty.time.sdjdi.module.repository.forecast.ForecastRepository
import com.farris.beauty.time.sdjdi.module.repository.forecast.OneWeekForecastRepositoryImpl
import com.farris.beauty.time.sdjdi.module.repository.forecast.ThreeDaysForecastRepositoryImpl
import com.farris.beauty.time.sdjdi.module.service.RetrofitManager
import com.farris.beauty.time.sdjdi.type.CountyType
import com.farris.beauty.time.sdjdi.type.CycleType
import com.farris.beauty.time.sdjdi.type.WeatherElementType
import com.farris.beauty.time.sdjdi.utils.getNstCalendar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json

class ForecastUseCaseImpl(
    private val threeDaysRepository: ForecastRepository = ThreeDaysForecastRepositoryImpl(),
    private val oneWeekRepository: ForecastRepository = OneWeekForecastRepositoryImpl(),
    private val json: Json = RetrofitManager.getBaseJson()
) : ForecastUseCase {

    override suspend operator fun invoke(
        countyType: CountyType,
        cycleType: CycleType,
        weatherElementType: List<WeatherElementType>
    ): Result<Map<String, Map<WeatherElementType, List<UseCaseWeatherTime>>>> {
        return withContext(Dispatchers.IO) {
            val source = fromWhere(cycleType)
            source.getData(countyType, weatherElementType)
                .mapCatching { repository ->
                    repository.groupBy({
                        it.township
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
                            json.decodeFromString(
                                ListSerializer(UseCaseWeatherElement.serializer()),
                                it.elementValue
                            )
                        )
                    }).mapValues { entry ->
                        val values = entry.value
                        values.groupBy {
                            WeatherElementType.fromName(it.elementName)
                                ?: throw IllegalArgumentException("Use Case not found WeatherElementType")
                        }
                    }
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