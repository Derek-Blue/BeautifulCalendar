package com.farris.beauty.time.sdjdi.module.service.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ForecastFieldResponse(

    @SerialName("id")
    val id: String? = null,

    @SerialName("type")
    val type: String? = null,
)
