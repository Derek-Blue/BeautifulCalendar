package com.farris.beauty.time.sdjdi.model.repository

data class RepositoryLocationForecast(
    val path: String,
    val county: String,
    val township: String,
    val geoCode: String,
    val lat: String,
    val lon: String,
    val elementName: String,
    val startTime: String, // "yyyy-MM-dd hh:mm:ss"
    val endTime: String,
    val elementValue: String,
    val elementMeasures: String,
)
