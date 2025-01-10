package com.example.travelplanerapp.domain.repository

interface CityCodeRepository {

    fun loadCityCodes(): Map<String, String>
}
