package com.farris.beauty.time.sdjdi.module.web

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