package com.akinayhan.travelguideapp.data.db

import androidx.room.TypeConverter
import com.akinayhan.travelguideapp.data.model.travel.Image
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json


class Converter {

    @TypeConverter
    fun fromImageList(value: List<Image>): String {
        val gson = Gson()
        val type = object : TypeToken<List<Image>>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toList(value: String): List<Image> {
        val gson = Gson()
        val type = object : TypeToken<List<Image>>() {}.type
        return gson.fromJson(value, type)
    }
}