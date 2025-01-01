package com.example.travelplanerapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.travelplanerapp.data.model.User.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class User(
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
