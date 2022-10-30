package com.farris.beauty.time.sdjdi.module.database

import androidx.room.*
import com.farris.beauty.time.sdjdi.module.repository.RepositoryLocationForecast

@Dao
interface ForecastDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(entities: List<RepositoryLocationForecast>)

    @Query("DELETE FROM Forecast_Table WHERE path=:countyPath ")
    fun delete(countyPath: String)

    @Query("SELECT * FROM Forecast_Table WHERE path=:countyPath AND elementName IN (:elements) ORDER BY startTime ASC")
    fun select(countyPath: String, elements: List<String>): List<RepositoryLocationForecast>
}