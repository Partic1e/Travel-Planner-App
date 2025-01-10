package com.example.travelplanerapp.data.model

data class TicketResponse(
    val success: Boolean,
    val data: List<Ticket>,
    val currency: String
)

data class Ticket(
    val price: Int
)
