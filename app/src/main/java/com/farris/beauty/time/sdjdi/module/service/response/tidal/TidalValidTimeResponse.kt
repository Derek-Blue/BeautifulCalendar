package com.farris.beauty.time.sdjdi.module.service.response.tidal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TidalValidTimeResponse(

    @SerialName("startTime")
    val startTime: String? = null,

    @SerialName("endTime")
    val endTime: String? = null,

    @SerialName("weatherElement")
    val weatherElement: List<TidalWeatherElementResponse>? = null,
)
