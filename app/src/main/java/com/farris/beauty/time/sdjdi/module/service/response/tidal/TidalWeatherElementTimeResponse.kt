package com.farris.beauty.time.sdjdi.module.service.response.tidal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TidalWeatherElementTimeResponse(

    @SerialName("dataTime")
    val dataTime: String? = null,

    @SerialName("parameter")
    val parameter: List<TidalWeatherElementTimeParameterResponse>? = null,

)
