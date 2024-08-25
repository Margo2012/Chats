package com.example.chats.ui.screens.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.chats.ui.theme.ChatsTheme

@Composable
fun PhoneAuthScreen(modifier: Modifier = Modifier) {
    var phoneNumber by remember { mutableStateOf("") }

    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            PhoneInput(
                value = phoneNumber,
                onValueChange = { phoneNumber = it },
                label = { Text(text = "Phone Number") },
                locale = "US", // Устанавливаем регион по умолчанию
                modifier = Modifier.fillMaxWidth()
            )
            Button(
                onClick = { /*TODO: Обработать вход*/ },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Sign in")
            }
        }
    }
}

@Preview
@Composable
fun PreviewPhoneAuthScreen() {
    ChatsTheme {
        PhoneAuthScreen(
            modifier = Modifier
                .background(color = MaterialTheme.colorScheme.background)
        )
    }
}

