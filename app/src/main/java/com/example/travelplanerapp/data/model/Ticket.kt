package com.example.travelplanerapp.data.model

data class Ticket(
    val id: Int,
    val departureCity: String,
    val arrivalCity: String,
    val price: Double,
    val departureDate: String
)