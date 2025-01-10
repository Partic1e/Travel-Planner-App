package com.example.travelplanerapp.domain.usecase

import com.example.travelplanerapp.data.entity.TicketEntity
import com.example.travelplanerapp.domain.repository.TravelRepository
import javax.inject.Inject

interface SaveTicketsUseCase {

    suspend operator fun invoke(tickets: List<TicketEntity>)
}

class SaveTicketsUseCaseImpl @Inject constructor(
    private val travelRepository: TravelRepository
) : SaveTicketsUseCase {

    override suspend fun invoke(tickets: List<TicketEntity>) {
        travelRepository.saveTickets(tickets)
    }
}
