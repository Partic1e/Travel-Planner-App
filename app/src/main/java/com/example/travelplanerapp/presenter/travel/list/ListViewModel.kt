package com.example.travelplanerapp.presenter.travel.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.travelplanerapp.data.model.Route
import kotlinx.coroutines.launch
import javax.inject.Inject

class ListViewModel @Inject constructor() : ViewModel() {

    private val _routes = MutableLiveData<List<Route>>()
    val routes: LiveData<List<Route>>
        get() = _routes

    init {
        viewModelScope.launch {
            val route1 = Route(1, "name1", 100)
            val route2 = Route(2, "name2", 200)
            val route3 = Route(3, "name3", 500)
            val route4 = Route(4, "name4", 1000)
            val route5 = Route(5, "name5", 99900)
            val route = listOf<Route>(route1, route2, route3, route4, route5)
            _routes.postValue(route)
        }
    }
}
