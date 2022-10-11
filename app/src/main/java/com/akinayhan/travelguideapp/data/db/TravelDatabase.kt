package com.akinayhan.travelguideapp.data.db



import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.akinayhan.travelguideapp.data.model.travel.Travel

@TypeConverters(Converter::class)
@Database(entities = [Travel::class], version = 1)
abstract class TravelDatabase : RoomDatabase() {
    abstract fun travelDao(): TravelDao
}
