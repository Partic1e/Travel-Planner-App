package com.example.travelplanerapp.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.travelplanerapp.data.entity.TicketEntity

@Dao
interface TicketDao {

    @Insert
    suspend fun insertTicket(ticketEntity: TicketEntity)

    @Query("SELECT * FROM ${TicketEntity.TABLE_NAME} WHERE routeId = :routeId")
    suspend fun getTickets(routeId: Int): List<TicketEntity>
}
