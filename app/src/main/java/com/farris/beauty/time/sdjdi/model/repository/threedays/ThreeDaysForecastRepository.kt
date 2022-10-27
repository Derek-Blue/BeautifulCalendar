package com.farris.beauty.time.sdjdi.model.repository.threedays

import com.farris.beauty.time.sdjdi.model.repository.RepositoryLocationForecast
import com.farris.beauty.time.sdjdi.utils.CountyType
import com.farris.beauty.time.sdjdi.utils.WeatherElementType

interface ThreeDaysForecastRepository {

    suspend fun getData(
        countyType: CountyType,
        getTypes: List<WeatherElementType> = emptyList()
    ): Result<List<RepositoryLocationForecast>>
}