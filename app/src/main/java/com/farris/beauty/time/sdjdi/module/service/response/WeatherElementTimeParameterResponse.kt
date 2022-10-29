package com.farris.beauty.time.sdjdi.module.service.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeatherElementTimeParameterResponse(

    @SerialName("dataTime")
    val dataTime: String? = null,

    @SerialName("startTime")
    val startTime: String? = null,

    @SerialName("endTime")
    val endTime: String? = null,

    @SerialName("parameter")
    val parameter: WeatherElementParameterResponse? = null,
)
