package com.farris.beauty.time.sdjdi.module.service.response.thirtysixhours

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ThirtySixLocationResponse(

    @SerialName("locationName")
    val locationName: String? = null,

    @SerialName("weatherElement")
    val weatherElements: List<ThirtySixWeatherElementResponse>? = null,
)
