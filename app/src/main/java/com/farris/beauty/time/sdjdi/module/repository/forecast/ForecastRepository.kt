package com.farris.beauty.time.sdjdi.module.repository.forecast

import com.farris.beauty.time.sdjdi.module.repository.RepositoryLocationForecast
import com.farris.beauty.time.sdjdi.type.CountyType
import com.farris.beauty.time.sdjdi.type.WeatherElementType

interface ForecastRepository {

    suspend fun getData(
        countyType: CountyType,
        getTypes: List<WeatherElementType> = emptyList()
    ): Result<List<RepositoryLocationForecast>>
}