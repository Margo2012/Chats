package com.example.chats.ui.dialog

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.chats.R
import com.example.chats.data.model.Country



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CountryPickerDialog(
    countries: List<Country>,
    onCountrySelected: (Country) -> Unit,
    onDismiss: () -> Unit
) {
    BasicAlertDialog(
        modifier = Modifier.padding(24.dp),
        onDismissRequest = onDismiss,
    ) {
        LazyColumn {
            items(countries){country ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            onCountrySelected(country)
                            onDismiss()
                        }
                        .padding(8.dp)
                ){
                    Image(
                        painterResource(id = getFlagResource(country.code)),
                        contentDescription = "${country.name} Flag",
                        modifier = Modifier.size(24.dp)  // Отображение флага
                    )
                }
            }
        }
    }
}


@DrawableRes
fun getFlagResource(countryCode: String): Int {
    return when (countryCode) {
        "RU" -> R.drawable.flag_ru
        "US" -> R.drawable.flag_us
        "DE" -> R.drawable.flag_germany
        "GB" -> R.drawable.flag_gb
        "FR" -> R.drawable.flag_france
        "ES" -> R.drawable.flag_spain
        else -> R.drawable.flag_ru // Флаг по умолчанию или иконка
    }
}

