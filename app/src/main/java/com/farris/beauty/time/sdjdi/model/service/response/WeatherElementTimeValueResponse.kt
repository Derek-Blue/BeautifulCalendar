package com.farris.beauty.time.sdjdi.model.service.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeatherElementTimeValueResponse(

    @SerialName("startTime")
    val startTime: String? = null,

    @SerialName("endTime")
    val endTime: String? = null,

    @SerialName("elementValue")
    val elementValue: List<WeatherElementValueResponse>? = null,
)
