package com.farris.beauty.time.sdjdi.module.service

import com.farris.beauty.time.sdjdi.module.service.data.CWB_TOKEN
import com.farris.beauty.time.sdjdi.module.service.response.ForecastRootResponse
import com.farris.beauty.time.sdjdi.module.service.response.thirtysixhours.ThirtySixForecastRootResponse
import com.farris.beauty.time.sdjdi.module.service.response.tidal.TidalRootResponse
import com.farris.beauty.time.sdjdi.type.ForecastApiType
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface ForecastApiService {

    @GET("/api/v1/rest/datastore/{path}")
    suspend fun thirtySixHours(
        @Path("path") path: String = ForecastApiType.ThirtySixHours.path,
        @Header("Authorization") auth: String = CWB_TOKEN
    ): Response<ThirtySixForecastRootResponse>

    @GET("/api/v1/rest/datastore/{path}")
    suspend fun location(
        @Path("path") path: String,
        @Header("Authorization") auth: String = CWB_TOKEN
    ): Response<ForecastRootResponse>

    @GET("/api/v1/rest/datastore/{path}")
    suspend fun chooseLocation(
        @Path("path") path: String = ForecastApiType.ChooseLocation.path,
        @Query("locationId") locationId: String,
        @Header("Authorization") auth: String = CWB_TOKEN
    ): Response<ForecastRootResponse>

    @GET("/api/v1/rest/datastore/{path}")
    suspend fun tidal(
        @Path("path") path: String = ForecastApiType.Tidal.path,
        @Header("Authorization") auth: String = CWB_TOKEN
    ): Response<TidalRootResponse>
}