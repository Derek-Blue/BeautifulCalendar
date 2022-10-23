package com.farris.beauty.time.sdjdi.model.service.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ForecastResultResponse(

    @SerialName("resource_id")
    val resourceId: String? = null,

    @SerialName("fields")
    val fields: List<ForecastFieldResponse>? = null,
)
