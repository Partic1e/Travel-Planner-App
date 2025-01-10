package com.example.travelplanerapp.data.db

import androidx.room.Dao
import androidx.room.Insert
import com.example.travelplanerapp.data.entity.TicketEntity

@Dao
interface TicketDao {

    @Insert
    suspend fun insertTicket(ticketEntities: TicketEntity)
}
