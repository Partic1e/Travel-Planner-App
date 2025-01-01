package com.example.travelplanerapp.data.repository

import com.example.travelplanerapp.data.db.UsersDao
import com.example.travelplanerapp.data.model.User
import com.example.travelplanerapp.data.model.UserParam
import com.example.travelplanerapp.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val dao: UsersDao
) : UserRepository {

    override suspend fun saveUser(user: User) {
        dao.upsertUser(user)
    }

    override suspend fun loadUser(userParam: UserParam): User =
        dao.getUser(login = userParam.login, password = userParam.password)
}
