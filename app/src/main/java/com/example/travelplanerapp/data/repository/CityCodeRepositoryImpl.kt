package com.example.travelplanerapp.data.repository

import android.content.Context
import com.example.travelplanerapp.domain.repository.CityCodeRepository
import com.google.gson.Gson
import javax.inject.Inject

class CityCodeRepositoryImpl @Inject constructor(
    private val context: Context
) : CityCodeRepository {

    override fun loadCityCodes(): Map<String, String> {
        val jsonFileContent = context.assets.open("russian_cities_with_iata.json")
            .bufferedReader()
            .use { it.readText() }

        val cityCodeList = Gson().fromJson(jsonFileContent, Array<CityCode>::class.java)
        return cityCodeList.associate { it.city to it.iata_code }
    }

    data class CityCode(
        val city: String,
        val iata_code: String
    )
}
