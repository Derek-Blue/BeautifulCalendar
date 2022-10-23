package com.farris.beauty.time.sdjdi.model.service.response.tidal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TidalWeatherElementResponse(

    @SerialName("elementName")
    val elementName: String? = null,

    @SerialName("elementValue")
    val elementValue: String? = null,

    @SerialName("time")
    val time: List<TidalWeatherElementTimeResponse>? = null,
)
