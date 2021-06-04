package com.comp306.kubdb.repositories

import androidx.annotation.WorkerThread
import com.comp306.kubdb.dao.AuthorDao
import com.comp306.kubdb.data.custom.AuthorName
import com.comp306.kubdb.data.entities.Author
import kotlinx.coroutines.flow.Flow

class AuthorRepository(private val authorDao: AuthorDao) {


    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    fun getBookAuthorsNames(isbn: Int): Flow<List<AuthorName>> {
        return authorDao.getBookAuthorsNames(isbn)
    }

}