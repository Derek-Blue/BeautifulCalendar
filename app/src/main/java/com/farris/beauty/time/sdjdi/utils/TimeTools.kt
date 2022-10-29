package com.farris.beauty.time.sdjdi.utils

import android.os.Looper
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

val TWTimeZone: TimeZone = TimeZone.getTimeZone("Asia/Taipei")

const val ONE_SECOND_MILLISECONDS = 1000L
const val ONE_MINUTE_SECONDS = 60L
const val ONE_HOUR_MINUTES = 60L
const val ONE_DAY_HOURS = 24L
const val ONE_WEEK_DAYS = 7L

const val FORECAST_SERVICE_TIME_RULE = "yyyy-MM-dd hh:mm:ss"

val FORECAST_FORMAT by lazy { SimpleDateFormat(FORECAST_SERVICE_TIME_RULE, Locale.TAIWAN) }

fun getNstCalendar(): Calendar {
    return Calendar.getInstance(TWTimeZone, Locale.TAIWAN)
}

fun getNstCalendar(timeInMillis: Long): Calendar {
    return getNstCalendar().apply {
        setTimeInMillis(timeInMillis)
    }
}

fun String.formatDate(format: SimpleDateFormat): Calendar? {
    return if (this.isBlank()) {
        null
    }else {
        try {
            val result = getThreadSafeFormatter(format).parse(this) ?: Date()
            val calendar = Calendar.getInstance()
            calendar.time = result
            calendar
        } catch (e: ParseException) {
            null
        }
    }
}

fun getThreadSafeFormatter(formatter: SimpleDateFormat): SimpleDateFormat {
    return if (isOnMainThread()) {
        formatter
    } else {
        formatter.clone() as SimpleDateFormat
    }
}

private fun isOnMainThread(): Boolean {
    return Looper.myLooper() == Looper.getMainLooper()
}