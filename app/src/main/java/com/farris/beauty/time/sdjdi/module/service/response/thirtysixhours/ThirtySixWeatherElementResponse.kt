package com.farris.beauty.time.sdjdi.module.service.response.thirtysixhours

import com.farris.beauty.time.sdjdi.module.service.response.WeatherElementTimeParameterResponse
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ThirtySixWeatherElementResponse(

    @SerialName("elementName")
    val elementName: String? = null,

    @SerialName("time")
    val weatherElementTime: List<WeatherElementTimeParameterResponse>? = null,
)
