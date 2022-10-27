package com.farris.beauty.time.sdjdi.model.service

import com.farris.beauty.time.sdjdi.model.service.data.CWB_TOKEN
import com.farris.beauty.time.sdjdi.model.service.response.ForecastRootResponse
import com.farris.beauty.time.sdjdi.model.service.response.thirtysixhours.ThirtySixForecastRootResponse
import com.farris.beauty.time.sdjdi.model.service.response.tidal.TidalRootResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface ForecastApiService {

    @GET("/v1/rest/datastore/{path}")
    suspend fun thirtySixHours(
        @Path("path") path: String = ForecastType.ThirtySixHours.path,
        @Header("Authorization") auth: String = CWB_TOKEN
    ): Response<ThirtySixForecastRootResponse>

    @GET("/v1/rest/datastore/{path}")
    suspend fun location(
        @Path("path") path: String,
        @Header("Authorization") auth: String = CWB_TOKEN
    ): Response<ForecastRootResponse>

    @GET("/v1/rest/datastore/{path}")
    suspend fun chooseLocation(
        @Path("path") path: String = ForecastType.ChooseLocation.path,
        @Query("locationId") locationId: String,
        @Header("Authorization") auth: String = CWB_TOKEN
    ): Response<ForecastRootResponse>

    @GET("/v1/rest/datastore/{path}")
    suspend fun tidal(
        @Path("path") path: String = ForecastType.Tidal.path,
        @Header("Authorization") auth: String = CWB_TOKEN
    ): Response<TidalRootResponse>
}