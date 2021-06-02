package com.comp306.kubdb.data.entities

import androidx.room.*
import com.comp306.kubdb.data.DatetimeTypeConverter
import java.io.Serializable
@Entity(
    tableName = "books",
    foreignKeys = [ForeignKey(
        entity = User::class,
        parentColumns = ["user_id"],
        childColumns = ["borrower_id"],
        onDelete = ForeignKey.SET_NULL,
        onUpdate = ForeignKey.NO_ACTION
    )],
    indices = [Index(
        name = "idx_books_user_id",
        unique = false,
        value = ["borrower_id"]
    )]
)
data class Book(
    @PrimaryKey @ColumnInfo(name = "isbn") val isbn: Int,
    @ColumnInfo(name = "book_title") val title: String,
    @ColumnInfo(name = "year_of_publication") val publicationYear: Int?,
    @ColumnInfo(name = "publisher") val publisher: String?,
    @ColumnInfo(name = "image_url_s") val smallImageUrl: String?,
    @ColumnInfo(name = "image_url_m") val mediumImageUrl: String?,
    @ColumnInfo(name = "image_url_l") val largeImageUrl: String?,
    @TypeConverters(DatetimeTypeConverter::class)
    @ColumnInfo(name = "borrow_date") val borrowDate: String?,
    @TypeConverters(DatetimeTypeConverter::class)
    @ColumnInfo(name = "return_date") val returnDate: String?,
    @ColumnInfo(name = "borrowed") val isBorrowed: Boolean?,
    @ColumnInfo(name = "borrower_id") val borrower: Int?
): Serializable {

    override fun toString(): String {
        return "ID: $isbn, title: $title"
    }
}