package com.example.travelplanerapp.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.travelplanerapp.data.entity.UserEntity.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val firstName: String,
    val lastName: String,
    val login: String,
    val password: String
) {

    companion object {
        const val TABLE_NAME = "users"
    }
}
