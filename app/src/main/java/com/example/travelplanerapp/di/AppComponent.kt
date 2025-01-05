package com.example.travelplanerapp.di

import android.app.Application
import com.example.travelplanerapp.presenter.login.LoginFragment
import com.example.travelplanerapp.presenter.register.RegisterFragment
import com.example.travelplanerapp.presenter.travel.RootTravelFragment
import com.example.travelplanerapp.presenter.travel.create.CreateFragment
import com.example.travelplanerapp.presenter.travel.list.ListFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(
    modules = [
        AppModule::class
    ]
)
@Singleton
interface AppComponent {

    fun inject(fragment: LoginFragment)
    fun inject(fragment: RegisterFragment)
    fun inject(fragment: RootTravelFragment)
    fun inject(fragment: ListFragment)
    fun inject(fragment: CreateFragment)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(app: Application): Builder
        fun build(): AppComponent
    }
}
