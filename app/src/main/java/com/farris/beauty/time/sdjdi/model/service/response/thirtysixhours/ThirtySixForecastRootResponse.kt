package com.farris.beauty.time.sdjdi.model.service.response.thirtysixhours

import com.farris.beauty.time.sdjdi.model.service.response.ForecastResultResponse
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ThirtySixForecastRootResponse(

    @SerialName("success")
    val success: Boolean? = null,

    @SerialName("result")
    val result: ForecastResultResponse? = null,

    @SerialName("records")
    val records: ThirtySixForecastRecordResponse? = null,
)
