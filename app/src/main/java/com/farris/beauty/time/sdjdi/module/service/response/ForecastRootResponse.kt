package com.farris.beauty.time.sdjdi.module.service.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ForecastRootResponse(

    @SerialName("success")
    val success: Boolean? = null,

    @SerialName("result")
    val result: ForecastResultResponse? = null,

    @SerialName("records")
    val records: ForecastRecordResponse? = null,
)
