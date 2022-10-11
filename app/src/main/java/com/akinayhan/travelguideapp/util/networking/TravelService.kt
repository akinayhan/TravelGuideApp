package com.akinayhan.travelguideapp.util.networking

import com.akinayhan.travelguideapp.data.model.categories.GuideCategory
import com.akinayhan.travelguideapp.data.model.travel.Travel
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

interface TravelService {
    @GET("AllTravelList")
    suspend fun getTravel(): List<Travel>

    @GET("GuideCategories")
    suspend fun getGuideCategories(): List<GuideCategory>

    @PUT("AllTravelList/{id}")
    suspend fun updateBookmark(
        @Body travelBody: Travel,
        @Path("id") travelID: String
    ): Travel

}