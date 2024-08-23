package com.example.chats.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.chats.data.model.User

@Database(entities = [User::class], version = 1)
abstract class AppDataBase : RoomDatabase() {
    abstract fun userDao(): UserDao
}