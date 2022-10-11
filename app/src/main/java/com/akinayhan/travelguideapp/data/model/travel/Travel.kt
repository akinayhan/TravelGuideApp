package com.akinayhan.travelguideapp.data.model.travel


import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.akinayhan.travelguideapp.util.constants.Constants
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = Constants.TRAVEL_ROOM_TABLE_NAME)
data class Travel(
    @PrimaryKey(autoGenerate = true)
    val myId: Int = 0,
    @SerializedName("category")
    var category: String? = null,
    @SerializedName("city")
    var city: String? = null,
    @SerializedName("country")
    var country: String? = null,
    @SerializedName("description")
    var description: String? = null,
    @SerializedName("id")
    var id: String? = null,
    @SerializedName("images")
    var images: List<Image>? = null,
    @SerializedName("isBookmark")
    var isBookmark: Boolean = false,
    @SerializedName("title")
    var title: String? = null
) : Parcelable
