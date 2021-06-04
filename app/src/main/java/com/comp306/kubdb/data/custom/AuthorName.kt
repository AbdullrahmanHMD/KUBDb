package com.comp306.kubdb.data.custom

import androidx.room.ColumnInfo
import androidx.room.Ignore

data class AuthorName(
    @ColumnInfo(name = "author_name") val name: String,
    @ColumnInfo(name = "author_surname") val surname: String
) {
    @Ignore
    val fullName = "$name $surname"
}