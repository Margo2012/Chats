package com.example.chats.network

import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route

class TokenAuthenticator(
    private val tokenManager: TokenManager,
    private val apiService: ApiService
): Authenticator {
    override fun authenticate(route: Route?, response: Response): Request? {
        synchronized(this){
            val newAccessToken = refreshAccessToken() ?: return null

            tokenManager.accessToken = newAccessToken
            return response.request.newBuilder()
                .header("Authorization", "Bearer $newAccessToken")
                .build()
        }
    }

    private fun refreshAccessToken(): String? {
        val refreshToken  = tokenManager.refreshToken ?: return null
        val response = apiService.refreshToken(mapOf("refresh_token" to refreshToken)).execute()

        return if (response.isSuccessful){
            response.body()?.accessToken
        } else null

    }
}