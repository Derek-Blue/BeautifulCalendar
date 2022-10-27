package com.farris.beauty.time.sdjdi.model.web

object ServiceWebProvider {

    private var forecastApiWeb: ForecastApiWeb? = null

    fun getForecastApiWeb(): ForecastApiWeb {
        synchronized(this) {
            return forecastApiWeb ?: ForecastApiWebImpl().also {
                forecastApiWeb = it
            }
        }
    }
}