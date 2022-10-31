package com.farris.beauty.time.sdjdi.module.service

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
        @Header("Authorization") auth: String,
        @Path("path") path: String = ForecastApiType.ThirtySixHours.path,
    ): Response<ThirtySixForecastRootResponse>

    @GET("/api/v1/rest/datastore/{path}")
    suspend fun location(
        @Header("Authorization") auth: String,
        @Path("path") path: String,
        @Query("elementName")elementName: String? = null,
    ): Response<ForecastRootResponse>

    @GET("/api/v1/rest/datastore/{path}")
    suspend fun chooseLocation(
        @Header("Authorization") auth: String,
        @Path("path") path: String = ForecastApiType.ChooseLocation.path,
        @Query("locationId") locationId: String,
    ): Response<ForecastRootResponse>

    @GET("/api/v1/rest/datastore/{path}")
    suspend fun tidal(
        @Header("Authorization") auth: String,
        @Path("path") path: String = ForecastApiType.Tidal.path,
    ): Response<TidalRootResponse>
}