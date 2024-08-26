package com.example.chats.ui.screens.auth

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.chats.ui.theme.ChatsTheme


@Composable
fun AuthScreen(
    modifier: Modifier = Modifier,
    authViewModel: AuthViewModel = hiltViewModel()) {
    var phoneNumber by remember { mutableStateOf("") }
    var code by remember { mutableStateOf("") }
    val authorizationResult by authViewModel.authorizationResult.collectAsState()

    if (authorizationResult) {
        // Redirect to home screen or show success
    } else {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            PhoneInputWithCountryPicker(
                value = phoneNumber,
                onValueChange = { phoneNumber = it },
                onCountrySelected = { country ->
                    // Handle country selection if needed
                    // e.g., validate that number starts with correct country code
                }
            )

            Button(
                onClick = {
                    authViewModel.sendPhoneNumberAndRequestCode(phoneNumber)
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Send Code")
            }

            SmsCodeInput(
                code = code,
                onCodeChange = { code = it },
                onSubmit = {
                    authViewModel.verifyCode(phoneNumber, code)
                }
            )
        }
    }
}

@Preview
@Composable
fun PreviewPhoneAuthScreen() {
    ChatsTheme {
        AuthScreen( )
    }
}






