package com.example.travelplanerapp.presenter.travel.create

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.travelplanerapp.data.model.City
import com.example.travelplanerapp.data.entity.RouteEntity
import com.example.travelplanerapp.data.entity.TicketEntity
import com.example.travelplanerapp.domain.repository.CityCodeRepository
import com.example.travelplanerapp.domain.usecase.SaveRouteUseCase
import com.example.travelplanerapp.domain.usecase.SaveTicketsUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class CreateViewModel @Inject constructor(
    private val saveRouteUseCase: SaveRouteUseCase,
    private val saveTicketsUseCase: SaveTicketsUseCase,
    private val cityCodeRepository: CityCodeRepository
) : ViewModel() {

    private val _route = MutableLiveData<List<List<City>>>()
    val route: LiveData<List<List<City>>>
        get() = _route

    private val cityCodes by lazy { cityCodeRepository.loadCityCodes() }

    fun addStartCity(startCity: String) {
        val city = City(startCity, "")
        val newCityList = listOf(city)
        val currentRoute = _route.value.orEmpty().toMutableList()
        currentRoute.add(0, newCityList)
        _route.value = currentRoute.toList()
    }

    fun addCities(cities: List<City>) {
        val currentRoute = _route.value.orEmpty().toMutableList()
        currentRoute.add(cities)
        _route.value = currentRoute.toList()
    }

    fun createRoute(routeName: String) {
        viewModelScope.launch {
            val routeEntityId = saveRouteUseCase(RouteEntity(name = routeName))
            saveTicketsUseCase(createTickets(routeEntityId))
        }
    }

    private fun createTickets(routeId: Int): List<TicketEntity> {
        val ticketEntities = mutableListOf<TicketEntity>()

        val route = _route.value.orEmpty()

        for (i in 0 until route.size - 1) {
            val currentCities = route[i]
            val nextCities = route[i + 1]

            for (currentCity in currentCities) {
                for (nextCity in nextCities) {
                    val ticketEntity = TicketEntity(
                        startCity = currentCity.city,
                        endCity = nextCity.city,
                        date = nextCity.date,
                        price = 0,
                        isActual = false,
                        startCityCode = getCityCode(currentCity.city),
                        endCityCode = getCityCode(nextCity.city),
                        routeId = routeId
                    )
                    ticketEntities.add(ticketEntity)
                }
            }
        }
        return ticketEntities
    }

    private fun getCityCode(cityName: String): String {
        return cityCodes[cityName] ?: "Unknown"
    }
}
