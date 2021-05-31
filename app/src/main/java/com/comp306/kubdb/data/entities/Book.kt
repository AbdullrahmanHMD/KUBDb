package com.comp306.kubdb.data.entities

import androidx.room.*
import com.comp306.kubdb.data.DatetimeTypeConverter

@Entity(
    tableName = "Books",
    foreignKeys = [ForeignKey(
        entity = User::class,
        parentColumns = arrayOf("user_id"),
        childColumns = arrayOf("user_id"),
        onDelete = ForeignKey.CASCADE,
        onUpdate = ForeignKey.CASCADE
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
    @ColumnInfo(name = "borrower_id") val borrower: String?
) {

    override fun toString(): String {
        return "ID: $isbn, title: $title"
    }
}