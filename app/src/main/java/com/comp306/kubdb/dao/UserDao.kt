package com.comp306.kubdb.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.comp306.kubdb.data.custom.RealNumber
import com.comp306.kubdb.data.entities.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Query("SELECT * FROM users")
    fun getAllUsers(): Flow<List<User>>

    @Query("SELECT * FROM users WHERE user_id = :userID")
    fun getUserByID(userID: String): Flow<User>

    @Query("SELECT * FROM users WHERE user_id = :userID AND password = :password")
    fun getUserByCredentials(userID: Int, password: String): Flow<User>

}