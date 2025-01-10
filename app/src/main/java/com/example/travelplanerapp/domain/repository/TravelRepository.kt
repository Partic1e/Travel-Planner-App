package com.example.travelplanerapp.domain.repository

import com.example.travelplanerapp.data.entity.RouteEntity
import com.example.travelplanerapp.data.entity.TicketEntity

interface TravelRepository {

    suspend fun getRoutes(): List<RouteEntity>

    suspend fun insertRoute(routeEntity: RouteEntity): Int

    suspend fun saveTickets(tickets: List<TicketEntity>)
}
