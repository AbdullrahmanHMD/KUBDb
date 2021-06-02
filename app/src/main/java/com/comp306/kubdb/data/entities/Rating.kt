package com.comp306.kubdb.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.ForeignKey.NO_ACTION
import androidx.room.Index

@Entity(
    tableName = "rating",
    primaryKeys = ["user_id", "isbn"],
    foreignKeys = [
        ForeignKey(
            entity = User::class,
            parentColumns = ["user_id"],
            childColumns = ["user_id"],
            onDelete = CASCADE,
            onUpdate = NO_ACTION
        ), ForeignKey(
            entity = Book::class,
            parentColumns = ["isbn"],
            childColumns = ["isbn"],
            onDelete = CASCADE,
            onUpdate = NO_ACTION
        )],
    indices = [Index(
        name = "idx_rating_isbn",
        unique = false,
        value = ["isbn"]
    )]
)
data class Rating(
    @ColumnInfo(name = "user_id") val userID: Int,
    @ColumnInfo(name = "isbn") val isbn: Int,
    @ColumnInfo(name = "rating") val rating: Int
)