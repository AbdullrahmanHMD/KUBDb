package com.comp306.kubdb.repositories

import androidx.annotation.WorkerThread
import com.comp306.kubdb.dao.UserDao
import com.comp306.kubdb.data.User
import kotlinx.coroutines.flow.Flow

class UserRepository(private val userDao: UserDao) {


    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun getUserByID(user_id: String): Flow<User> {
        return userDao.getUserByID(user_id)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun getUserByCredentials(user_id: String, password: String): Flow<User> {
        return userDao.getUserByCredentials(user_id, password)
    }

    @Suppress("RedundantSuspendModifier") //TODO: REMOVE?
    @WorkerThread
    suspend fun addUser(user: User) {
        userDao.addUser(user)
    }

}