package com.example.travelplanerapp.domain.repository

import com.example.travelplanerapp.data.entity.UserEntity
import com.example.travelplanerapp.data.model.UserParam

interface UserRepository {

    suspend fun saveUser(userEntity: UserEntity)

    suspend fun loadUser(userParam: UserParam): UserEntity
}
