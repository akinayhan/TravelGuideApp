package com.akinayhan.travelguideapp.data.datasource.locale

import com.akinayhan.travelguideapp.data.datasource.TravelDataSource
import com.akinayhan.travelguideapp.data.datasource.TripDataSource
import com.akinayhan.travelguideapp.data.db.TravelDao
import com.akinayhan.travelguideapp.data.model.categories.GuideCategory
import com.akinayhan.travelguideapp.data.model.travel.Travel
import com.akinayhan.travelguideapp.util.constants.Resource
import com.akinayhan.travelguideapp.util.networking.TravelService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class TripLocaleDataSource @Inject constructor(private var travelDao: TravelDao
) : TripDataSource {

    override suspend fun getMyTrips(): Flow<Resource<List<Travel>>> = flow {
        emit(Resource.Loading(true))
        try {
            emit(Resource.Success(travelDao.getMyTrips()))
        } catch (ex: Exception) {
            emit(Resource.Error(ex.localizedMessage))
        }
    }

    override suspend fun addTrip(travelBody: Travel): Flow<Resource<Travel>>  = flow {

        travelDao.Insert(travelBody)

        emit(Resource.Loading(true))
        try {

            emit(Resource.Success(null))
        } catch (ex: Exception) {
            emit(Resource.Error(ex.localizedMessage))
        }
    }

}