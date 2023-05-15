package com.example.randomuser.di

import com.example.randomuser.data.datasource.remote.ApiService
import com.example.randomuser.data.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    /**
     * Provides RemoteDataRepository for access api service method
     */

    @Singleton
    @Provides
    fun provideUserRepository(
        apiService: ApiService,
    ): UserRepository {
        return UserRepository(
            apiService
        )
    }

}