package com.comp306.kubdb.data.custom

import androidx.room.ColumnInfo
import androidx.room.Entity

data class BookAverageRating(
    @ColumnInfo(name = "isbn") val isbn: Int,
    @ColumnInfo(name = "avg_rating") val averageRating: Float
) {

    fun getAsMapEntry(): Pair<Int, Float> = (isbn to averageRating)

}