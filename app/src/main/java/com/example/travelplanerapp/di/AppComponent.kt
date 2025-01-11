package com.example.travelplanerapp.di

import android.app.Application
import com.example.travelplanerapp.presenter.city.CityFragment
import com.example.travelplanerapp.presenter.graph.GraphFragment
import com.example.travelplanerapp.presenter.login.LoginFragment
import com.example.travelplanerapp.presenter.register.RegisterFragment
import com.example.travelplanerapp.presenter.travel.RootTravelFragment
import com.example.travelplanerapp.presenter.travel.create.CreateFragment
import com.example.travelplanerapp.presenter.travel.routes.RoutesFragment
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
    fun inject(fragment: RoutesFragment)
    fun inject(fragment: CreateFragment)
    fun inject(fragment: CityFragment)
    fun inject(fragment: GraphFragment)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(app: Application): Builder
        fun build(): AppComponent
    }
}
