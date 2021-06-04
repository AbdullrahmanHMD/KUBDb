package com.comp306.kubdb

import android.app.Application
import com.comp306.kubdb.data.LibraryDatabase
import com.comp306.kubdb.repositories.AuthorRepository
import com.comp306.kubdb.repositories.BookRepository
import com.comp306.kubdb.repositories.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class LibraryApplication : Application() {

    val applicationScope = CoroutineScope(SupervisorJob())

    private val database by lazy { LibraryDatabase.getDatabase(this, applicationScope) }
    val userRepository by lazy { UserRepository(database.userDao()) }
    val bookRepository by lazy { BookRepository(database.bookDao()) }
    val authorRepository by lazy { AuthorRepository(database.AuthorDao()) }

}