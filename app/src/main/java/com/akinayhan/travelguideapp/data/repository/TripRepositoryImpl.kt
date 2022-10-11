package com.akinayhan.travelguideapp.data.repository

import android.content.Context
import com.akinayhan.travelguideapp.data.datasource.locale.TripLocaleDataSource
import com.akinayhan.travelguideapp.data.datasource.remote.TravelRemoteDataSource
import com.akinayhan.travelguideapp.data.model.travel.Travel
import com.akinayhan.travelguideapp.util.constants.Resource
import com.akinayhan.travelguideapp.util.networking.NetworkUtil
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class TripRepositoryImpl @Inject constructor(
    private val tripLocaleDataSource: TripLocaleDataSource
    ): TripRepository{

    override suspend fun getMyTrips(): Flow<Resource<List<Travel>>> {
        return tripLocaleDataSource.getMyTrips()
    }

    override suspend fun addTrip(travelBody: Travel): Flow<Resource<Travel>> {
        return tripLocaleDataSource.addTrip(travelBody)
    }
}