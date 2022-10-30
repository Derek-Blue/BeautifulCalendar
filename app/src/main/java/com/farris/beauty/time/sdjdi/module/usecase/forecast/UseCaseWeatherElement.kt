package com.farris.beauty.time.sdjdi.module.usecase.forecast

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UseCaseWeatherElement(
    @SerialName("value")
    val value: String,

    @SerialName("measures")
    val measures: String,
)
