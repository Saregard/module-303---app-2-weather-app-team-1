package com.example.firstactivity.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Sys(
    @SerializedName("country")
    val country: String? = "",
    @SerializedName("id")
    val id: Int? = 0,
    @SerializedName("sunrise")
    val sunrise: Int? = 0,
    @SerializedName("sunset")
    val sunset: Int? = 0,
    @SerializedName("type")
    val type: Int? = 0
) : Parcelable
