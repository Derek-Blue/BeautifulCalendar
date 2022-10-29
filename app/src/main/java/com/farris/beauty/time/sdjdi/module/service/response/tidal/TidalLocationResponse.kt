package com.farris.beauty.time.sdjdi.module.service.response.tidal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TidalLocationResponse(

    @SerialName("locationName")
    val locationName: String? = null,

    @SerialName("stationId")
    val stationId: String? = null,

    @SerialName("validTime")
    val validTime: List<TidalValidTimeResponse>? = null,
)
