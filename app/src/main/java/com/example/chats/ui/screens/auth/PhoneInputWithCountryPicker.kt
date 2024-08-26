package com.example.chats.ui.screens.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
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
import com.example.chats.data.model.Country
import com.example.chats.data.model.countries
import com.example.chats.ui.dialog.CountryPickerDialog
import com.example.chats.ui.theme.ChatsTheme

@Composable
fun PhoneInputWithCountryPicker(
    modifier: Modifier = Modifier,
    initialCountry: Country = Country("RU", "Russia", "+7"),
    value: String,
    onValueChange: (String) -> Unit,
    onCountrySelected: (Country) -> Unit,
    isError: Boolean = false,
    label: @Composable (() -> Unit)? = null
) {
    var selectedCountry by remember { mutableStateOf(initialCountry) }
    var displayValue by remember { mutableStateOf(value) }
    var isDialogOpen by remember { mutableStateOf(false) }

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button(
            onClick = { isDialogOpen = true },
            modifier = Modifier.padding(end =8.dp)
        ) {
            Text(text = "${selectedCountry.name} (${selectedCountry.countryCode})")
        }

        OutlinedTextField(
            value = displayValue,
            onValueChange = {
                val formattedText = if (it.startsWith("+")) it else "${selectedCountry.countryCode}$it"
                displayValue = formattedText
                onValueChange(formattedText)
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            isError = isError,
            label = label,
            modifier = Modifier.weight(1f)
        )
    }

    // Отображение диалога выбора страны
    if (isDialogOpen) {
        CountryPickerDialog(
            countries = countries,
            onCountrySelected = { country ->
                selectedCountry = country
                onCountrySelected(country)
                isDialogOpen = false
            },
            onDismiss = { isDialogOpen = false }
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
            onCountrySelected = { /* handle region change */ }
        )
    }
}