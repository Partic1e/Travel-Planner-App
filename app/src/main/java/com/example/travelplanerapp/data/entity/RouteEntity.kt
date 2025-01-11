package com.example.travelplanerapp.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.travelplanerapp.data.entity.RouteEntity.Companion.TABLE_NAME

@Entity(
    tableName = TABLE_NAME
)
data class RouteEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String
) {
    companion object {
        const val TABLE_NAME = "routes"
    }
}
