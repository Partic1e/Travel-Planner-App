package com.example.travelplanerapp.domain.usecase

import com.example.travelplanerapp.data.entity.RouteEntity
import com.example.travelplanerapp.domain.repository.TravelRepository
import javax.inject.Inject

interface SaveRouteUseCase {

    suspend operator fun invoke(routeEntity: RouteEntity): Int
}

class SaveRouteUseCaseImpl @Inject constructor(
    private val travelRepository: TravelRepository
) : SaveRouteUseCase {

    override suspend operator fun invoke(routeEntity: RouteEntity): Int =
        travelRepository.insertRoute(routeEntity)
}
