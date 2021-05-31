package com.comp306.kubdb.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.comp306.kubdb.data.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Query("SELECT * FROM User WHERE user_id = :userID")
    fun getUserByID(userID: String): Flow<User>

    @Query("SELECT * FROM User WHERE user_id = :userID AND password = :password")
    fun getUserByCredentials(userID: String, password: String): Flow<User>



    @Insert(onConflict = OnConflictStrategy.IGNORE) //todo: change to query
    suspend fun addUser(user: User)
}