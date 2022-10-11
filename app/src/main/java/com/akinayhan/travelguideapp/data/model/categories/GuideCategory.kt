package com.akinayhan.travelguideapp.data.model.categories


import com.google.gson.annotations.SerializedName

data class GuideCategory(
    @SerializedName("icon")
    val icon: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("title")
    val title: String
)