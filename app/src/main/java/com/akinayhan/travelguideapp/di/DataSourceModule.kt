package com.akinayhan.travelguideapp.di

import com.akinayhan.travelguideapp.data.datasource.TravelDataSource
import com.akinayhan.travelguideapp.data.datasource.remote.TravelRemoteDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    /*
    @Binds
    @Singleton
    abstract fun bindTravelDataSource(
        dataSource: TravelRemoteDataSource
    ):TravelDataSource

     */
}