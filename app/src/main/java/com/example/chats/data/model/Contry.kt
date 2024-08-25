package com.example.chats.data.model


data class Country(
    val name: String,
    val code: String,
    val countryCode: String,
)

val countries = listOf(
    Country("United States", "US", "+1"),
    Country("Russia", "RU", "+7"),
    Country("Germany", "DE", "+49"),
    Country("United Kingdom", "GB", "+44"),
    Country("France", "FR", "+33"),
    Country("Spain", "ES", "+34")
)

