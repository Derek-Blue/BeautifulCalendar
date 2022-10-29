package com.farris.beauty.time.sdjdi.module.service.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeatherElementParameterResponse(

    @SerialName("parameterName")
    val parameterName: String? = null,

    @SerialName("parameterUnit")
    val parameterUnit: String? = null,
)
