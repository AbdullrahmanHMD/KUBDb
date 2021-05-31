package com.comp306.kubdb.repositories

import androidx.annotation.WorkerThread
import com.comp306.kubdb.dao.UserDao
import com.comp306.kubdb.data.entities.User
import kotlinx.coroutines.flow.Flow

class UserRepository(private val userDao: UserDao) {

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    fun getAllUsers(): Flow<List<User>> {
        return userDao.getAllUsers()
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun getUserByID(user_id: String): Flow<User> {
        return userDao.getUserByID(user_id)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun getUserByCredentials(user_id: Int, password: String): Flow<User> {
        return userDao.getUserByCredentials(user_id, password)
    }

    @Suppress("RedundantSuspendModifier") //TODO: REMOVE?
    @WorkerThread
    suspend fun addUser(user: User) {
        userDao.addUser(user)
    }

}