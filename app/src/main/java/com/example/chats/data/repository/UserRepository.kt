package com.example.connectme.data.repository

import android.provider.SyncStateContract.Helpers.insert
import com.example.chats.data.model.User
import com.example.chats.db.UserDao
import com.example.chats.network.ApiService
import com.example.chats.network.TokenManager
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow



class UserRepository @Inject constructor(
    private val apiService: ApiService,
    private val userDao: UserDao,
    private val tokenManager: TokenManager
) {

    fun getUserProfile(): Flow<User> = flow {
        try {
            val userProfile = apiService.getUserProfile()
            userDao.apply {
                deleteAll()
                insert(userProfile)
            }
            emit(userProfile)
        } catch (e: Exception) {
            // If there's an error (e.g., network error), fetch from local database
            emitAll(userDao.getUserById(1)) // Assuming you have a default user ID of 1())
        }
    }

    fun authenticate(phoneNumber: String, code: String): Flow<Unit> = flow {
        val tokenResponse = apiService.verifySmsCode(mapOf("phone_number" to phoneNumber, "code" to code))
        tokenManager.accessToken = tokenResponse.accessToken
        tokenManager.refreshToken = tokenResponse.refreshToken
        emit(Unit)
    }

    suspend fun sendAuthCode(phoneNumber: String) {
        apiService.sendAuthCode(mapOf("phone_number" to phoneNumber))
    }

    suspend fun saveTokens(accessToken: String, refreshToken: String) {
        tokenManager.accessToken = accessToken
        tokenManager.refreshToken = refreshToken
    }
}
