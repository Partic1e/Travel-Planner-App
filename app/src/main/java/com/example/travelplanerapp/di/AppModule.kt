package com.example.travelplanerapp.di

import com.example.travelplanerapp.di.viewModel.ViewModelModule
import dagger.Module

@Module(
    includes = [
        ViewModelModule::class,
        AppBindsModule::class
    ]
)
class AppModule
