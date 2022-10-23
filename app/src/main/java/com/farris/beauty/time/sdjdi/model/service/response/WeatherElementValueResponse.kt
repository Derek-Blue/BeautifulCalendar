package com.farris.beauty.time.sdjdi.model.service.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeatherElementValueResponse(

    @SerialName("value")
    val value: String? = null,

    @SerialName("measures")
    val measures: String? = null,
)
