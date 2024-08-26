package com.example.chats.ui.screens.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.chats.ui.theme.ChatsTheme


@Composable
fun SMSCodeScreen(modifier: Modifier) {
    var code by remember { mutableStateOf("") }

    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            OutlinedTextField(
                value = code,
                onValueChange = { newCode ->
                    code = newCode
                },
                label = { Text("Verification Code") },
                modifier = Modifier.fillMaxWidth()
            )
            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Verify")
            }

        }

    }

}

@Composable
fun SmsCodeInput(
    code: String,
    onCodeChange: (String) -> Unit,
    onSubmit: () -> Unit,
) {
    OutlinedTextField(
        value = code,
        onValueChange = { if (it.length <= 6 && it.all { ch -> ch.isDigit() }) onCodeChange(it) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        label = { Text("Enter SMS code") },
        modifier = Modifier.fillMaxWidth(),
        singleLine = true
    )

    Button(
        onClick = {
            if (code.length == 6) onSubmit()
            else {
                // Show invalid code length error
            }
        },
        modifier = Modifier.fillMaxWidth()
    ) {
        Text("Submit")
    }
}

@Preview
@Composable
fun PreviewSMSCodeScreen() {
    ChatsTheme {
        SMSCodeScreen(
            modifier = Modifier
                .background(color = MaterialTheme.colorScheme.background)
        )
    }
}
