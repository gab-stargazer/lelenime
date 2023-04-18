package com.lelestacia.lelenime.core.database.animeStuff.typeConverter

import androidx.room.TypeConverter
import java.util.Date

class DateConverter {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let {
            if (it.toInt() == 0) {
                Date()
            } else {
                Date(it)
            }
        }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }
}
