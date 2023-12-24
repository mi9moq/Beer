package com.mironov.beerapp.data.database.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object StringCollectionConverter {

    private val gson = Gson()

    @TypeConverter
    fun to(value: List<String>): String =
        gson.toJson(value)

    @TypeConverter
    fun from(value: String): List<String> {
        val typeToken = object : TypeToken<Set<String>>() {}.type
        return gson.fromJson(value, typeToken)
    }
}