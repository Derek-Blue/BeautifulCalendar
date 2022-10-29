package com.farris.beauty.time.sdjdi.module.service.response.tidal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TidalRecordsResponse(

    @SerialName("dataid")
    val dataId: String? = null,

    @SerialName("note")
    val note: String? = null,

    @SerialName("location")
    val location: List<TidalLocationResponse>? = null,
)
