package com.example.travelplanerapp.data.model

data class Ticket(
    val id: Int,
    val startCity: String,
    val endCity: String,
    val date: Long,
    val price: Int
)
