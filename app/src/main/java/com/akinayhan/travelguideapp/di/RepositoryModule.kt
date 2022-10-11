package com.akinayhan.travelguideapp.di

import android.content.Context
import com.akinayhan.travelguideapp.data.datasource.locale.TripLocaleDataSource
import com.akinayhan.travelguideapp.data.datasource.remote.TravelRemoteDataSource
import com.akinayhan.travelguideapp.data.repository.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun provideTravelRepository(@ApplicationContext appContext: Context,
                                 remoteDataSource: TravelRemoteDataSource,

                                 ): TravelRepository {
        return TravelRepositoryImpl(appContext,remoteDataSource)
    }

    @Provides
    @Singleton
    fun provideTripRepository(
        localeDataSource: TripLocaleDataSource): TripRepository {
        return TripRepositoryImpl(localeDataSource)
    }


}