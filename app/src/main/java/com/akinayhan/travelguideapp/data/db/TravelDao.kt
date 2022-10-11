package com.akinayhan.travelguideapp.data.db

import androidx.room.*
import com.akinayhan.travelguideapp.data.model.travel.Travel
import com.akinayhan.travelguideapp.util.constants.Constants


@Dao
interface TravelDao {


    @Query("SELECT * FROM ${Constants.TRAVEL_ROOM_TABLE_NAME}")
    fun getMyTrips(): List<Travel>

    @Insert
    fun Insert(travel: Travel?)


}