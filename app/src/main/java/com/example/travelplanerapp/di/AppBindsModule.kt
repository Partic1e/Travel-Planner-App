package com.example.travelplanerapp.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.travelplanerapp.data.db.UsersDao
import com.example.travelplanerapp.data.db.UsersDatabase
import com.example.travelplanerapp.data.repository.UserRepositoryImpl
import com.example.travelplanerapp.domain.repository.UserRepository
import com.example.travelplanerapp.domain.usecase.LoginByLoginUseCase
import com.example.travelplanerapp.domain.usecase.LoginByLoginUseCaseImpl
import com.example.travelplanerapp.domain.usecase.RegisterUseCase
import com.example.travelplanerapp.domain.usecase.RegisterUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
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
    fun bindUserRepository(repository: UserRepositoryImpl): UserRepository

    companion object {

        @Provides
        fun provideContext(app: Application): Context = app.applicationContext

        @Provides
        @Singleton
        fun provideDb(context: Context): UsersDatabase =
            Room.databaseBuilder(
                context,
                UsersDatabase::class.java,
                "users"
            ).build()

        @Provides
        @Singleton
        fun provideUsersDao(db: UsersDatabase): UsersDao =
            db.usersDao
    }
}
