package com.farris.beauty.time.sdjdi.module.remoteconfig

import com.farris.beauty.time.sdjdi.R
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class FireBaseRemoteConfigImpl(
    private val dispatcher: CoroutineContext = Dispatchers.IO
): RemoteConfig {

    companion object {
        private const val FETCH_TIME_OUT_IN_SECONDS = 5L
        private const val MINIMUM_FETCH_INTERVAL_IN_SECONDS = 900L

        private const val CWB_TOKEN_KEY = "cwb_token"
    }

    override suspend fun init(): Result<Boolean> {
        return withContext(dispatcher) {
            kotlin.runCatching {
                val firebaseRemoteConfig = FirebaseRemoteConfig.getInstance()

                val setting = FirebaseRemoteConfigSettings.Builder()
                    .setFetchTimeoutInSeconds(FETCH_TIME_OUT_IN_SECONDS)
                    .setMinimumFetchIntervalInSeconds(MINIMUM_FETCH_INTERVAL_IN_SECONDS)
                    .build()

                firebaseRemoteConfig.setConfigSettingsAsync(setting).await()
                firebaseRemoteConfig.setDefaultsAsync(R.xml.default_remote_config).await()
                firebaseRemoteConfig.fetchAndActivate().await()
            }
        }
    }

    override suspend fun getCWBToken(): String {
        return withContext(dispatcher) {
            FirebaseRemoteConfig.getInstance().getString(CWB_TOKEN_KEY)
        }
    }
}