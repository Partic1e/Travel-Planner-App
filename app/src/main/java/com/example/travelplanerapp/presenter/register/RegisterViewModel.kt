package com.example.travelplanerapp.presenter.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.travelplanerapp.data.model.User
import com.example.travelplanerapp.domain.usecase.RegisterUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class RegisterViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase
) : ViewModel() {

    fun registerUser(firstName: String, lastName: String, login: String, password: String) {
        viewModelScope.launch {
            registerUseCase(
                User(
                    firstName = firstName,
                    lastName = lastName,
                    login = login,
                    password = password
                )
            )
        }
    }
}
