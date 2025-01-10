package com.example.travelplanerapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.travelplanerapp.data.entity.RouteEntity
import com.example.travelplanerapp.data.entity.TicketEntity
import com.example.travelplanerapp.data.entity.UserEntity

@Database(
    entities = [
        UserEntity::class,
        RouteEntity::class,
        TicketEntity::class
    ],
    version = 1
)
abstract class TravelDatabase : RoomDatabase() {

    abstract val usersDao: UsersDao
    abstract val routeDao: RouteDao
    abstract val ticketDao: TicketDao
}
