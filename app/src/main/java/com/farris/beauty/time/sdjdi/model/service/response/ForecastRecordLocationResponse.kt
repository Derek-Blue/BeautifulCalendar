package com.farris.beauty.time.sdjdi.model.service.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ForecastRecordLocationResponse(

    @SerialName("datasetDescription")
    val datasetDescription: String? = null,

    @SerialName("locationsName")
    val locationsName: String? = null,

    @SerialName("dataid")
    val dataId: String? = null,

    @SerialName("location")
    val location: List<LocationResponse>? = null,
)
