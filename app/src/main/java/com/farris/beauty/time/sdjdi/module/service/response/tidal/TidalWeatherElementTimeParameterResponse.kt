package com.farris.beauty.time.sdjdi.module.service.response.tidal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TidalWeatherElementTimeParameterResponse(

    @SerialName("parameterName")
    val parameterName: String? = null,

    @SerialName("parameterValue")
    val parameterValue: String? = null,

    @SerialName("parameterMeasure")
    val parameterMeasure: String? = null
)
