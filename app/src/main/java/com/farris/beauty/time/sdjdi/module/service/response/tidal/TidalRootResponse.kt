package com.farris.beauty.time.sdjdi.module.service.response.tidal

import com.farris.beauty.time.sdjdi.module.service.response.ForecastResultResponse
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TidalRootResponse(

    @SerialName("success")
    val success: Boolean? = null,

    @SerialName("result")
    val result: ForecastResultResponse? = null,

    @SerialName("records")
    val records: TidalRecordsResponse? = null,
)
