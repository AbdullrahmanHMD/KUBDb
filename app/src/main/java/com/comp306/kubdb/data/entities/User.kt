package com.comp306.kubdb.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "users")
data class User(
    @PrimaryKey @ColumnInfo(name = "user_id") val userID: Int,
    @ColumnInfo(name = "password") val password: String,
    @ColumnInfo(name = "first_name") val name: String,
    @ColumnInfo(name = "last_name") val lastName: String,
    @ColumnInfo(name = "age") val age: Int?,
    @ColumnInfo(name = "city") val city: String?,
    @ColumnInfo(name = "state") val state: String?,
    @ColumnInfo(name = "country") val country: String?
): Serializable {
    override fun toString(): String {
        return "ID: $userID, name: $name $lastName, age: $age, Location: $city, $state/$country"
    }
}