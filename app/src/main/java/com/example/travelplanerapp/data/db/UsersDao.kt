package com.example.travelplanerapp.data.db

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.travelplanerapp.data.model.User

@Dao
interface UsersDao {

    @Upsert
    suspend fun upsertUser(user: User)

    @Query("SELECT * FROM ${User.TABLE_NAME} WHERE login = :login AND password = :password")
    suspend fun getUser(login: String, password: String): User
}
