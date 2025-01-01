package com.example.travelplanerapp.di

import android.app.Application
import com.example.travelplanerapp.presenter.login.LoginFragment
import com.example.travelplanerapp.presenter.register.RegisterFragment
import com.example.travelplanerapp.presenter.travel.TestFragment
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
    fun inject(fragment: TestFragment)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(app: Application): Builder
        fun build(): AppComponent
    }
}
