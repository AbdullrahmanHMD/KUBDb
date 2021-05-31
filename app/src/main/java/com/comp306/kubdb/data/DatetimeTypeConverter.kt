package com.comp306.kubdb.data

import androidx.room.TypeConverter
import java.text.SimpleDateFormat
import java.util.*

class DatetimeTypeConverter {

    companion object {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        @TypeConverter
        fun fromDatetime(dateTime: String): Date? {
            return try {
                dateFormat.parse(dateTime)
            } catch (exception: Exception) {
                exception.printStackTrace()
                null
            }
        }

    }
}