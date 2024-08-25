package com.example.chats.ui.screens.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
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
import com.google.i18n.phonenumbers.PhoneNumberUtil

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
                modifier = Modifier.fillMaxWidth(),
                value = phoneNumber,
                onValueChange = { phoneNumber = it },
                label = { Text(text = "Phone Number") },
                locale = "US", // Устанавливаем регион по умолчанию
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


@Composable
fun PhoneInputWithCountryPicker(
    modifier: Modifier = Modifier,
    initialRegion: String = "RU",
    value: String,
    onValueChange: (String) -> Unit,
    onRegionSelected: (String) -> Unit,
    locale: String? = "RU",
    isError: Boolean = false,
    label: @Composable (() -> Unit)? = null
) {
    val phoneNumberUtil = remember { PhoneNumberUtil.getInstance() }
    var displayValue by remember { mutableStateOf(value) }
    val region by remember { mutableStateOf(locale ?: initialRegion) }

    fun formatPhoneNumber(input: String): String {
        return try {
            val number = phoneNumberUtil.parse(input, region)
            if (phoneNumberUtil.isValidNumber(number)) {
                phoneNumberUtil.format(number, PhoneNumberUtil.PhoneNumberFormat.INTERNATIONAL)
            } else {
                input
            }
        } catch (e: Exception) {
            input
        }
    }

    DisposableEffect(value) {
        displayValue = formatPhoneNumber(value)
        onDispose { }
    }

    Row(modifier = modifier) {
        // Here we could potentially add a button or dropdown for selecting country code and flag.
        // This is just a basic placeholder, and more sophisticated UI could be built.
        Button(onClick = {
            // Show country picker dialog or similar UI
            /*val newRegion = ... // Determine the region selected by the user
            onRegionSelected(newRegion)*/
        }) {
            // Display flag and country code
            Text(text = "+${phoneNumberUtil.getCountryCodeForRegion(region)}")
        }

        OutlinedTextField(
            value = displayValue,
            onValueChange = {
                displayValue = formatPhoneNumber(it)
                onValueChange(it)
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            isError = isError,
            label = label,
            modifier = Modifier.weight(1f)
        )
    }
}


@Preview
@Composable
fun PreviewPhoneInputWithCountryPicker() {
    ChatsTheme {
        PhoneInputWithCountryPicker(
            modifier = Modifier
                .background(color = MaterialTheme.colorScheme.background),
            value = "89879767856543",
            onValueChange = {  },
            onRegionSelected = { /* handle region change */ }
        )
    }
}