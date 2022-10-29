package com.farris.beauty.time.sdjdi.module.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import com.farris.beauty.time.sdjdi.MyApplication
import java.io.File

object DataStoreProvider {

    private var cacheTimePreferences: DataStore<Preferences>? = null

    @Synchronized
    fun getCacheTimeTable(): DataStore<Preferences> {
        return cacheTimePreferences ?: PreferenceDataStoreFactory.create(
            produceFile = {
                File(MyApplication.instance.filesDir, "datastore/cache_time.preferences_pb")
            }
        )
    }


}