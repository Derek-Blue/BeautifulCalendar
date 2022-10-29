package com.farris.beauty.time.sdjdi.module.repository

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Forecast_Table")
data class RepositoryLocationForecast(
    @PrimaryKey val id: String,
    val path: String,
    val county: String,
    val township: String,
    val geoCode: String,
    val lat: String,
    val lon: String,
    val elementName: String,
    val startTime: Long,
    val endTime: Long,
    val elementValue: String,
    val elementMeasures: String,
)
