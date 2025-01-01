package com.example.travelplanerapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.travelplanerapp.data.model.User

@Database(
    entities = [
        User::class,
    ],
    version = 1
)
abstract class UsersDatabase : RoomDatabase() {

    abstract val usersDao: UsersDao
}
