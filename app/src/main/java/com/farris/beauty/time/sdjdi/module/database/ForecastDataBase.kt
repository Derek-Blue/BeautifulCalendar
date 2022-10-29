package com.farris.beauty.time.sdjdi.module.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.farris.beauty.time.sdjdi.MyApplication
import com.farris.beauty.time.sdjdi.module.repository.RepositoryLocationForecast

@Database(
    entities = [RepositoryLocationForecast::class],
    version = 1
)
abstract class ForecastDataBase: RoomDatabase() {

    abstract fun forecastDao(): ForecastDao

    companion object {

        private const val FORECAST_DATABASE_NAME = "ForecastDataBase"

        @Volatile
        private var INSTANCE: ForecastDataBase? = null

        fun getInstance(): ForecastDataBase {
            synchronized(this) {
                return INSTANCE ?: Room.databaseBuilder(
                    MyApplication.instance.applicationContext,
                    ForecastDataBase::class.java,
                    FORECAST_DATABASE_NAME
                ).build().also {
                    INSTANCE = it
                }
            }
        }
    }
}