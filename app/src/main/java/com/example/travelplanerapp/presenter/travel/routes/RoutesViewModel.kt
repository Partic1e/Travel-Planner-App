package com.example.travelplanerapp.presenter.travel.routes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.travelplanerapp.data.entity.RouteEntity
import com.example.travelplanerapp.domain.usecase.GetRoutesForUserUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class RoutesViewModel @Inject constructor(
    private val getRoutesForUserUseCase: GetRoutesForUserUseCase
) : ViewModel() {

    private val _routes = MutableLiveData<List<RouteEntity>>()
    val routes: LiveData<List<RouteEntity>>
        get() = _routes

    init {
        viewModelScope.launch {
            val routes = getRoutesForUserUseCase()
            _routes.postValue(routes)
        }
    }
}
