package com.comp306.kubdb

import android.app.Application
import com.comp306.kubdb.data.LibraryDatabase
import com.comp306.kubdb.repositories.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class LibraryApplication : Application() {

    val applicationScope = CoroutineScope(SupervisorJob())

    val database by lazy { LibraryDatabase.getDatabase(this, applicationScope) }
    val userRepository by lazy { UserRepository(database.userDao()) }

}