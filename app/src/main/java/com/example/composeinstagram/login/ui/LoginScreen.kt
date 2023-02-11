package com.example.composeinstagram.login.ui

import android.app.Activity
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.TextFieldDefaults.textFieldColors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import com.example.composeinstagram.R

@Preview
@Composable
fun LoginScreen(loginViewModel: LoginViewModel) {
    Box(
        Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        if (loginViewModel.isLoading.value) {
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                CircularProgressIndicator( Modifier
                    .align(Alignment.Center))
            }
        } else {
            Header(Modifier.align(Alignment.TopEnd))
            Body(Modifier.align(Alignment.Center), loginViewModel)
            Footer(Modifier.align(Alignment.BottomCenter))
        }
    }

}

@Composable
fun Footer(modifier: Modifier) {
    Column(modifier = modifier.fillMaxWidth()) {
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Gray)
                .height(1.dp)
        )
        Spacer(modifier = Modifier.size(24.dp))
        Row(modifier = modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            Text(
                text = "Don't have an account?",
                color = Color.Gray,
                fontSize = 12.sp
            )
            Text(
                fontWeight = FontWeight.Bold,
                text = "Sign up",
                color = Color(0xFF4EA8E9),
                fontSize = 12.sp, modifier = Modifier.padding(horizontal = 8.dp)
            )
        }
        Spacer(modifier = Modifier.size(24.dp))
    }
}

@Composable
fun Body(modifier: Modifier, loginViewModel: LoginViewModel) {
    Column(modifier = modifier) {
        ImageLogo(Modifier.align(Alignment.CenterHorizontally), loginViewModel)
        Spacer(modifier = Modifier.size(16.dp))
        Email(loginViewModel.email.value) {
            loginViewModel.onLoginChange(it, loginViewModel.password.value)
        }
        Spacer(modifier = Modifier.size(4.dp))
        Password(loginViewModel.password.value) {
            loginViewModel.onLoginChange(loginViewModel.email.value, it)
        }
        Spacer(modifier = Modifier.size(8.dp))
        ForgotPassword(Modifier.align(Alignment.End))
        Spacer(modifier = Modifier.size(16.dp))
        LoginButton(loginViewModel.isLoginEnabled.value, loginViewModel)
        Spacer(modifier = Modifier.size(16.dp))
        LoginDivider()
        Spacer(modifier = Modifier.size(16.dp))
        SocialLogin()
    }
}

@Composable
fun SocialLogin() {
    Row(
        Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.fb),
            contentDescription = "Facebook",
            modifier = Modifier.size(16.dp)
        )
        Text(
            text = "Log in with Facebook",
            fontSize = 14.sp,
            color = Color(0xFF4EA8E9),
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}

@Composable
fun LoginDivider() {
    Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        Spacer(
            modifier = Modifier
                .weight(1f)
                .background(Color.Gray)
                .height(1.dp)
        )
        Text(
            text = "OR",
            color = Color.Gray,
            modifier = Modifier.padding(horizontal = 16.dp),
            fontWeight = FontWeight.Bold,
            fontSize = 12.sp
        )
        Spacer(
            modifier = Modifier
                .weight(1f)
                .background(Color.Gray)
                .height(1.dp)
        )
    }
}

@Composable
fun LoginButton(loginEnabled: Boolean, loginViewModel: LoginViewModel) {
    Button(
        modifier = Modifier.fillMaxWidth(), enabled = loginEnabled, onClick = {
            loginViewModel.onLoginClick()
        },
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color(0xFF4EA8E9),
            disabledBackgroundColor = Color(0xFF78C8F9).copy(alpha = 0.5f),
            contentColor = Color.White,
            disabledContentColor = Color.White
        )
    ) {
        Text(text = "Log in", color = Color.White)
    }
}

@Composable
fun ForgotPassword(modifier: Modifier) {
    Text(
        modifier = modifier,
        text = "Forgot Password?",
        fontSize = 12.sp,
        fontWeight = FontWeight.Bold,
        color = Color(0xFF4EA8E9)
    )
}

@Composable
fun Password(password: String, onTextChange: (String) -> Unit) {
    var passwordVisibility by remember { mutableStateOf(false) }
    TextField(
        value = password,
        onValueChange = { onTextChange(it) },
        modifier = Modifier.fillMaxWidth(),
        maxLines = 1,
        placeholder = {
            Text(text = "Password")
        },
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        colors = textFieldColors(
            backgroundColor = Color(0xFFFaFaFa),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            cursorColor = Color.Black,
            textColor = Color(0xFFB2B2B2)
        ),
        trailingIcon = {
            val image = if (passwordVisibility) {
                Icons.Filled.VisibilityOff
            } else {
                Icons.Filled.Visibility
            }

            IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                Icon(imageVector = image, contentDescription = null)
            }

        },
        visualTransformation = if (passwordVisibility) {
            VisualTransformation.None
        } else {
            PasswordVisualTransformation()
        }
    )
}


@Composable
fun Email(text: String, onTextChange: (String) -> Unit) {
    TextField(value = text, onValueChange = { onTextChange(it) }, modifier = Modifier.fillMaxWidth(), placeholder = {
        Text(text = "Email")
    }, maxLines = 1,
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        colors = textFieldColors(
            backgroundColor = Color(0xFFFaFaFa),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            cursorColor = Color.Black,
            textColor = Color(0xFFB2B2B2)
        )
    )
}

@Composable
fun ImageLogo(modifier: Modifier, loginViewModel: LoginViewModel) {
    Image(painterResource(id = R.drawable.insta), contentDescription = null, modifier = modifier.clickable {
        loginViewModel.onImageClick()
    })
}

@Composable
fun Header(modifier: Modifier) {
    val activity = LocalContext.current as Activity
    Icon(imageVector = Icons.Default.Close, contentDescription = null, modifier = modifier.clickable {
        activity.finish()
    })
}