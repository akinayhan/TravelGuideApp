package com.akinayhan.travelguideapp.data.repository

import com.akinayhan.travelguideapp.data.model.travel.Travel
import com.akinayhan.travelguideapp.util.constants.Resource
import kotlinx.coroutines.flow.Flow

interface TripRepository {
    suspend fun getMyTrips(): Flow<Resource<List<Travel>>>

    suspend fun addTrip(travelBody: Travel): Flow<Resource<Travel>>
}