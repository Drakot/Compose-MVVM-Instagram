package com.example.composeinstagram.login.domain

import com.example.composeinstagram.login.data.LoginRepository

class LoginUseCase(val repository: LoginRepository = LoginRepository()) {
    suspend operator fun invoke(user: String, password: String): Boolean {
        return repository.doLogin(user, password)
    }
}