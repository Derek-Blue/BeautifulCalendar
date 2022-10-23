package com.farris.beauty.time.sdjdi.model.service.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeatherElementTimeParameterResponse(

    @SerialName("startTime")
    val startTime: String? = null,

    @SerialName("endTime")
    val endTime: String? = null,

    @SerialName("parameter")
    val parameter: WeatherElementParameterResponse? = null,
)
