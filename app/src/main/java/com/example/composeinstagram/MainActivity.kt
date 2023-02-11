package com.example.composeinstagram

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.composeinstagram.login.ui.LoginScreen
import com.example.composeinstagram.login.ui.LoginViewModel
import com.example.composeinstagram.ui.theme.ComposeInstagramTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeInstagramTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    LoginScreen(LoginViewModel())
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}


@Composable
fun DefaultPreview() {
    ComposeInstagramTheme {
        LoginScreen(LoginViewModel())
    }
}