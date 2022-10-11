package com.akinayhan.travelguideapp.data.datasource

import com.akinayhan.travelguideapp.data.model.categories.GuideCategory
import com.akinayhan.travelguideapp.data.model.travel.Travel
import com.akinayhan.travelguideapp.util.constants.Resource

import kotlinx.coroutines.flow.Flow

interface TravelDataSource {
    suspend fun getTravels(): Flow<Resource<List<Travel>>>

    suspend fun getCategory(): Flow<Resource<List<GuideCategory>>>

    suspend fun updateBookmark(travel: Travel): Flow<Resource<Travel>>


}