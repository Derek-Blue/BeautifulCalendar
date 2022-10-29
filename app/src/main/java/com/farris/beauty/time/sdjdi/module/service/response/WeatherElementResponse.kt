package com.farris.beauty.time.sdjdi.module.service.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeatherElementResponse(

    @SerialName("elementName")
    val elementName: String? = null,

    @SerialName("description")
    val description: String? = null,

    @SerialName("time")
    val weatherElementTime: List<WeatherElementTimeValueResponse>? = null,
)
