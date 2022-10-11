package com.akinayhan.travelguideapp.data.repository

import com.akinayhan.travelguideapp.data.model.categories.GuideCategory
import com.akinayhan.travelguideapp.data.model.travel.Travel
import com.akinayhan.travelguideapp.util.constants.Resource
import kotlinx.coroutines.flow.Flow

interface TravelRepository {
    suspend fun getAllTravels(): Flow<Resource<List<Travel>>>

    suspend fun getCategories():Flow<Resource<List<GuideCategory>>>

    suspend fun editTravel(travel: Travel):Flow<Resource<Travel>>
}