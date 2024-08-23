package com.example.chats.network

import com.example.chats.data.model.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @GET("api/v1/users/me/")
    suspend fun getUserProfile(): User

    @POST("api/v1/users/refresh-token/")
    fun refreshToken(@Body body: Map<String, String>): Call<TokenResponse>
    @POST("auth/phone")
    suspend fun authenticatePhoneNumber(phoneNumber: String)
    @POST("api/v1/users/check-auth-code/")
    suspend fun verifySmsCode(@Body body: Map<String, String>): TokenResponse

    @POST("api/v1/users/register/")
    suspend fun registerUser(@Body body: Map<String, String>): TokenResponse

    @POST("api/v1/users/send-auth-code/")
    suspend fun sendAuthCode(@Body body: Map<String, String>)

}

data class TokenResponse(val accessToken: String, val refreshToken: String)