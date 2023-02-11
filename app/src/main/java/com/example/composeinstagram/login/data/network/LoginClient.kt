package com.example.composeinstagram.login.data.network

import com.example.composeinstagram.login.data.network.response.LoginResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface LoginClient {
    @GET("/v3/f78c9d33-28b1-4f81-9cf1-6d6ff78fa014")
    suspend fun doLogin(@Query("user")user:String, @Query("password")password:String): Response<LoginResponse>
}