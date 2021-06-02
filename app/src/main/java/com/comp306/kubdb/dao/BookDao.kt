package com.comp306.kubdb.dao

import androidx.room.Dao
import androidx.room.Query
import com.comp306.kubdb.data.custom.BookAverageRating
import com.comp306.kubdb.data.entities.Book
import kotlinx.coroutines.flow.Flow

@Dao
interface BookDao {


    @Query("SELECT isbn, AVG(rating) AS avg_rating FROM rating WHERE isbn IN (:isbn) GROUP BY isbn")
    fun getAverageRating(isbn: List<Int>): Flow<List<BookAverageRating>>

    @Query("SELECT b.* FROM (SELECT MAX(avg_ratings.rating) as max, avg_ratings.author_id as id FROM (SELECT AVG(r.rating) as rating, w.author_id as author_id FROM writes w, rating r WHERE w.isbn = r.isbn GROUP BY w.author_id HAVING COUNT(*) > 5 ORDER BY rating DESC  ) avg_ratings) avg_rating INNER JOIN books b INNER JOIN writes w ON b.isbn = w.isbn AND w.author_id = avg_rating.id")
    fun getBooksOFTopAuthor(): Flow<List<Book>>

}