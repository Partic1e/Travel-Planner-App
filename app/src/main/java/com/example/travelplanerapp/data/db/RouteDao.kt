package com.example.travelplanerapp.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.travelplanerapp.data.entity.RouteEntity

@Dao
interface RouteDao {

    @Query("SELECT * FROM ${RouteEntity.TABLE_NAME}")
    suspend fun getRoutesForUser(): List<RouteEntity>

    @Insert
    suspend fun insertRoute(routeEntity: RouteEntity): Long
}
