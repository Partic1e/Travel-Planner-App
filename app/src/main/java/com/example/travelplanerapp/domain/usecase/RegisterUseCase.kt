package com.example.travelplanerapp.domain.usecase

import com.example.travelplanerapp.data.model.User
import com.example.travelplanerapp.domain.repository.UserRepository
import javax.inject.Inject

interface RegisterUseCase {

    suspend operator fun invoke(user: User)
}

class RegisterUseCaseImpl @Inject constructor(
    private val userRepository: UserRepository
) : RegisterUseCase {

    override suspend fun invoke(user: User) {
        userRepository.saveUser(user)
    }
}
