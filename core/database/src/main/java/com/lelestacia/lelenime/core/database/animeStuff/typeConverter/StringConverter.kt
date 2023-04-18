package com.lelestacia.lelenime.core.database.animeStuff.typeConverter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class StringConverter {

    @TypeConverter
    fun stringToList(stringParam: String): List<String> {
        val type = object : TypeToken<List<String>>() {}.type
        return if (stringParam.isEmpty()) {
            emptyList()
        } else {
            Gson().fromJson(stringParam, type)
        }
    }

    @TypeConverter
    fun listToString(listParam: List<String>): String {
        val type = object : TypeToken<List<String>>() {}.type
        return if (listParam.isEmpty()) {
            ""
        } else {
            Gson().toJson(listParam, type)
        }
    }
}
