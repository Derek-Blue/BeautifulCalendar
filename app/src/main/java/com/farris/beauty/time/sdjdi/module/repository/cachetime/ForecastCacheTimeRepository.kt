package com.farris.beauty.time.sdjdi.module.repository.cachetime

interface ForecastCacheTimeRepository {

    suspend fun updateTime(path: String)

    suspend fun getLastTime(path: String): Long
}