package com.example.travelplanerapp.domain.repository

import com.example.travelplanerapp.data.model.User
import com.example.travelplanerapp.data.model.UserParam

interface UserRepository {

    suspend fun saveUser(user: User)

    suspend fun loadUser(userParam: UserParam): User
}
