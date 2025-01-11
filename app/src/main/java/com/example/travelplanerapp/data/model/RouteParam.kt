package com.example.travelplanerapp.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RouteParam(
    val cities: List<City>
) : Parcelable
