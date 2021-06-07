package com.comp306.kubdb.data.views

import androidx.room.ColumnInfo
import androidx.room.DatabaseView

@DatabaseView(
    viewName = "most_ratings_user",
    value = "SELECT MAX(t.cnt), t.user_id FROM (    SELECT COUNT(*) AS cnt, u.user_id FROM users u INNER JOIN rating r ON u.user_id = r.user_id  GROUP BY u.user_id ORDER BY cnt DESC) AS t"
)
data class MostRatingUser(
    @ColumnInfo(name = "author_id") val authorID: Int,
)