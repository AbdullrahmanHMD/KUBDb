package com.comp306.kubdb.data.custom

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "authors")
data class BookAverageRating(
    @ColumnInfo(name = "isbn") val isbn: Int,
    @ColumnInfo(name = "avg_rating") val averageRating: Double
) {

    fun getAsMapEntry(): Pair<Int, Double> = (isbn to averageRating)

}