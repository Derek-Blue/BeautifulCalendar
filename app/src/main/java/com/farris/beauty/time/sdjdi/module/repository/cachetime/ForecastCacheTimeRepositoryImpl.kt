package com.farris.beauty.time.sdjdi.module.repository.cachetime

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import com.farris.beauty.time.sdjdi.module.datastore.DataStoreProvider
import com.farris.beauty.time.sdjdi.utils.getNstCalendar
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class ForecastCacheTimeRepositoryImpl(
    private val dataStore: DataStore<Preferences> = DataStoreProvider.getCacheTimeTable()
) : ForecastCacheTimeRepository {

    /**
     *  [ location path ] to [ timeInMillis ]
     */

    override suspend fun updateTime(path: String) {
        val current = getNstCalendar().timeInMillis
        dataStore.edit {
            it[longPreferencesKey(path)] = current
        }
    }

    override suspend fun getLastTime(path: String): Long {
        return dataStore.data.map {
            it[longPreferencesKey(path)] ?: 0
        }.first()
    }
}