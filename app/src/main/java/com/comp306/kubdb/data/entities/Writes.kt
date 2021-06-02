package com.comp306.kubdb.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.ForeignKey.NO_ACTION
import androidx.room.Index

@Entity(
    tableName = "writes",
    primaryKeys = ["author_id", "isbn"],
    foreignKeys = [
        ForeignKey(
            entity = Author::class,
            parentColumns = ["author_id"],
            childColumns = ["author_id"],
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
        name = "idx_writes_isbn",
        unique = false,
        value = ["isbn"]
    )]
)
data class Writes(
    @ColumnInfo(name = "author_id") val authorID: Int,
    @ColumnInfo(name = "isbn") val isbn: Int
)