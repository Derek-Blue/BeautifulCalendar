package com.farris.beauty.time.sdjdi.model.service.response.thirtysixhours

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ThirtySixForecastRecordResponse(

    @SerialName("datasetDescription")
    val datasetDescription: String? = null,

    @SerialName("location")
    val location: List<ThirtySixLocationResponse>? = null,
)
