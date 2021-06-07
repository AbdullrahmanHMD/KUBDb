package com.comp306.kubdb.data.custom

import androidx.room.ColumnInfo
import java.io.Serializable

data class RealNumber(@ColumnInfo(name = "real") val value: Float): Serializable