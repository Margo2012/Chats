package com.example.chats.ui.screens.auth

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import com.google.i18n.phonenumbers.PhoneNumberUtil

@Composable
fun PhoneInput(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    locale: String? = "US", // США указан как регион по умолчанию
    isError: Boolean = false,
    label: @Composable (() -> Unit)? = null,

) {
    val phoneNumberUtil = remember { PhoneNumberUtil.getInstance() }
    var displayValue by remember { mutableStateOf(value) }
    val region by remember { mutableStateOf(locale) }

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

    OutlinedTextField(
        value = displayValue,
        onValueChange = {
            displayValue = formatPhoneNumber(it)
            onValueChange(it)
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
        isError = isError,
        label = label,
        modifier = modifier
    )
}