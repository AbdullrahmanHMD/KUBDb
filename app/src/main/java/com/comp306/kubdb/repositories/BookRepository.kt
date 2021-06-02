package com.comp306.kubdb.repositories

import androidx.annotation.WorkerThread
import com.comp306.kubdb.dao.BookDao
import com.comp306.kubdb.data.custom.BookAverageRating
import com.comp306.kubdb.data.entities.Book
import kotlinx.coroutines.flow.Flow

class BookRepository(private val bookDao: BookDao) {


    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    fun getAverageRating(isbns: List<Int>): Flow<List<BookAverageRating>> {
        return bookDao.getAverageRating(isbns)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    fun getBooksOFTopAuthor(): Flow<List<Book>> {
        return bookDao.getBooksOFTopAuthor()
    }


}