package com.example.travelplanerapp.presenter.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.travelplanerapp.data.model.User
import com.example.travelplanerapp.data.model.UserParam
import com.example.travelplanerapp.domain.usecase.LoginByLoginUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val loginByLoginUseCase: LoginByLoginUseCase
) : ViewModel() {

    private val _userLiveData = MutableLiveData<User>()
    val userLiveData: LiveData<User>
        get() = _userLiveData

    fun loginUser(login: String, password: String) {
        viewModelScope.launch {
            val user = loginByLoginUseCase(UserParam(login = login, password = password))
            _userLiveData.value = user
        }
    }
}
