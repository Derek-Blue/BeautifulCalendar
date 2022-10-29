package com.farris.beauty.time.sdjdi.module.service.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LocationResponse(

    @SerialName("locationName")
    val locationName: String? = null,

    @SerialName("geocode")
    val geocode: String? = null,

    @SerialName("lat")
    val lat: String? = null,

    @SerialName("lon")
    val lon: String? = null,

    @SerialName("weatherElement")
    val weatherElements: List<WeatherElementResponse>? = null,
)
