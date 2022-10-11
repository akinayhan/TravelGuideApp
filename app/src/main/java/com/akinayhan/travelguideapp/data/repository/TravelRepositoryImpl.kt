package com.akinayhan.travelguideapp.data.repository

import android.content.Context
import com.akinayhan.travelguideapp.data.datasource.remote.TravelRemoteDataSource
import com.akinayhan.travelguideapp.data.model.categories.GuideCategory
import com.akinayhan.travelguideapp.data.model.travel.Travel
import com.akinayhan.travelguideapp.util.constants.Resource
import com.akinayhan.travelguideapp.util.networking.NetworkUtil
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TravelRepositoryImpl @Inject constructor(
    private val context: Context,
    private val remoteDataSource: TravelRemoteDataSource
) : TravelRepository {

    override suspend fun getAllTravels(): Flow<Resource<List<Travel>>> {
        if (NetworkUtil.isNetworkConnected(context)) {
            return remoteDataSource.getTravels()
        } else {
            return remoteDataSource.getTravels()  //TODO connects to db
        }
    }

    override suspend fun getCategories(): Flow<Resource<List<GuideCategory>>> {
        if (NetworkUtil.isNetworkConnected(context)) {
            return remoteDataSource.getCategory()
        } else {
            return remoteDataSource.getCategory()  //TODO connects to db
        }
    }

    override suspend fun editTravel(travel: Travel): Flow<Resource<Travel>> {
        if (NetworkUtil.isNetworkConnected(context)) {
            return remoteDataSource.updateBookmark(travel)
        } else {
            return remoteDataSource.updateBookmark(travel)  //TODO connects to db
        }
    }
}
