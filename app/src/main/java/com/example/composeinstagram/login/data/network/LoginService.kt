package com.example.composeinstagram.login.data.network

import com.example.composeinstagram.core.network.RetrofitHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LoginService {
    private val retrofit = RetrofitHelper.getRetrofit()

    suspend fun doLogin(user: String, password: String): Boolean {
        return withContext(Dispatchers.IO) {
            val loginClient = retrofit.create(LoginClient::class.java)
            val response = loginClient.doLogin(user, password)
            response.body()?.success ?: false
        }

    }
}