package com.example.travelplanerapp.domain.usecase

import com.example.travelplanerapp.data.entity.RouteEntity
import com.example.travelplanerapp.domain.repository.TravelRepository
import javax.inject.Inject

interface GetRoutesForUserUseCase {

    suspend operator fun invoke(): List<RouteEntity>
}

class GetRoutesForUserUseCaseImpl @Inject constructor(
    private val travelRepository: TravelRepository
) : GetRoutesForUserUseCase {

    override suspend fun invoke(): List<RouteEntity> =
        travelRepository.getRoutes()
}
