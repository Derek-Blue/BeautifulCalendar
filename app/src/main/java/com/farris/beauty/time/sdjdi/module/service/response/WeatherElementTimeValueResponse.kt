package com.farris.beauty.time.sdjdi.module.service.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonArray

@Serializable
data class WeatherElementTimeValueResponse(

    @SerialName("dataTime")
    val dataTime: String? = null,

    @SerialName("startTime")
    val startTime: String? = null,

    @SerialName("endTime")
    val endTime: String? = null,

    /**
     * @see WeatherElementValueResponse
     */
    @SerialName("elementValue")
    val elementValue: JsonArray? = null,
)
