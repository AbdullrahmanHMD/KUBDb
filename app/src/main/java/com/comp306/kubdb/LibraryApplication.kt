package com.comp306.kubdb

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.comp306.kubdb.data.LibraryDatabase
import com.comp306.kubdb.repositories.AuthorRepository
import com.comp306.kubdb.repositories.BookRepository
import com.comp306.kubdb.repositories.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class LibraryApplication : Application() {

    val loaded = MutableLiveData(false)
    val applicationScope = CoroutineScope(SupervisorJob())

    val database by lazy { LibraryDatabase.getDatabase(this, applicationScope, loaded) }
    val userRepository by lazy { UserRepository(database.userDao()) }
    val bookRepository by lazy { BookRepository(database.bookDao()) }
    val authorRepository by lazy { AuthorRepository(database.AuthorDao()) }

}