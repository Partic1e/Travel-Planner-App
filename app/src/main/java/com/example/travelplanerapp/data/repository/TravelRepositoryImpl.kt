package com.example.travelplanerapp.data.repository

import com.example.travelplanerapp.data.db.RouteDao
import com.example.travelplanerapp.data.db.TicketDao
import com.example.travelplanerapp.data.entity.RouteEntity
import com.example.travelplanerapp.data.entity.TicketEntity
import com.example.travelplanerapp.data.service.TicketService
import com.example.travelplanerapp.domain.repository.TravelRepository
import javax.inject.Inject

class TravelRepositoryImpl @Inject constructor(
    private val routeDao: RouteDao,
    private val ticketDao: TicketDao,
    private val ticketService: TicketService
) : TravelRepository {

    override suspend fun getRoutes(): List<RouteEntity> =
        routeDao.getRoutesForUser()

    override suspend fun insertRoute(routeEntity: RouteEntity): Int =
        routeDao.insertRoute(routeEntity).toInt()

    override suspend fun saveTickets(tickets: List<TicketEntity>) {
        for (ticket in tickets) {
            val response = ticketService.getPricesForDates(
                origin = ticket.startCityCode,
                destination = ticket.endCityCode,
                departureAt = ticket.date,
                returnAt = null
            )

            if (response.isSuccessful) {
                ticket.price = response.body()!!.data.first().price
                ticketDao.insertTicket(ticket)
            }
        }
    }
}
