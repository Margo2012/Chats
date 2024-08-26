package com.example.chats.di

import com.example.chats.db.UserDao
import com.example.chats.network.ApiService
import com.example.chats.network.TokenManager
import com.example.connectme.data.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepoModule{
    @Provides
    @Singleton
    fun provideUserRepository(
        apiService: ApiService,
        userDao: UserDao,
        tokenManager: TokenManager
    ): UserRepository {
        return UserRepository(apiService, userDao, tokenManager)
    }
}