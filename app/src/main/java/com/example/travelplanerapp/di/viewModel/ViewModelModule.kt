package com.example.travelplanerapp.di.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.travelplanerapp.presenter.graph.GraphViewModel
import com.example.travelplanerapp.presenter.login.LoginViewModel
import com.example.travelplanerapp.presenter.register.RegisterViewModel
import com.example.travelplanerapp.presenter.travel.create.CreateViewModel
import com.example.travelplanerapp.presenter.travel.routes.RoutesViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @Binds
    fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    fun bindLoginViewModel(viewModel: LoginViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(RegisterViewModel::class)
    fun bindRegisterViewModel(viewModel: RegisterViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(RoutesViewModel::class)
    fun bindListViewModel(viewModel: RoutesViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CreateViewModel::class)
    fun bindCreateViewModel(viewModel: CreateViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(GraphViewModel::class)
    fun bindGraphViewModel(viewModel: GraphViewModel): ViewModel
}
