package com.example.travelplanerapp.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.travelplanerapp.data.db.RouteDao
import com.example.travelplanerapp.data.db.TicketDao
import com.example.travelplanerapp.data.db.UsersDao
import com.example.travelplanerapp.data.db.TravelDatabase
import com.example.travelplanerapp.data.repository.CityCodeRepositoryImpl
import com.example.travelplanerapp.data.repository.TravelRepositoryImpl
import com.example.travelplanerapp.data.repository.UserRepositoryImpl
import com.example.travelplanerapp.data.service.TicketService
import com.example.travelplanerapp.domain.repository.CityCodeRepository
import com.example.travelplanerapp.domain.repository.TravelRepository
import com.example.travelplanerapp.domain.repository.UserRepository
import com.example.travelplanerapp.domain.usecase.GetRoutesForUserUseCase
import com.example.travelplanerapp.domain.usecase.GetRoutesForUserUseCaseImpl
import com.example.travelplanerapp.domain.usecase.LoginByLoginUseCase
import com.example.travelplanerapp.domain.usecase.LoginByLoginUseCaseImpl
import com.example.travelplanerapp.domain.usecase.RegisterUseCase
import com.example.travelplanerapp.domain.usecase.RegisterUseCaseImpl
import com.example.travelplanerapp.domain.usecase.SaveRouteUseCase
import com.example.travelplanerapp.domain.usecase.SaveRouteUseCaseImpl
import com.example.travelplanerapp.domain.usecase.SaveTicketsUseCase
import com.example.travelplanerapp.domain.usecase.SaveTicketsUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
interface AppBindsModule {

    @Binds
    @Singleton
    fun bindLoginByLoginUseCase(useCase: LoginByLoginUseCaseImpl): LoginByLoginUseCase

    @Binds
    @Singleton
    fun bindRegisterUseCase(useCase: RegisterUseCaseImpl): RegisterUseCase

    @Binds
    @Singleton
    fun bindGetRoutesForUserUseCase(useCase: GetRoutesForUserUseCaseImpl): GetRoutesForUserUseCase

    @Binds
    @Singleton
    fun bindSaveRouteUseCase(useCase: SaveRouteUseCaseImpl): SaveRouteUseCase

    @Binds
    @Singleton
    fun bindSaveTicketsUseCase(useCase: SaveTicketsUseCaseImpl): SaveTicketsUseCase

    @Binds
    @Singleton
    fun bindUserRepository(repository: UserRepositoryImpl): UserRepository

    @Binds
    @Singleton
    fun bindTravelRepository(repository: TravelRepositoryImpl): TravelRepository

    @Binds
    @Singleton
    fun bindCityCodeRepository(repository: CityCodeRepositoryImpl): CityCodeRepository

    companion object {

        @Provides
        fun provideContext(app: Application): Context = app.applicationContext

        @Provides
        @Singleton
        fun provideDb(context: Context): TravelDatabase =
            Room.databaseBuilder(
                context,
                TravelDatabase::class.java,
                "travel"
            ).build()

        @Provides
        fun provideService(): TicketService =
            Retrofit.Builder()
                .baseUrl("https://api.travelpayouts.com/aviasales/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(TicketService::class.java)

        @Provides
        @Singleton
        fun provideUsersDao(db: TravelDatabase): UsersDao =
            db.usersDao

        @Provides
        @Singleton
        fun provideRouteDao(db: TravelDatabase): RouteDao =
            db.routeDao

        @Provides
        @Singleton
        fun provideTicketDao(db: TravelDatabase): TicketDao =
            db.ticketDao
    }
}
