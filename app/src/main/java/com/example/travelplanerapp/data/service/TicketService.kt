package com.example.travelplanerapp.data.service

import com.example.travelplanerapp.data.model.TicketResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TicketService {

    companion object {
        const val TOKEN = "0fec24a5507b92bf22fbcd000ce7dcec"
    }

    @GET("v3/prices_for_dates")
    suspend fun getPricesForDates(
        @Query("origin") origin: String,
        @Query("destination") destination: String,
        @Query("departure_at") departureAt: String?,
        @Query("return_at") returnAt: String?,
        @Query("currency") currency: String = "rub",
        @Query("one_way") oneWay: Boolean = true,
        @Query("direct") direct: Boolean = false,
        @Query("limit") limit: Int = 30,
        @Query("page") page: Int = 1,
        @Query("sorting") sorting: String = "price",
        @Query("unique") unique: Boolean = false,
        @Query("token") token: String = TOKEN
    ): Response<TicketResponse>
}
