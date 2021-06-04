package com.comp306.kubdb

import kotlin.math.pow
import kotlin.math.roundToInt

fun Float.precisionTo(precision: Int) = ((this * 10.0.pow(precision)).roundToInt() / 10.0).toFloat()

fun <T> List<T>.asCommaSeparatedString(): String {
    var result = this[0].toString()
    for (index in 1 until this.size)
        result+=", ${this[index]}"

    return result
}
