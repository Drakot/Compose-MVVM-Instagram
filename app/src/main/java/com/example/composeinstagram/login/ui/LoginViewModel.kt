package com.example.composeinstagram.login.ui

import android.util.Log
import android.util.Patterns
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composeinstagram.login.domain.LoginUseCase
import kotlinx.coroutines.launch

class LoginViewModel(val useCase: LoginUseCase = LoginUseCase()) : ViewModel() {
    private val _email: MutableState<String> = mutableStateOf("")
    val email: State<String> = _email

    private val _password: MutableState<String> = mutableStateOf("")
    val password: State<String> = _password

    private val _isLoginEnabled: MutableState<Boolean> = mutableStateOf(false)
    val isLoginEnabled: State<Boolean> = _isLoginEnabled

    private val _isLoading: MutableState<Boolean> = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    /* private val _loginData: MutableState<LoginData> = mutableStateOf(LoginData())
     val loginData: State<LoginData> = _loginData
 */

    fun onLoginChange(email: String, password: String) {
        _email.value = email
        _password.value = password
        _isLoginEnabled.value = enableLogin(email, password)
    }

    fun enableLogin(email: String, password: String): Boolean =
        Patterns.EMAIL_ADDRESS.matcher(email).matches() && password.length > 5

    fun onLoginClick() {
        viewModelScope.launch {
            _isLoading.value = true
            val result = useCase(email.value, password.value)
            if (result) {
                //nav next screen
                Log.i("LoginViewModel", "onLoginClick: success")
            } else {
                //show error
            }
            _isLoading.value = false
        }
    }

    fun onImageClick() {
        onLoginChange("email@email.com","password123456")
    }
}

data class LoginData(
    var email: String = "",
    var password: String = "",
    val isLoginEnabled: Boolean = false
)

sealed class LoginUiState {
    object Success : LoginUiState()
    data class Error(val message: String) : LoginUiState()
    object Loading : LoginUiState()
    object Empty : LoginUiState()
}
