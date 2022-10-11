package com.akinayhan.travelguideapp.data.model.travel


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Image(
    @SerializedName("altText")
    var altText: String? = null,
    @SerializedName("height")
    var height: Int? = null,
    @SerializedName("isHeroImage")
    var isHeroImage: Boolean=false,
    @SerializedName("url")
    var url: String? = null,
    @SerializedName("width")
    var width: Int? = null
) : Parcelable