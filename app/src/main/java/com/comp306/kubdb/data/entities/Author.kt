package com.comp306.kubdb.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Authors")
data class Author(
    @PrimaryKey @ColumnInfo(name = "author_id") val authorID: Int,
    @ColumnInfo(name = "author_name") val name: String,
    @ColumnInfo(name = "author_surname") val lastName: String
) {
    override fun toString(): String {
        return "ID: $authorID, name: $name $lastName"
    }
}