package com.example.travelplanerapp.domain.usecase

import com.example.travelplanerapp.data.model.User
import com.example.travelplanerapp.data.model.UserParam
import com.example.travelplanerapp.domain.repository.UserRepository
import javax.inject.Inject

interface LoginByLoginUseCase {

    suspend operator fun invoke(userParam: UserParam): User
}

class LoginByLoginUseCaseImpl @Inject constructor(
    private val userRepository: UserRepository
) : LoginByLoginUseCase {

    override suspend fun invoke(userParam: UserParam): User =
        userRepository.loadUser(userParam)
}
