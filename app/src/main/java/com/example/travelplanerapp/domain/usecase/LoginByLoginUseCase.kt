package com.example.travelplanerapp.domain.usecase

import com.example.travelplanerapp.data.entity.UserEntity
import com.example.travelplanerapp.data.model.UserParam
import com.example.travelplanerapp.domain.repository.UserRepository
import javax.inject.Inject

interface LoginByLoginUseCase {

    suspend operator fun invoke(userParam: UserParam): UserEntity
}

class LoginByLoginUseCaseImpl @Inject constructor(
    private val userRepository: UserRepository
) : LoginByLoginUseCase {

    override suspend fun invoke(userParam: UserParam): UserEntity =
        userRepository.loadUser(userParam)
}
