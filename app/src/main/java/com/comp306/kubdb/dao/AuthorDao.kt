package com.comp306.kubdb.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.RoomWarnings
import com.comp306.kubdb.data.custom.AuthorName
import com.comp306.kubdb.data.custom.BookAverageRating
import com.comp306.kubdb.data.custom.RealNumber
import com.comp306.kubdb.data.entities.Author
import com.comp306.kubdb.data.entities.Book
import kotlinx.coroutines.flow.Flow

@Dao
interface AuthorDao {


    @Query("SELECT author_name, author_surname FROM writes NATURAL JOIN authors a WHERE isbn = :isbn")
    fun getBookAuthorsNames(isbn: Int): Flow<List<AuthorName>>

}