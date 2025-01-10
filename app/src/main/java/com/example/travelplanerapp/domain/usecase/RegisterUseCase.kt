package com.example.travelplanerapp.domain.usecase

import com.example.travelplanerapp.data.entity.UserEntity
import com.example.travelplanerapp.domain.repository.UserRepository
import javax.inject.Inject

interface RegisterUseCase {

    suspend operator fun invoke(userEntity: UserEntity)
}

class RegisterUseCaseImpl @Inject constructor(
    private val userRepository: UserRepository
) : RegisterUseCase {

    override suspend fun invoke(userEntity: UserEntity) {
        userRepository.saveUser(userEntity)
    }
}
