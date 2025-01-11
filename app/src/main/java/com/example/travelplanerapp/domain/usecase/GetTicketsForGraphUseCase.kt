package com.example.travelplanerapp.domain.usecase

import com.example.travelplanerapp.data.entity.TicketEntity
import com.example.travelplanerapp.domain.repository.TravelRepository
import javax.inject.Inject

interface GetTicketsForGraphUseCase {

    suspend operator fun invoke(routeId: Int): List<TicketEntity>
}

class GetTicketsForGraphUseCaseImpl @Inject constructor(
    private val travelRepository: TravelRepository
) : GetTicketsForGraphUseCase {

    override suspend fun invoke(routeId: Int): List<TicketEntity> =
        travelRepository.getTickets(routeId)
}
