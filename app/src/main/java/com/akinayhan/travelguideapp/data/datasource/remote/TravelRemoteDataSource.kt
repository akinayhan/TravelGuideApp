package com.akinayhan.travelguideapp.data.datasource.remote

import com.akinayhan.travelguideapp.data.datasource.TravelDataSource
import com.akinayhan.travelguideapp.data.model.categories.GuideCategory
import com.akinayhan.travelguideapp.data.model.travel.Travel
import com.akinayhan.travelguideapp.util.constants.Resource
import com.akinayhan.travelguideapp.util.networking.TravelService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class TravelRemoteDataSource @Inject constructor(var service: TravelService) : TravelDataSource {

    override suspend fun getTravels(): Flow<Resource<List<Travel>>> = flow {

        emit(Resource.Loading(true))
        try {
            emit(Resource.Success(service.getTravel()))
        } catch (ex: Exception) {
            emit(Resource.Error(ex.localizedMessage))
        }

    }

    override suspend fun getCategory(): Flow<Resource<List<GuideCategory>>> = flow{
        emit(Resource.Loading(true))
        try {
            emit(Resource.Success(service.getGuideCategories()))
        } catch (ex: Exception) {
            emit(Resource.Error(ex.localizedMessage))
        }
    }

    override suspend fun updateBookmark(travel: Travel): Flow<Resource<Travel>> = flow{
        emit(Resource.Loading(true))
        try {
            emit(Resource.Success(service.updateBookmark(travel,travel.id!!)))
        } catch (ex: Exception) {
            emit(Resource.Error(ex.localizedMessage))
        }
    }


}
