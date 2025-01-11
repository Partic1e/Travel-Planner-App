package com.example.travelplanerapp.presenter.travel.create

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.travelplanerapp.data.model.City
import com.example.travelplanerapp.data.entity.RouteEntity
import com.example.travelplanerapp.data.entity.TicketEntity
import com.example.travelplanerapp.data.model.RouteParam
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

    private val _ticketsSaved = MutableLiveData<Boolean>()
    val ticketsSaved: LiveData<Boolean> get() = _ticketsSaved

    private val _route = MutableLiveData<List<RouteParam>>(mutableListOf())
    val route: LiveData<List<RouteParam>> get() = _route

    private val _routeId = MutableLiveData<Int>()
    val routeId: LiveData<Int> get() = _routeId

    private val cityCodes by lazy { cityCodeRepository.loadCityCodes() }

    fun addStartCity(startCity: String) {
        val city = City(startCity, "")
        val currentRoute = _route.value.orEmpty().toMutableList()
        currentRoute.add(0, RouteParam(listOf(city)))
        _route.value = currentRoute
    }

    fun addCities(cities: List<City>) {
        val currentRoute = _route.value.orEmpty().toMutableList()
        currentRoute.add(RouteParam(cities))
        _route.value = currentRoute
    }

    fun updateRoute(updatedRoute: List<RouteParam>) {
        _route.value = updatedRoute
    }

    fun createRoute(routeName: String) {
        viewModelScope.launch {
            val routeEntityId = saveRouteUseCase(RouteEntity(name = routeName))
            _routeId.value = routeEntityId
            saveTicketsUseCase(createTickets(routeEntityId))
            _ticketsSaved.postValue(true)
        }
    }

    private fun createTickets(routeId: Int): List<TicketEntity> {
        val ticketEntities = mutableListOf<TicketEntity>()
        val route = _route.value.orEmpty()

        for (i in 0 until route.size - 1) {
            val currentCities = route[i].cities
            val nextCities = route[i + 1].cities

            for (currentCity in currentCities) {
                for (nextCity in nextCities) {
                    ticketEntities.add(
                        TicketEntity(
                            startCity = currentCity.city,
                            endCity = nextCity.city,
                            date = nextCity.date,
                            price = 0,
                            isActual = false,
                            startCityCode = getCityCode(currentCity.city),
                            endCityCode = getCityCode(nextCity.city),
                            routeId = routeId
                        )
                    )
                }
            }
        }
        return ticketEntities
    }

    private fun getCityCode(cityName: String): String {
        return cityCodes[cityName] ?: "Unknown"
    }
}
