package com.farris.beauty.time.sdjdi.model.service.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ForecastRecordResponse(

    @SerialName("locations")
    val locations: List<ForecastRecordLocationResponse>? = null,
)
