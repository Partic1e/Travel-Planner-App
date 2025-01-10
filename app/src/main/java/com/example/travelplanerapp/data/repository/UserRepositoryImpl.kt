package com.example.travelplanerapp.data.repository

import com.example.travelplanerapp.data.db.UsersDao
import com.example.travelplanerapp.data.entity.UserEntity
import com.example.travelplanerapp.data.model.UserParam
import com.example.travelplanerapp.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val dao: UsersDao
) : UserRepository {

    override suspend fun saveUser(userEntity: UserEntity) {
        dao.upsertUser(userEntity)
    }

    override suspend fun loadUser(userParam: UserParam): UserEntity =
        dao.getUser(login = userParam.login, password = userParam.password)
}
