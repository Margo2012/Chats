package com.example.chats.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.chats.data.model.User
import kotlinx.coroutines.flow.Flow

/*@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: User)

    @Query("SELECT * FROM users LIMIT 1")
    fun getUser(): Flow<User>

    @Query("Delete from users")
    suspend fun deleteAll()
}*/

@Dao
interface UserDao {
    @Query("SELECT * FROM users WHERE id = :id")
    fun getUserById(id: Int): Flow<User>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: User)

    @Query("DELETE FROM users WHERE id = :id")
    suspend fun deleteById(id: Int)

    @Query("DELETE FROM users")
    suspend fun deleteAll()
}