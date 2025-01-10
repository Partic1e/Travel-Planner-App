package com.example.travelplanerapp.presenter.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.travelplanerapp.data.entity.UserEntity
import com.example.travelplanerapp.data.model.UserParam
import com.example.travelplanerapp.domain.usecase.LoginByLoginUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val loginByLoginUseCase: LoginByLoginUseCase
) : ViewModel() {

    private val _userEntityLiveData = MutableLiveData<UserEntity>()
    val userEntityLiveData: LiveData<UserEntity>
        get() = _userEntityLiveData

    fun loginUser(login: String, password: String) {
        viewModelScope.launch {
            val user = loginByLoginUseCase(UserParam(login = login, password = password))
            _userEntityLiveData.postValue(user)
        }
    }
}
