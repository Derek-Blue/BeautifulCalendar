package com.farris.beauty.time.sdjdi.module.remoteconfig

interface RemoteConfig {

    suspend fun init(): Result<Boolean>

    suspend fun getCWBToken(): String
}