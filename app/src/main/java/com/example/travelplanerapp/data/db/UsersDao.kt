package com.example.travelplanerapp.data.db

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.travelplanerapp.data.entity.UserEntity

@Dao
interface UsersDao {

    @Upsert
    suspend fun upsertUser(userEntity: UserEntity)

    @Query("SELECT * FROM ${UserEntity.TABLE_NAME} WHERE login = :login AND password = :password")
    suspend fun getUser(login: String, password: String): UserEntity
}
