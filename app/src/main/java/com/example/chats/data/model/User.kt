package com.example.chats.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @PrimaryKey
    val id: Int,
    val name: String,
    val phoneNumber: String,
    val city: String,
    val dateOfBirth: String,
    val zodiacSign: String,
    val about: String,
    val profilePictureUrl: String
)