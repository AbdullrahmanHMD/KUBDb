package com.comp306.kubdb.repositories

import androidx.annotation.WorkerThread
import com.comp306.kubdb.dao.BookDao
import com.comp306.kubdb.data.custom.BookAverageRating
import com.comp306.kubdb.data.custom.RealNumber
import com.comp306.kubdb.data.entities.Book
import kotlinx.coroutines.flow.Flow

class BookRepository(private val bookDao: BookDao) {


    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun getAverageRatings(isbns: List<Int>): Flow<List<BookAverageRating>> {
        return bookDao.getAverageRatings(isbns)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun getAverageRating(isbn: Int): Flow<RealNumber> {
        return bookDao.getAverageRating(isbn)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    fun getBooksOFTopAuthor(): Flow<List<Book>> {
        return bookDao.getBooksOFTopAuthor()
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    fun getRecommendedBooks(age: Int?, state: String?): Flow<List<Book>>? {
        if (age != null && state != null) {
            println("NOT NULL IN REPO: $age, $state")
            return bookDao.getRecommendedBooks(age, state)
        }
        print("NULL IN REPO")
        return null
    }
}