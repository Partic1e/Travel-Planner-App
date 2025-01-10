package com.example.travelplanerapp.data.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.travelplanerapp.data.entity.TicketEntity.Companion.TABLE_NAME

@Entity(
    tableName = TABLE_NAME,
    foreignKeys = [
        ForeignKey(
            entity = RouteEntity::class,
            parentColumns = ["id"],
            childColumns = ["routeId"]
        )
    ]
)
data class TicketEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val startCity: String,
    val endCity: String,
    val startCityCode: String,
    val endCityCode: String,
    val date: String,
    var price: Int,
    val isActual: Boolean,
    val routeId: Int
) {
    companion object {
        const val TABLE_NAME = "tickets"
    }
}
