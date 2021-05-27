package com.comp306.kubdb.repositories

import androidx.annotation.WorkerThread
import com.comp306.kubdb.dao.UserDao
import com.comp306.kubdb.data.User
import kotlinx.coroutines.flow.Flow

class UserRepository(private val userDao: UserDao) {

    val allUsers: Flow<List<User>> = userDao.getAllUsers()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun addUser(user: User) {
        userDao.addUser(user)
    }

}